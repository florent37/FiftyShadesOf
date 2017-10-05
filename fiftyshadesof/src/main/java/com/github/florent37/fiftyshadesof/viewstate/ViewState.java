package com.github.florent37.fiftyshadesof.viewstate;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.github.florent37.fiftyshadesof.GreyDrawable;

/**
 * Created by f.champigny on 30/08/16.
 */
public abstract class ViewState<V extends View> {
    V view;
    Drawable background;
    protected boolean darker;

    public ViewState(V view) {
        this.view = view;
    }

    protected void restore() {
    }

    protected void restoreBackground() {
        this.view.setBackgroundDrawable(background);
    }

    public void beforeStart(){
        this.background = view.getBackground();
    }

    public void start(boolean fadein) {
        GreyDrawable greyDrawable = new GreyDrawable();
        this.view.setBackgroundDrawable(greyDrawable);
        greyDrawable.setFadein(fadein);
        greyDrawable.start(view, darker);
    }

    public void stop() {
        Drawable drawable = this.view.getBackground();
        if(drawable instanceof GreyDrawable){
            GreyDrawable greyDrawable = (GreyDrawable)drawable;
            greyDrawable.stop(new GreyDrawable.Callback(){
                @Override
                public void onFadeOutStarted() {
                    restore();
                }

                @Override
                public void onFadeOutFinished() {
                    restoreBackground();
                }
            });
        } else {
            restore();
        }
    }
}
