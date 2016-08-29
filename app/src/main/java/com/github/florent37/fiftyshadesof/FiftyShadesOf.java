package com.github.florent37.fiftyshadesof;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florentchampigny on 29/08/16.
 */
public class FiftyShadesOf {
    private final Context context;
    private final List<View> viewList;

    public FiftyShadesOf(Context context) {
        this.context = context;
        this.viewList = new ArrayList<>();
    }

    public static FiftyShadesOf with(Context context) {
        return new FiftyShadesOf(context);
    }

    public FiftyShadesOf on(int... viewsId) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            for (int view : viewsId) {
                viewList.add(activity.findViewById(view));
            }
        }
        return this;
    }

    public FiftyShadesOf on(View... views) {
        this.viewList.addAll(Arrays.asList(views));
        return this;
    }

    public FiftyShadesOf on(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; ++i) {
            View child = viewGroup.getChildAt(i);
            if (accept(child)) {
                this.viewList.add(child);
            } else if (child instanceof ViewGroup) {
                on((ViewGroup) child);
            }
        }
        return this;
    }

    private boolean accept(View view) {
        return view instanceof TextView
                || view instanceof ImageView;
    }

    public FiftyShadesOf exept(View... views) {
        this.viewList.removeAll(Arrays.asList(views));
        return this;
    }

    public void start() {
        for(View view : viewList){
            if(view instanceof TextView){

            } else if(view instanceof ImageView){

            }
        }
    }

    public void stop() {

    }
}
