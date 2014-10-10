package com.grottworkshop.gwsbaselibrary.widget.magnet;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.grottworkshop.gwsbaselibrary.R;

/**
 * Created by prem on 7/20/14.
 * ViewHolder for the remove Icon.
 */
class RemoveView {

    /**
     * The M layout.
     */
    View mLayout;
    /**
     * The M button.
     */
    View mButton;
    /**
     * The M shadow.
     */
    View mShadow;
    /**
     * The M button image.
     */
    ImageView mButtonImage;
    private WindowManager mWindowManager;
    private SimpleAnimator mShowAnim;
    private SimpleAnimator mHideAnim;

    private SimpleAnimator mShadowFadeOut;
    private SimpleAnimator mShadowFadeIn;

    private final int buttonBottomPadding;

    /**
     * The Should be responsive.
     */
    boolean shouldBeResponsive = true;

    /**
     * Instantiates a new Remove view.
     *
     * @param context the context
     */
    RemoveView(Context context) {
        mLayout = LayoutInflater.from(context).inflate(R.layout.magnet_x_button_holder, null);
        mButton = mLayout.findViewById(R.id.xButton);
        mButtonImage = (ImageView) mLayout.findViewById(R.id.xButtonImg);
        mButtonImage.setImageResource(R.drawable.trash);
        buttonBottomPadding = mButton.getPaddingBottom();
        mShadow = mLayout.findViewById(R.id.shadow);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        addToWindow(mLayout);
        mShowAnim = new SimpleAnimator(mButton, R.anim.magnet_slide_up);
        mHideAnim = new SimpleAnimator(mButton, R.anim.magnet_slide_down);
        mShadowFadeIn = new SimpleAnimator(mShadow, android.R.anim.fade_in);
        mShadowFadeOut = new SimpleAnimator(mShadow, android.R.anim.fade_out);
        hide();
    }

    /**
     * Sets icon res id.
     *
     * @param id the id
     */
    void setIconResId(int id) {
        mButtonImage.setImageResource(id);
    }

    /**
     * Sets shadow bG.
     *
     * @param shadowBG the shadow bG
     */
    void setShadowBG(int shadowBG) {
        mShadow.setBackgroundResource(shadowBG);
    }

    /**
     * Show void.
     */
    void show() {
        if (mLayout != null && mLayout.getParent() == null) {
            addToWindow(mLayout);
        }
        mShadowFadeIn.startAnimation();
        mShowAnim.startAnimation();
    }

    /**
     * Hide void.
     */
    void hide() {
        mShadowFadeOut.startAnimation();
        mHideAnim.startAnimation(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mLayout != null && mLayout.getParent() != null) {
                    mWindowManager.removeView(mLayout);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * On move.
     *
     * @param x the x
     * @param y the y
     */
    void onMove(final float x, final float y) {
        if (shouldBeResponsive) {
            final int xTransformed = (int) Math.abs(x * 100 / (mButton.getContext().getResources().getDisplayMetrics().widthPixels / 2));
            final int bottomPadding = buttonBottomPadding - (xTransformed / 5);
            if (x < 0) {
                mButton.setPadding(0, 0, xTransformed, bottomPadding);
            } else {
                mButton.setPadding(xTransformed, 0, 0, bottomPadding);
            }
        }
    }

    /**
     * Destroy void.
     */
    void destroy() {
        if (mLayout != null && mLayout.getParent() != null) {
            mWindowManager.removeView(mLayout);
        }
        mLayout = null;
        mWindowManager = null;
    }

    private void addToWindow(View layout) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
        );
        mWindowManager.addView(layout, params);
    }
}
