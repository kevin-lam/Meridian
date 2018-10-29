package kevinlamcs.android.com.meridian.custom.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class Elevate extends BaseTransition {
    private static final String PROPNAME_ELEVATION = "anim:elevate:elevation";

    public Elevate() {
    }

    public Elevate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void captureValues(TransitionValues transitionValues) {
        float elevation = transitionValues.view.getElevation();
        transitionValues.values.put(PROPNAME_ELEVATION, elevation);
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        final View view;
        float startElevation, endElevation;
        if (startValues == null && endValues != null) {
            view = endValues.view;
            endElevation = (float) endValues.values.get(PROPNAME_ELEVATION);
            startElevation = endElevation - 16;
        } else if (startValues != null && endValues == null){
            view = startValues.view;
            startElevation = (float) startValues.values.get(PROPNAME_ELEVATION);
            endElevation = startElevation + 16;
        } else {
            view = startValues.view;
            startElevation = (float) startValues.values.get(PROPNAME_ELEVATION);
            endElevation = (float) endValues.values.get(PROPNAME_ELEVATION);
        }

        ValueAnimator animator = ValueAnimator.ofFloat(startElevation, endElevation);
        animator.addUpdateListener(animation -> view.setElevation((float)animation.getAnimatedValue()));
        return animator;
    }
}
