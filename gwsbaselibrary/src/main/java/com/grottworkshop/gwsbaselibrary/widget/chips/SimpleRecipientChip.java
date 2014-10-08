package com.grottworkshop.gwsbaselibrary.widget.chips;

import android.text.TextUtils;

/**
 * Created by fgrott on 10/8/2014.
 */
class SimpleRecipientChip implements BaseRecipientChip {
    private final CharSequence mDisplay;

    private final CharSequence mValue;

    private final long mContactId;

    private final Long mDirectoryId;

    private final String mLookupKey;

    private final long mDataId;

    private final RecipientEntry mEntry;

    private boolean mSelected = false;

    private CharSequence mOriginalText;

    public SimpleRecipientChip(final RecipientEntry entry) {
        mDisplay = entry.getDisplayName();
        mValue = entry.getDestination().trim();
        mContactId = entry.getContactId();
        mDirectoryId = entry.getDirectoryId();
        mLookupKey = entry.getLookupKey();
        mDataId = entry.getDataId();
        mEntry = entry;
    }

    @Override
    public void setSelected(final boolean selected) {
        mSelected = selected;
    }

    @Override
    public boolean isSelected() {
        return mSelected;
    }

    @Override
    public CharSequence getDisplay() {
        return mDisplay;
    }

    @Override
    public CharSequence getValue() {
        return mValue;
    }

    @Override
    public long getContactId() {
        return mContactId;
    }

    @Override
    public Long getDirectoryId() {
        return mDirectoryId;
    }

    @Override
    public String getLookupKey() {
        return mLookupKey;
    }

    @Override
    public long getDataId() {
        return mDataId;
    }

    @Override
    public RecipientEntry getEntry() {
        return mEntry;
    }

    @Override
    public void setOriginalText(final String text) {
        if (TextUtils.isEmpty(text)) {
            mOriginalText = text;
        } else {
            mOriginalText = text.trim();
        }
    }

    @Override
    public CharSequence getOriginalText() {
        return !TextUtils.isEmpty(mOriginalText) ? mOriginalText : mEntry.getDestination();
    }

    @Override
    public String toString() {
        return mDisplay + " <" + mValue + ">";
    }
}