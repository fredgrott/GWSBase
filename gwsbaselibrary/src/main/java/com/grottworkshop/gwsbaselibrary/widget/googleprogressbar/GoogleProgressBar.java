/*
   Copyright 2013 Javier Pardo de Santayana GÃ³mez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 */
package com.grottworkshop.gwsbaselibrary.widget.googleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.grottworkshop.gwsbaselibrary.R;


/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public class GoogleProgressBar extends ProgressBar {

    private enum ProgressType{
        /**
         * The FOLDING_CIRCLES.
         */
        FOLDING_CIRCLES,
        /**
         * The GOOGLE_MUSIC_DICES.
         */
        GOOGLE_MUSIC_DICES,
        /**
         * The NEXUS_ROTATION_CROSS.
         */
        NEXUS_ROTATION_CROSS
    }

    /**
     * Instantiates a new Google progress bar.
     *
     * @param context the context
     */
    public GoogleProgressBar(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Google progress bar.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public GoogleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,android.R.attr.progressBarStyle);
    }

    /**
     * Instantiates a new Google progress bar.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyle the def style
     */
    public GoogleProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GoogleProgressBar, defStyle, 0);
        final int typeIndex = a.getInteger(R.styleable.GoogleProgressBar_type, context.getResources().getInteger(R.integer.default_type));
        final int colorsId = a.getResourceId(R.styleable.GoogleProgressBar_colors, R.array.google_colors);
        a.recycle();

        Drawable drawable = buildDrawable(context,typeIndex,colorsId);
        if(drawable!=null)
            setIndeterminateDrawable(drawable);
    }

    private Drawable buildDrawable(Context context, int typeIndex,int colorsId) {
        Drawable drawable = null;
        ProgressType type = ProgressType.values()[typeIndex];
        switch (type){
            case FOLDING_CIRCLES:
                drawable = new FoldingCirclesDrawable.Builder(context)
                        .colors(getResources().getIntArray(colorsId))
                        .build();
                break;
            case GOOGLE_MUSIC_DICES:
                drawable = new GoogleMusicDicesDrawable.Builder()
                        .build();
                break;
            case NEXUS_ROTATION_CROSS:
                drawable = new NexusRotationCrossDrawable.Builder(context)
                        .colors(getResources().getIntArray(colorsId))
                        .build();
        }

        return drawable;
    }
}
