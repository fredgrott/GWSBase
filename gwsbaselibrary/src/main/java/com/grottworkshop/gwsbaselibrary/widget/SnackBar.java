/*
   Copyright {2014} MrEngineer13, Modified by Fred Grott(GrottWorkShop) Copyright 2014

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
package com.grottworkshop.gwsbaselibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.grottworkshop.gwsbaselibrary.R;

import java.util.Stack;

/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public class SnackBar {
    private static final String SAVED_MSGS = "SAVED_MSGS";

    private static final String SAVED_CURR_MSG = "SAVED_CURR_MSG";

    private static final int ANIMATION_DURATION = 300;

    /**
     * The constant LONG_SNACK.
     */
    public static final short LONG_SNACK = 5000;

    /**
     * The constant MED_SNACK.
     */
    public static final short MED_SNACK = 3500;

    /**
     * The constant SHORT_SNACK.
     */
    public static final short SHORT_SNACK = 2000;

    /**
     * The constant PERMANENT_SNACK.
     */
    public static final short PERMANENT_SNACK = 0;

    private View mContainer;

    private View mParentView;

    private TextView mSnackMsg;

    private Button mSnackBtn;

    private Stack<Snack> mSnacks = new Stack<Snack>();

    private Snack mCurrentSnack;

    private boolean mShowing;

    private OnMessageClickListener mClickListener;

    private OnVisibilityChangeListener mVisibilityChangeListener;

    private Handler mHandler;

    private float mPreviousY;

    private AnimationSet mOutAnimationSet;

    private AnimationSet mInAnimationSet;

    private Context mContext;

    /**
     * The interface On message click listener.
     */
    public interface OnMessageClickListener {

        /**
         * On message click.
         *
         * @param token the token
         */
        void onMessageClick(Parcelable token);
    }

    /**
     * The interface On visibility change listener.
     */
    public interface OnVisibilityChangeListener {

        /**
         * On show.
         *
         * @param stackSize the stack size
         */
        void onShow(int stackSize);

        /**
         * On hide.
         *
         * @param stackSize the stack size
         */
        void onHide(int stackSize);
    }

    /**
     * Instantiates a new Snack bar.
     *
     * @param activity the activity
     */
    public SnackBar(Activity activity) {
        mContext = activity.getApplicationContext();
        ViewGroup container = (ViewGroup) activity.findViewById(android.R.id.content);
        View v = activity.getLayoutInflater().inflate(R.layout.sb__snack, container);
        init(v);
    }

    /**
     * Instantiates a new Snack bar.
     *
     * @param context the context
     * @param v the v
     */
    public SnackBar(Context context, View v) {
        mContext = context;
        init(v);
    }

    private void init(View v) {
        mParentView = v;
        mContainer = v.findViewById(R.id.snackContainer);
        mContainer.setVisibility(View.GONE);
        mSnackMsg = (TextView) v.findViewById(R.id.snackMessage);
        mSnackBtn = (Button) v.findViewById(R.id.snackButton);
        mSnackBtn.setOnClickListener(mButtonListener);

        mInAnimationSet = new AnimationSet(false);

        TranslateAnimation mSlideInAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f);

        AlphaAnimation mFadeInAnimation = new AlphaAnimation(0.0f, 1.0f);

        mInAnimationSet.addAnimation(mSlideInAnimation);
        mInAnimationSet.addAnimation(mFadeInAnimation);

        mOutAnimationSet = new AnimationSet(false);

        TranslateAnimation mSlideOutAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1.0f);

        AlphaAnimation mFadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);

        mOutAnimationSet.addAnimation(mSlideOutAnimation);
        mOutAnimationSet.addAnimation(mFadeOutAnimation);

        mOutAnimationSet.setDuration(ANIMATION_DURATION);
        mOutAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                sendOnHide();
                if (!mSnacks.empty()) {
                    show(mSnacks.pop());
                } else {
                    mCurrentSnack = null;
                    mContainer.setVisibility(View.GONE);
                    mShowing = false;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mHandler = new Handler();
    }


    /**
     * Show void.
     *
     * @param message the message
     */
    public void show(String message) {
        show(message, null);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     */
    public void show(String message, String actionMessage) {
        show(message, actionMessage, Style.DEFAULT);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param textColor the text color
     */
    public void show(String message, String actionMessage, int textColor) {
        show(message, actionMessage, textColor, 0);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param actionStyle the action style
     */
    public void show(String message, String actionMessage, Style actionStyle) {
        show(message, actionMessage, actionStyle, 0);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param textColor the text color
     * @param actionIcon the action icon
     */
    public void show(String message, String actionMessage, int textColor, int actionIcon) {
        show(message, actionMessage, textColor, actionIcon, null);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param actionStyle the action style
     * @param actionIcon the action icon
     */
    public void show(String message, String actionMessage, Style actionStyle, int actionIcon) {
        show(message, actionMessage, actionStyle, actionIcon, null);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param actionIcon the action icon
     * @param token the token
     */
    public void show(String message, String actionMessage, int actionIcon, Parcelable token) {
        show(message, actionMessage, Style.DEFAULT, actionIcon, token);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param textColor the text color
     * @param actionIcon the action icon
     * @param token the token
     */
    public void show(String message, String actionMessage, int textColor, int actionIcon, Parcelable token) {
        show(message, actionMessage, textColor, actionIcon, token, MED_SNACK);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param actionStyle the action style
     * @param actionIcon the action icon
     * @param token the token
     */
    public void show(String message, String actionMessage, Style actionStyle, int actionIcon, Parcelable token) {
        show(message, actionMessage, actionStyle, actionIcon, token, MED_SNACK);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param duration the duration
     */
    public void show(String message, short duration) {
        show(message, null, duration);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param duration the duration
     */
    public void show(String message, String actionMessage, short duration) {
        show(message, actionMessage, Style.DEFAULT, duration);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param textColor the text color
     * @param duration the duration
     */
    public void show(String message, String actionMessage, int textColor, short duration) {
        show(message, actionMessage, textColor, 0, duration);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param actionStyle the action style
     * @param duration the duration
     */
    public void show(String message, String actionMessage, Style actionStyle, short duration) {
        show(message, actionMessage, actionStyle, 0, duration);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param textColor the text color
     * @param actionIcon the action icon
     * @param duration the duration
     */
    public void show(String message, String actionMessage, int textColor, int actionIcon, short duration) {
        show(message, actionMessage, textColor, actionIcon, null, duration);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param actionStyle the action style
     * @param actionIcon the action icon
     * @param duration the duration
     */
    public void show(String message, String actionMessage, Style actionStyle, int actionIcon, short duration) {
        show(message, actionMessage, actionStyle, actionIcon, null, duration);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param actionIcon the action icon
     * @param token the token
     * @param duration the duration
     */
    public void show(String message, String actionMessage, int actionIcon, Parcelable token, short duration) {
        show(message, actionMessage, Style.DEFAULT, actionIcon, token, duration);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param textColor the text color
     * @param actionIcon the action icon
     * @param token the token
     * @param duration the duration
     */
    public void show(String message, String actionMessage, int textColor, int actionIcon, Parcelable token, short duration) {
        int color = mContext.getResources().getColor(textColor);
        Snack m = new Snack(message, (actionMessage != null ? actionMessage.toUpperCase() : null),
                actionIcon, token, duration, color);
    }

    /**
     * Show void.
     *
     * @param message the message
     * @param actionMessage the action message
     * @param style the style
     * @param actionIcon the action icon
     * @param token the token
     * @param duration the duration
     */
    public void show(String message, String actionMessage, Style style, int actionIcon, Parcelable token, short duration) {
        Snack m = new Snack(message, (actionMessage != null ? actionMessage.toUpperCase() : null), actionIcon, token, duration, style);
        if (isShowing()) {
            mSnacks.push(m);
        } else {
            show(m);
        }
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        mContainer.measure(View.MeasureSpec.makeMeasureSpec(mParentView.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(mParentView.getHeight(), View.MeasureSpec.AT_MOST));
        return mContainer.getMeasuredHeight();
    }

    /**
     * Gets container view.
     *
     * @return the container view
     */
    public View getContainerView() {
        return mContainer;
    }

    private ColorStateList getActionTextColor(Style style) {
        switch (style){
            case ALERT:
                return mContext.getResources().getColorStateList(R.color.sb__button_text_color_red);
            case INFO:
                return mContext.getResources().getColorStateList(R.color.sb__button_text_color_yellow);
            case CONFIRM:
                return mContext.getResources().getColorStateList(R.color.sb__button_text_color_green);
            case DEFAULT:
                return mContext.getResources().getColorStateList(R.color.sb__default_button_text_color);
            default:
                return mContext.getResources().getColorStateList(R.color.sb__default_button_text_color);
        }
    }

    private void show(Snack message) {
        show(message, false);
    }

    private void show(Snack message, boolean immediately) {
        mShowing = true;
        mContainer.setVisibility(View.VISIBLE);
        sendOnShow();
        mCurrentSnack = message;
        mSnackMsg.setText(message.mMessage);
        if (message.mActionMessage != null) {
            mSnackMsg.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            mSnackBtn.setVisibility(View.VISIBLE);
            mSnackBtn.setText(message.mActionMessage);
            //mSnackBtn.setTextColor(message.mBtnTextColor);
            mSnackBtn.setCompoundDrawablesWithIntrinsicBounds(message.mActionIcon, 0, 0, 0);
        } else {
            mSnackMsg.setGravity(Gravity.CENTER);
            mSnackBtn.setVisibility(View.GONE);
        }

        if (message.mBtnTextColor > 0){
            mSnackBtn.setTextColor(message.mBtnTextColor);
        } else {
            mSnackBtn.setTextColor(getActionTextColor(message.mStyle));
        }

        System.out.println("immediately " + immediately);

        if (immediately) {
            mInAnimationSet.setDuration(0);
        } else {
            mInAnimationSet.setDuration(ANIMATION_DURATION);
        }
        mContainer.startAnimation(mInAnimationSet);

        if (message.mDuration > 0) {
            mHandler.postDelayed(mHideRunnable, message.mDuration);
        }

        mContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        int[] location = new int[2];
                        mContainer.getLocationInWindow(location);
                        if (y > mPreviousY) {
                            float dy = y - mPreviousY;
                            mContainer.offsetTopAndBottom(Math.round(4 * dy));

                            if ((mContainer.getResources().getDisplayMetrics().heightPixels - location[1]) - 100 <= 0) {
                                mHandler.removeCallbacks(mHideRunnable);
                                mContainer.startAnimation(mOutAnimationSet);

                                if (!mSnacks.empty()) {
                                    mSnacks.clear();
                                }
                            }
                        }
                }

                mPreviousY = y;

                return true;
            }
        });
    }

    private final View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mClickListener != null && mCurrentSnack != null) {
                mClickListener.onMessageClick(mCurrentSnack.mToken);
                mCurrentSnack = null;
                mHandler.removeCallbacks(mHideRunnable);
                mHideRunnable.run();
            }
        }
    };

    /**
     * Sets on click listener.
     *
     * @param listener the listener
     */
    public void setOnClickListener(OnMessageClickListener listener) {
        mClickListener = listener;
    }

    /**
     * Clear void.
     *
     * @param animate the animate
     */
    public void clear(boolean animate) {
        mSnacks.clear();
        if (animate) mHideRunnable.run();
    }

    /**
     * Clear void.
     */
    public void clear() {
        clear(true);
    }

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mContainer.startAnimation(mOutAnimationSet);
        }
    };


    /**
     * On restore instance state.
     *
     * @param state the state
     */
    public void onRestoreInstanceState(Bundle state) {
        Snack currentSnack = state.getParcelable(SAVED_CURR_MSG);
        if (currentSnack != null) {
            show(currentSnack, true);
            Parcelable[] messages = state.getParcelableArray(SAVED_MSGS);
            for (Parcelable p : messages) {
                mSnacks.push((Snack) p);
            }
        }
    }

    /**
     * On save instance state.
     *
     * @return the bundle
     */
    public Bundle onSaveInstanceState() {
        Bundle b = new Bundle();

        b.putParcelable(SAVED_CURR_MSG, mCurrentSnack);

        final int count = mSnacks.size();
        final Snack[] snacks = new Snack[count];
        int i = 0;
        for (Snack snack : mSnacks) {
            snacks[i++] = snack;
        }

        b.putParcelableArray(SAVED_MSGS, snacks);

        return b;
    }

    private boolean isShowing() {
        return mShowing;
    }

    /**
     * The enum Style.
     */
    public enum Style{
        /**
         * The DEFAULT.
         */
        DEFAULT,
        /**
         * The ALERT.
         */
        ALERT,
        /**
         * The CONFIRM.
         */
        CONFIRM,
        /**
         * The INFO.
         */
        INFO
    }

    private static class Snack implements Parcelable {

        /**
         * The M message.
         */
        final String mMessage;

        /**
         * The M action message.
         */
        final String mActionMessage;

        /**
         * The M action icon.
         */
        final int mActionIcon;

        /**
         * The M token.
         */
        final Parcelable mToken;

        /**
         * The M duration.
         */
        final short mDuration;

        /**
         * The M btn text color.
         */
        final int mBtnTextColor;

        /**
         * The M style.
         */
        final Style mStyle;

        /**
         * Instantiates a new Snack.
         *
         * @param message the message
         * @param actionMessage the action message
         * @param actionIcon the action icon
         * @param token the token
         * @param duration the duration
         * @param textColor the text color
         */
        public Snack(String message, String actionMessage, int actionIcon,
                     Parcelable token, short duration, int textColor) {
            mMessage = message;
            mActionMessage = actionMessage;
            mActionIcon = actionIcon;
            mToken = token;
            mDuration = duration;
            mBtnTextColor = textColor;
            mStyle = Style.DEFAULT;
        }

        /**
         * Instantiates a new Snack.
         *
         * @param message the message
         * @param actionMessage the action message
         * @param actionIcon the action icon
         * @param token the token
         * @param duration the duration
         * @param style the style
         */
        public Snack(String message, String actionMessage, int actionIcon,
                     Parcelable token, short duration, Style style) {
            mMessage = message;
            mActionMessage = actionMessage;
            mActionIcon = actionIcon;
            mToken = token;
            mDuration = duration;
            mStyle = style;
            mBtnTextColor = 0;
        }

        /**
         * Instantiates a new Snack.
         *
         * @param p the p
         */
// reads data from parcel
        public Snack(Parcel p) {
            mMessage = p.readString();
            mActionMessage = p.readString();
            mActionIcon = p.readInt();
            mToken = p.readParcelable(p.getClass().getClassLoader());
            mDuration = (short) p.readInt();
            mBtnTextColor = p.readInt();
            mStyle = Style.valueOf(p.readString());
        }

        // writes data to parcel
        public void writeToParcel(Parcel out, int flags) {
            out.writeString(mMessage);
            out.writeString(mActionMessage);
            out.writeInt(mActionIcon);
            out.writeParcelable(mToken, 0);
            out.writeInt((int) mDuration);
            out.writeInt(mBtnTextColor);
            out.writeString(mStyle.name());
        }

        public int describeContents() {
            return 0;
        }

        /**
         * The constant CREATOR.
         */
// creates snack array
        public static final Parcelable.Creator<Snack> CREATOR = new Parcelable.Creator<Snack>() {
            public Snack createFromParcel(Parcel in) {
                return new Snack(in);
            }

            public Snack[] newArray(int size) {
                return new Snack[size];
            }
        };
    }

    private void sendOnHide()
    {
        if (mVisibilityChangeListener != null) {
            mVisibilityChangeListener.onHide(mSnacks.size());
        }
    }

    private void sendOnShow()
    {
        if (mVisibilityChangeListener != null) {
            mVisibilityChangeListener.onShow(mSnacks.size());
        }
    }

}
