package com.github.florent37.fiftyshadesof;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.lang.ref.WeakReference;

/**
 * Created by f.champigny on 30/08/16.
 */
public class GreyDrawable extends Drawable {
    public final static int DEFAULT_GREY = Color.argb(100, 215, 215, 215);
    public final static int DARKER_GREY = Color.argb(200, 180, 180, 180);

    public final static int DEFAULT_GREY_BOLD = Color.argb(100, 180, 180, 180);
    public final static int DARKER_GREY_BOLD = Color.argb(200, 150, 150, 150);

    public final static int DURATION = 750;
    public final static int STOP_DURATION = 200;

    int grayColor = DEFAULT_GREY;
    ValueAnimator valueAnimator;
    Paint paint;

    WeakReference<View> viewWeakReference;

    public GreyDrawable() {
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(grayColor);
        canvas.drawRect(canvas.getClipBounds(), paint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 255;
    }

    public void start(View view, boolean darker) {
        viewWeakReference = new WeakReference<>(view);

        final int defaultGrey = darker ? DEFAULT_GREY_BOLD : DEFAULT_GREY;
        final int darkerGrey = darker ? DARKER_GREY_BOLD : DARKER_GREY;

        valueAnimator = ValueAnimator.ofInt(defaultGrey, darkerGrey);
        valueAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        valueAnimator.setDuration(DURATION);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                grayColor = (int) valueAnimator.getAnimatedValue();

                View v = viewWeakReference.get();
                if (v != null) {
                    v.invalidate();
                }
            }
        });
        valueAnimator.start();
    }

    public void cancel() {
        valueAnimator.cancel();
    }

    public void stop(@Nullable final Callback callback) {
        valueAnimator.cancel();

        final int emptyColor = Color.argb(0, Color.red(grayColor), Color.green(grayColor), Color.blue(grayColor));

        valueAnimator = ValueAnimator.ofInt(grayColor, emptyColor);
        valueAnimator.setDuration(STOP_DURATION);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                grayColor = (int) valueAnimator.getAnimatedValue();

                View v = viewWeakReference.get();
                if (v != null) {
                    v.invalidate();
                }
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (callback != null) {
                    callback.onFadeOutFinished();
                }
            }
        });
        valueAnimator.setStartDelay(50);
        valueAnimator.setDuration(400);
        valueAnimator.start();

        if (callback != null) {
            callback.onFadeOutStarted();
        }
    }

    public interface Callback {
        void onFadeOutStarted();

        void onFadeOutFinished();
    }
}
