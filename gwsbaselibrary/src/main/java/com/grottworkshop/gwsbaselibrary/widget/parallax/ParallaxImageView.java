package com.grottworkshop.gwsbaselibrary.widget.parallax;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by f.laurent on 21/11/13.
 * antoine-merle.com inspiration
 */
public class ParallaxImageView extends ImageView {

    private int mCurrentTranslation;

    /**
     * Instantiates a new Parallax image view.
     *
     * @param context the context
     */
    public ParallaxImageView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Parallax image view.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public ParallaxImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Parallax image view.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyle the def style
     */
    public ParallaxImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Sets current translation.
     *
     * @param currentTranslation the current translation
     */
    public void setCurrentTranslation(int currentTranslation) {
        mCurrentTranslation = currentTranslation;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(0, -mCurrentTranslation / 2)  ;
        super.draw(canvas);
        canvas.restore();
    }
}
