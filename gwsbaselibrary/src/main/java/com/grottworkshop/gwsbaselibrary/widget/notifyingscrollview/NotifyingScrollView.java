package com.grottworkshop.gwsbaselibrary.widget.notifyingscrollview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * The type Notifying scroll view.
 * @author Cyril Mottier with modifications from Manuel Peinado
 * Created by fgrott on 10/9/2014.
 */
public class NotifyingScrollView extends ScrollView {
    // Edge-effects don't mix well with the translucent action bar in Android 2.X
    private boolean mDisableEdgeEffects = true;

    /**
     * The interface On scroll changed listener.
     * @author Cyril Mottier
     */
    public interface OnScrollChangedListener {
        /**
         * On scroll changed.
         *
         * @param who the who
         * @param l the l
         * @param t the t
         * @param oldl the oldl
         * @param oldt the oldt
         */
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }

    private OnScrollChangedListener mOnScrollChangedListener;

    /**
     * Instantiates a new Notifying scroll view.
     *
     * @param context the context
     */
    public NotifyingScrollView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Notifying scroll view.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public NotifyingScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Notifying scroll view.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyle the def style
     */
    public NotifyingScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    /**
     * Sets on scroll changed listener.
     *
     * @param listener the listener
     */
    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
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
}