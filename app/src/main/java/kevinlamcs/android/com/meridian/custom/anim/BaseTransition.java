package kevinlamcs.android.com.meridian.custom.anim;

import android.content.Context;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.util.AttributeSet;

public abstract class BaseTransition extends Transition {

    public abstract void captureValues(TransitionValues transitionValues);

    public BaseTransition() {
    }

    public BaseTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }
}
