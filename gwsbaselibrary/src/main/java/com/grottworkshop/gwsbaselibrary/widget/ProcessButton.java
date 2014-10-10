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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;

import com.grottworkshop.gwsbaselibrary.R;

/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public abstract class ProcessButton extends FlatButton {

    private int mProgress;
    private int mMaxProgress;
    private int mMinProgress;

    private GradientDrawable mProgressDrawable;
    private GradientDrawable mCompleteDrawable;
    private GradientDrawable mErrorDrawable;

    private CharSequence mLoadingText;
    private CharSequence mCompleteText;
    private CharSequence mErrorText;

    /**
     * Instantiates a new Process button.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyle the def style
     */
    public ProcessButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * Instantiates a new Process button.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public ProcessButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Instantiates a new Process button.
     *
     * @param context the context
     */
    public ProcessButton(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        mMinProgress = 0;
        mMaxProgress = 100;

        mProgressDrawable = (GradientDrawable) getDrawable(R.drawable.apb_rect_progress).mutate();
        mProgressDrawable.setCornerRadius(getCornerRadius());

        mCompleteDrawable = (GradientDrawable) getDrawable(R.drawable.apb_rect_complete).mutate();
        mCompleteDrawable.setCornerRadius(getCornerRadius());

        mErrorDrawable = (GradientDrawable) getDrawable(R.drawable.apb_rect_error).mutate();
        mErrorDrawable.setCornerRadius(getCornerRadius());

        if (attrs != null) {
            initAttributes(context, attrs);
        }
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.ProcessButton);

        if (attr == null) {
            return;
        }

        try {
            mLoadingText = attr.getString(R.styleable.ProcessButton_pb_textProgress);
            mCompleteText = attr.getString(R.styleable.ProcessButton_pb_textComplete);
            mErrorText = attr.getString(R.styleable.ProcessButton_pb_textError);

            int purple = getColor(R.color.purple_progress);
            int colorProgress = attr.getColor(R.styleable.ProcessButton_pb_colorProgress, purple);
            mProgressDrawable.setColor(colorProgress);

            int green = getColor(R.color.green_complete);
            int colorComplete = attr.getColor(R.styleable.ProcessButton_pb_colorComplete, green);
            mCompleteDrawable.setColor(colorComplete);

            int red = getColor(R.color.red_error);
            int colorError = attr.getColor(R.styleable.ProcessButton_pb_colorError, red);
            mErrorDrawable.setColor(colorError);

        } finally {
            attr.recycle();
        }
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(int progress) {
        mProgress = progress;

        if (mProgress == mMinProgress) {
            onNormalState();
        } else if (mProgress == mMaxProgress) {
            onCompleteState();
        } else if (mProgress < mMinProgress){
            onErrorState();
        } else {
            onProgress();
        }

        invalidate();
    }

    /**
     * On error state.
     */
    protected void onErrorState() {
        if(getErrorText() != null) {
            setText(getErrorText());
        }
        setBackgroundCompat(getErrorDrawable());
    }

    /**
     * On progress.
     */
    protected void onProgress() {
        if(getLoadingText() != null) {
            setText(getLoadingText());
        }
        setBackgroundCompat(getNormalDrawable());
    }

    /**
     * On complete state.
     */
    protected void onCompleteState() {
        if(getCompleteText() != null) {
            setText(getCompleteText());
        }
        setBackgroundCompat(getCompleteDrawable());
    }

    /**
     * On normal state.
     */
    protected void onNormalState() {
        if(getNormalText() != null) {
            setText(getNormalText());
        }
        setBackgroundCompat(getNormalDrawable());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // progress
        if(mProgress > mMinProgress && mProgress < mMaxProgress) {
            drawProgress(canvas);
        }

        super.onDraw(canvas);
    }

    /**
     * Draw progress.
     *
     * @param canvas the canvas
     */
    public abstract void drawProgress(Canvas canvas);

    /**
     * Gets progress.
     *
     * @return the progress
     */
    public int getProgress() {
        return mProgress;
    }

    /**
     * Gets max progress.
     *
     * @return the max progress
     */
    public int getMaxProgress() {
        return mMaxProgress;
    }

    /**
     * Gets min progress.
     *
     * @return the min progress
     */
    public int getMinProgress() {
        return mMinProgress;
    }

    /**
     * Gets progress drawable.
     *
     * @return the progress drawable
     */
    public GradientDrawable getProgressDrawable() {
        return mProgressDrawable;
    }

    /**
     * Gets complete drawable.
     *
     * @return the complete drawable
     */
    public GradientDrawable getCompleteDrawable() {
        return mCompleteDrawable;
    }

    /**
     * Gets loading text.
     *
     * @return the loading text
     */
    public CharSequence getLoadingText() {
        return mLoadingText;
    }

    /**
     * Gets complete text.
     *
     * @return the complete text
     */
    public CharSequence getCompleteText() {
        return mCompleteText;
    }

    /**
     * Sets progress drawable.
     *
     * @param progressDrawable the progress drawable
     */
    public void setProgressDrawable(GradientDrawable progressDrawable) {
        mProgressDrawable = progressDrawable;
    }

    /**
     * Sets complete drawable.
     *
     * @param completeDrawable the complete drawable
     */
    public void setCompleteDrawable(GradientDrawable completeDrawable) {
        mCompleteDrawable = completeDrawable;
    }

    /**
     * Sets loading text.
     *
     * @param loadingText the loading text
     */
    public void setLoadingText(CharSequence loadingText) {
        mLoadingText = loadingText;
    }

    /**
     * Sets complete text.
     *
     * @param completeText the complete text
     */
    public void setCompleteText(CharSequence completeText) {
        mCompleteText = completeText;
    }

    /**
     * Gets error drawable.
     *
     * @return the error drawable
     */
    public GradientDrawable getErrorDrawable() {
        return mErrorDrawable;
    }

    /**
     * Sets error drawable.
     *
     * @param errorDrawable the error drawable
     */
    public void setErrorDrawable(GradientDrawable errorDrawable) {
        mErrorDrawable = errorDrawable;
    }

    /**
     * Gets error text.
     *
     * @return the error text
     */
    public CharSequence getErrorText() {
        return mErrorText;
    }

    /**
     * Sets error text.
     *
     * @param errorText the error text
     */
    public void setErrorText(CharSequence errorText) {
        mErrorText = errorText;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.mProgress = mProgress;

        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            mProgress = savedState.mProgress;
            super.onRestoreInstanceState(savedState.getSuperState());
            setProgress(mProgress);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    /**
     * A {@link android.os.Parcelable} representing the {@link ProcessButton}'s
     * state.
     */
    public static class SavedState extends BaseSavedState {

        private int mProgress;

        /**
         * Instantiates a new Saved state.
         *
         * @param parcel the parcel
         */
        public SavedState(Parcelable parcel) {
            super(parcel);
        }

        private SavedState(Parcel in) {
            super(in);
            mProgress = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(mProgress);
        }

        /**
         * The constant CREATOR.
         */
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
