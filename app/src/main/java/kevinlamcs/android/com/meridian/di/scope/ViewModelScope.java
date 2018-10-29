package kevinlamcs.android.com.meridian.di.scope;

import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import dagger.MapKey;


@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelScope {
    Class<? extends ViewModel> value();
}
