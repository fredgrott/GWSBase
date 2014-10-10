package com.grottworkshop.gwsbaselibrary.widget.fadingactionbar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 *
 *
 * Created by fgrott on 10/9/2014.
 */
public class ObservableScrollView extends ScrollView implements ObservableScrollable {
    // Edge-effects don't mix well with the translucent action bar in Android 2.X
    private boolean mDisableEdgeEffects = true;

    private OnScrollChangedCallback mOnScrollChangedListener;

    /**
     * Instantiates a new Observable scroll view.
     *
     * @param context the context
     */
    public ObservableScrollView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Observable scroll view.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Observable scroll view.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyle the def style
     */
    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScroll(l, t);
        }
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        // http://stackoverflow.com/a/6894270/244576
        if (mDisableEdgeEffects && Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return 0.0f;
        }
        return super.getTopFadingEdgeStrength();
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        // http://stackoverflow.com/a/6894270/244576
        if (mDisableEdgeEffects && Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return 0.0f;
        }
        return super.getBottomFadingEdgeStrength();
    }

    @Override
    public void setOnScrollChangedCallback(OnScrollChangedCallback callback) {
        mOnScrollChangedListener = callback;
    }
}