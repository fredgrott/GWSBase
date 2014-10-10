/*
 *    The MIT License (MIT)
 *
 *   Copyright (c) 2014 Danylyk Dmytro
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */
package com.grottworkshop.gwsbaselibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import com.grottworkshop.gwsbaselibrary.R;

/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public class FlatButton extends Button {

    private StateListDrawable mNormalDrawable;
    private CharSequence mNormalText;
    private float cornerRadius;

    /**
     * Instantiates a new Flat button.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyle the def style
     */
    public FlatButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * Instantiates a new Flat button.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Instantiates a new Flat button.
     *
     * @param context the context
     */
    public FlatButton(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        mNormalDrawable = new StateListDrawable();
        if (attrs != null) {
            initAttributes(context, attrs);
        }
        mNormalText = getText().toString();
        setBackgroundCompat(mNormalDrawable);
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.FlatButton);
        if(attr == null) {
            return;
        }

        try {

            float defValue = getDimension(R.dimen.corner_radius);
            cornerRadius = attr.getDimension(R.styleable.FlatButton_pb_cornerRadius, defValue);

            mNormalDrawable.addState(new int[]{android.R.attr.state_pressed},
                    createPressedDrawable(attr));
            mNormalDrawable.addState(new int[] { }, createNormalDrawable(attr));

        } finally {
            attr.recycle();
        }
    }

    private LayerDrawable createNormalDrawable(TypedArray attr) {
        LayerDrawable drawableNormal =
                (LayerDrawable) getDrawable(R.drawable.apb_rect_normal).mutate();

        GradientDrawable drawableTop =
                (GradientDrawable) drawableNormal.getDrawable(0).mutate();
        drawableTop.setCornerRadius(getCornerRadius());

        int blueDark = getColor(R.color.blue_pressed);
        int colorPressed = attr.getColor(R.styleable.FlatButton_pb_colorPressed, blueDark);
        drawableTop.setColor(colorPressed);

        GradientDrawable drawableBottom =
                (GradientDrawable) drawableNormal.getDrawable(1).mutate();
        drawableBottom.setCornerRadius(getCornerRadius());

        int blueNormal = getColor(R.color.blue_normal);
        int colorNormal = attr.getColor(R.styleable.FlatButton_pb_colorNormal, blueNormal);
        drawableBottom.setColor(colorNormal);
        return drawableNormal;
    }

    private Drawable createPressedDrawable(TypedArray attr) {
        GradientDrawable drawablePressed =
                (GradientDrawable) getDrawable(R.drawable.apb_rect_pressed).mutate();
        drawablePressed.setCornerRadius(getCornerRadius());

        int blueDark = getColor(R.color.blue_pressed);
        int colorPressed = attr.getColor(R.styleable.FlatButton_pb_colorPressed, blueDark);
        drawablePressed.setColor(colorPressed);

        return drawablePressed;
    }

    /**
     * Gets drawable.
     *
     * @param id the id
     * @return the drawable
     */
    protected Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }

    /**
     * Gets dimension.
     *
     * @param id the id
     * @return the dimension
     */
    protected float getDimension(int id) {
        return getResources().getDimension(id);
    }

    /**
     * Gets color.
     *
     * @param id the id
     * @return the color
     */
    protected int getColor(int id) {
        return getResources().getColor(id);
    }

    /**
     * Gets typed array.
     *
     * @param context the context
     * @param attributeSet the attribute set
     * @param attr the attr
     * @return the typed array
     */
    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    /**
     * Gets corner radius.
     *
     * @return the corner radius
     */
    public float getCornerRadius() {
        return cornerRadius;
    }

    /**
     * Gets normal drawable.
     *
     * @return the normal drawable
     */
    public StateListDrawable getNormalDrawable() {
        return mNormalDrawable;
    }

    /**
     * Gets normal text.
     *
     * @return the normal text
     */
    public CharSequence getNormalText() {
        return mNormalText;
    }

    /**
     * Set the View's background. Masks the API changes made in Jelly Bean.
     *
     * @param drawable the drawable
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public void setBackgroundCompat(Drawable drawable) {
        int pL = getPaddingLeft();
        int pT = getPaddingTop();
        int pR = getPaddingRight();
        int pB = getPaddingBottom();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
        setPadding(pL, pT, pR, pB);
    }
}
