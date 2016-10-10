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
    public final static int DEFAULT_GREY = Color.argb(50, 200, 200, 200);
    public final static int DARKER_GREY = Color.argb(160, 180, 180, 180);

    public final static int DEFAULT_GREY_BOLD = Color.argb(50, 180, 180, 180);
    public final static int DARKER_GREY_BOLD = Color.argb(180, 150, 150, 150);

    public final static int DURATION = 750;
    public final static int STOP_DURATION = 200;

    boolean fadein = false;

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

    public boolean isFadein() {
        return fadein;
    }

    public void setFadein(boolean fadein) {
        this.fadein = fadein;
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

        if (fadein) {
            stopFadeIn(callback);
        } else {
            stopGray(callback);
        }
    }

    public void stopFadeIn(@Nullable final Callback callback) {
        if (callback != null) {
            View v = viewWeakReference.get();
            if (v != null) {
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(v, View.ALPHA, 0f).setDuration(400);
                alphaAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        callback.onFadeOutStarted();

                        callback.onFadeOutFinished();
                        View v = viewWeakReference.get();
                        if (v != null) {
                            ObjectAnimator.ofFloat(v, View.ALPHA, 1f).start();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        onAnimationEnd(animation);
                    }
                });
                alphaAnimator.start();
            }
        }
    }

    public void stopGray(@Nullable final Callback callback) {

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
                    View v = viewWeakReference.get();
                    if (v != null) {
                        ObjectAnimator.ofFloat(v, View.ALPHA, 1f).start();
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
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
