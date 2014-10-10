/*
 * Copyright 2013 Philip Schiffer
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.grottworkshop.gwsbaselibrary.app.licensesdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.webkit.WebView;

import com.grottworkshop.gwsbaselibrary.R;

import java.util.List;

/**
 *
 *
 * Created by fgrott on 10/8/2014.
 */
public class LicensesDialog {
    /**
     * The constant LICENSES_DIALOG_NOTICE.
     */
    public static final Notice LICENSES_DIALOG_NOTICE = new Notice("LicensesDialog", "http://psdev.de/LicensesDialog",
            "Copyright 2013 Philip Schiffer",
            new ApacheSoftwareLicense20());

    private final Context mContext;
    private final String mTitleText;
    private final String mLicensesText;
    private final String mCloseText;
    private final int mThemeResourceId;
    private final int mDividerColor;

    //
    private DialogInterface.OnDismissListener mOnDismissListener;

    private LicensesDialog(final Context context, final String licensesText, final String titleText, final String closeText, final int themeResourceId,
                           final int dividerColor) {
        mContext = context;
        mTitleText = titleText;
        mLicensesText = licensesText;
        mCloseText = closeText;
        mThemeResourceId = themeResourceId;
        mDividerColor = dividerColor;
    }

    /**
     * Sets on dismiss listener.
     *
     * @param onDismissListener the on dismiss listener
     * @return the on dismiss listener
     */
    public LicensesDialog setOnDismissListener(final DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
        return this;
    }

    /**
     * Create dialog.
     *
     * @return the dialog
     */
    public Dialog create() {
        //Get resources
        final WebView webView = new WebView(mContext);
        webView.loadDataWithBaseURL(null, mLicensesText, "text/html", "utf-8", null);
        final AlertDialog.Builder builder;
        if (mThemeResourceId != 0) {
            builder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, mThemeResourceId));
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle(mTitleText)
                .setView(webView)
                .setPositiveButton(mCloseText, new Dialog.OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int i) {
                        dialogInterface.dismiss();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(final DialogInterface dialog) {
                if (mOnDismissListener != null) {
                    mOnDismissListener.onDismiss(dialog);
                }
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                if (mDividerColor != 0) {
                    // Set title divider color
                    final int titleDividerId = mContext.getResources().getIdentifier("titleDivider", "id", "android");
                    final View titleDivider = dialog.findViewById(titleDividerId);
                    if (titleDivider != null) {
                        titleDivider.setBackgroundColor(mDividerColor);
                    }
                }
            }
        });
        return dialog;
    }

    /**
     * Show dialog.
     *
     * @return the dialog
     */
    public Dialog show() {
        final Dialog dialog = create();
        dialog.show();
        return dialog;
    }

    //

    private static Notices getNotices(final Context context, final int rawNoticesResourceId) {
        try {
            final Resources resources = context.getResources();
            if ("raw".equals(resources.getResourceTypeName(rawNoticesResourceId))) {
                final Notices notices = NoticesXmlParser.parse(resources.openRawResource(rawNoticesResourceId));
                return notices;
            } else {
                throw new IllegalStateException("not a raw resource");
            }
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getLicensesText(final Context context, final Notices notices, final boolean showFullLicenseText,
                                          final boolean includeOwnLicense, final String style) {
        try {
            if (includeOwnLicense) {
                final List<Notice> noticeList = notices.getNotices();
                noticeList.add(LICENSES_DIALOG_NOTICE);
            }
            return NoticesHtmlBuilder.create(context).setShowFullLicenseText(showFullLicenseText).setNotices(notices).setStyle(style).build();
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static Notices getSingleNoticeNotices(final Notice notice) {
        final Notices notices = new Notices();
        notices.addNotice(notice);
        return notices;
    }

    // Inner classes

    /**
     * The type Builder.
     */
    public static final class Builder {

        private final Context mContext;

        // Default values
        private String mTitleText;
        private String mCloseText;
        @Nullable
        private Integer mRawNoticesId;
        @Nullable
        private Notices mNotices;
        @Nullable
        private String mNoticesText;
        private String mNoticesStyle;
        private boolean mShowFullLicenseText;
        private boolean mIncludeOwnLicense;
        private int mThemeResourceId;
        private int mDividerColor;

        /**
         * Instantiates a new Builder.
         *
         * @param context the context
         */
        public Builder(final Context context) {
            mContext = context;
            mTitleText = context.getString(R.string.notices_title);
            mCloseText = context.getString(R.string.notices_close);
            mNoticesStyle = context.getString(R.string.notices_default_style);
            mShowFullLicenseText = false;
            mIncludeOwnLicense = false;
            mThemeResourceId = 0;
            mDividerColor = 0;
        }

        /**
         * Sets title.
         *
         * @param titleId the title id
         * @return the title
         */
        public Builder setTitle(final int titleId) {
            mTitleText = mContext.getString(titleId);
            return this;
        }

        /**
         * Sets title.
         *
         * @param title the title
         * @return the title
         */
        public Builder setTitle(final String title) {
            mTitleText = title;
            return this;
        }

        /**
         * Sets close text.
         *
         * @param closeId the close id
         * @return the close text
         */
        public Builder setCloseText(final int closeId) {
            mCloseText = mContext.getString(closeId);
            return this;
        }

        /**
         * Sets close text.
         *
         * @param closeText the close text
         * @return the close text
         */
        public Builder setCloseText(final String closeText) {
            mCloseText = closeText;
            return this;
        }

        /**
         * Sets notices.
         *
         * @param rawNoticesId the raw notices id
         * @return the notices
         */
        public Builder setNotices(final int rawNoticesId) {
            mRawNoticesId = rawNoticesId;
            mNotices = null;
            return this;
        }

        /**
         * Sets notices.
         *
         * @param notices the notices
         * @return the notices
         */
        public Builder setNotices(final Notices notices) {
            mNotices = notices;
            mRawNoticesId = null;
            return this;
        }

        /**
         * Sets notices.
         *
         * @param notice the notice
         * @return the notices
         */
        public Builder setNotices(final Notice notice) {
            return setNotices(getSingleNoticeNotices(notice));
        }

        /**
         * Sets notices.
         *
         * @param notices the notices
         * @return the notices
         */
        Builder setNotices(final String notices) {
            mNotices = null;
            mRawNoticesId = null;
            mNoticesText = notices;
            return this;
        }

        /**
         * Sets notices css style.
         *
         * @param cssStyleTextId the css style text id
         * @return the notices css style
         */
        public Builder setNoticesCssStyle(final int cssStyleTextId) {
            mNoticesStyle = mContext.getString(cssStyleTextId);
            return this;
        }

        /**
         * Sets notices css style.
         *
         * @param cssStyleText the css style text
         * @return the notices css style
         */
        public Builder setNoticesCssStyle(final String cssStyleText) {
            mNoticesStyle = cssStyleText;
            return this;
        }

        /**
         * Sets show full license text.
         *
         * @param showFullLicenseText the show full license text
         * @return the show full license text
         */
        public Builder setShowFullLicenseText(final boolean showFullLicenseText) {
            mShowFullLicenseText = showFullLicenseText;
            return this;
        }

        /**
         * Sets include own license.
         *
         * @param includeOwnLicense the include own license
         * @return the include own license
         */
        public Builder setIncludeOwnLicense(final boolean includeOwnLicense) {
            mIncludeOwnLicense = includeOwnLicense;
            return this;
        }

        /**
         * Sets theme resource id.
         *
         * @param themeResourceId the theme resource id
         * @return the theme resource id
         */
        public Builder setThemeResourceId(final int themeResourceId) {
            mThemeResourceId = themeResourceId;
            return this;
        }

        /**
         * Sets divider color.
         *
         * @param dividerColor the divider color
         * @return the divider color
         */
        public Builder setDividerColor(final int dividerColor) {
            mDividerColor = dividerColor;
            return this;
        }

        /**
         * Sets divider color id.
         *
         * @param dividerColorId the divider color id
         * @return the divider color id
         */
        public Builder setDividerColorId(final int dividerColorId) {
            mDividerColor = mContext.getResources().getColor(dividerColorId);
            return this;
        }

        /**
         * Build licenses dialog.
         *
         * @return the licenses dialog
         */
        public LicensesDialog build() {
            final String licensesText;
            if (mNotices != null) {
                licensesText = getLicensesText(mContext, mNotices, mShowFullLicenseText, mIncludeOwnLicense, mNoticesStyle);
            } else if (mRawNoticesId != null) {
                licensesText = getLicensesText(mContext, getNotices(mContext, mRawNoticesId), mShowFullLicenseText, mIncludeOwnLicense, mNoticesStyle);
            } else if (mNoticesText != null) {
                licensesText = mNoticesText;
            } else {
                throw new IllegalStateException("Notices have to be provided, see setNotices");
            }

            return new LicensesDialog(mContext, licensesText, mTitleText, mCloseText, mThemeResourceId, mDividerColor);
        }

    }
}
