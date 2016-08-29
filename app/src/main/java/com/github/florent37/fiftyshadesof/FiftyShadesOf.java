package com.github.florent37.fiftyshadesof;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by florentchampigny on 29/08/16.
 */
public class FiftyShadesOf {
    private final Context context;

    private HashMap<View, ViewState> viewsState;

    static abstract class ViewState<V extends View> {
        V view;
        Drawable background;

        public ViewState(V view) {
            this.view = view;
            init();
        }

        protected void init() {
            this.background = view.getBackground();
        }

        protected void restore() {
            this.view.setBackgroundDrawable(background);
        }

        public void start() {
            GreyDrawable drawable = new GreyDrawable();
            handleDrawable(drawable);
            drawable.start(view);
        }

        public void stop() {
            restore();
        }

        protected abstract void handleDrawable(GreyDrawable greyDrawable);
    }

    static class TextViewState extends ViewState<TextView> {
        ColorStateList textColor;

        public TextViewState(TextView textView) {
            super(textView);
        }

        @Override
        protected void init() {
            super.init();
            this.textColor = view.getTextColors();
        }

        @Override
        protected void restore() {
            this.view.setBackgroundDrawable(null);
            this.view.setTextColor(textColor);
        }

        @Override
        public void start() {
            super.start();
            view.setTextColor(Color.TRANSPARENT);
        }

        @Override
        protected void handleDrawable(GreyDrawable greyDrawable) {
            this.view.setBackgroundDrawable(greyDrawable);
        }

    }

    static class ImageViewState extends ViewState<ImageView> {
        Drawable source;

        public ImageViewState(ImageView imageView) {
            super(imageView);
        }

        @Override
        protected void init() {
            super.init();
            this.source = view.getDrawable();
        }

        @Override
        protected void restore() {
            this.view.setImageDrawable(source);
        }

        @Override
        protected void handleDrawable(GreyDrawable greyDrawable) {
            this.view.setImageDrawable(greyDrawable);
        }

    }

    static class GreyDrawable extends Drawable {

        public final static int DEFAULT_GREY = Color.rgb(215, 215, 215);
        public final static int DARKER_GREY = Color.rgb(180, 180, 180);
        public final static int DURATION = 750;

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

        public void start(View view) {
            viewWeakReference = new WeakReference<View>(view);

            valueAnimator = ValueAnimator.ofInt(DEFAULT_GREY, DARKER_GREY);
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
    }

    public FiftyShadesOf(Context context) {
        this.context = context;
        this.viewsState = new HashMap<>();
    }

    public static FiftyShadesOf with(Context context) {
        return new FiftyShadesOf(context);
    }

    public FiftyShadesOf on(int... viewsId) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            for (int view : viewsId) {
                add(activity.findViewById(view));
            }
        }
        return this;
    }

    public void add(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            viewsState.put(view, new TextViewState(textView));
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            viewsState.put(view, new ImageViewState(imageView));
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; ++i) {
                View child = viewGroup.getChildAt(i);
                add(child);
            }
        }
    }

    public FiftyShadesOf on(View... views) {
        for (View view : views)
            add(view);
        return this;
    }

    public FiftyShadesOf except(View... views) {
        for (View view : views) {
            this.viewsState.remove(view);
        }
        return this;
    }

    public FiftyShadesOf start() {
        for (ViewState viewState : viewsState.values()) {
            viewState.start();
        }
        return this;
    }

    public FiftyShadesOf stop() {
        for (ViewState viewState : viewsState.values()) {
            viewState.stop();
        }
        return this;
    }
}
