package com.github.florent37.fiftyshadesof;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.florent37.fiftyshadesof.viewstate.ImageViewState;
import com.github.florent37.fiftyshadesof.viewstate.TextViewState;
import com.github.florent37.fiftyshadesof.viewstate.ViewState;
import java.util.HashMap;

/**
 * Created by florentchampigny on 29/08/16.
 */
public class FiftyShadesOf {
    private final Context context;

    private HashMap<View, ViewState> viewsState;

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
