package kevinlamcs.android.com.meridian.util.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

import kevinlamcs.android.com.meridian.util.log.ApplicationLogger;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private final AtomicBoolean pending = new AtomicBoolean(false);

    @MainThread
    public void observe(LifecycleOwner owner, final Observer<T> observer) {

        if (hasActiveObservers()) {
            ApplicationLogger.w(getClass().getSimpleName(), "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                if (pending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t) {
        pending.set(true);
        super.setValue(t);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
