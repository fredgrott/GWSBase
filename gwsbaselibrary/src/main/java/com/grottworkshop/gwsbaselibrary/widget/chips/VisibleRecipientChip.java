package com.grottworkshop.gwsbaselibrary.widget.chips;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

/**
 * Created by fgrott on 10/8/2014.
 */
public class VisibleRecipientChip extends ImageSpan implements DrawableRecipientChip {
    private final SimpleRecipientChip mDelegate;

    public VisibleRecipientChip(final Drawable drawable, final RecipientEntry entry) {
        this(drawable, entry, DynamicDrawableSpan.ALIGN_BOTTOM);
    }

    public VisibleRecipientChip(final Drawable drawable, final RecipientEntry entry,
                                final int verticalAlignment) {
        super(drawable, verticalAlignment);

        mDelegate = new SimpleRecipientChip(entry);
    }

    @Override
    public void setSelected(final boolean selected) {
        mDelegate.setSelected(selected);
    }

    @Override
    public boolean isSelected() {
        return mDelegate.isSelected();
    }

    @Override
    public CharSequence getDisplay() {
        return mDelegate.getDisplay();
    }

    @Override
    public CharSequence getValue() {
        return mDelegate.getValue();
    }

    @Override
    public long getContactId() {
        return mDelegate.getContactId();
    }

    @Override
    public Long getDirectoryId() {
        return mDelegate.getDirectoryId();
    }

    @Override
    public String getLookupKey() {
        return mDelegate.getLookupKey();
    }

    @Override
    public long getDataId() {
        return mDelegate.getDataId();
    }

    @Override
    public RecipientEntry getEntry() {
        return mDelegate.getEntry();
    }

    @Override
    public void setOriginalText(final String text) {
        mDelegate.setOriginalText(text);
    }

    @Override
    public CharSequence getOriginalText() {
        return mDelegate.getOriginalText();
    }

    @Override
    public Rect getBounds() {
        return getDrawable().getBounds();
    }

    @Override
    public void draw(final Canvas canvas) {
        getDrawable().draw(canvas);
    }

    @Override
    public String toString() {
        return mDelegate.toString();
    }
}
