package com.grottworkshop.gwsbaselibrary.widget.chips;

/**
 * Created by fgrott on 10/8/2014.
 */
interface BaseRecipientChip {

    /**
     * Set the selected state of the chip.
     */
    void setSelected(boolean selected);

    /**
     * Return true if the chip is selected.
     */
    boolean isSelected();

    /**
     * Get the text displayed in the chip.
     */
    CharSequence getDisplay();

    /**
     * Get the text value this chip represents.
     */
    CharSequence getValue();

    /**
     * Get the id of the contact associated with this chip.
     */
    long getContactId();

    /**
     * Get the directory id of the contact associated with this chip.
     */
    Long getDirectoryId();

    /**
     * Get the directory lookup key associated with this chip, or <code>null</code>.
     */
    String getLookupKey();

    /**
     * Get the id of the data associated with this chip.
     */
    long getDataId();

    /**
     * Get associated RecipientEntry.
     */
    RecipientEntry getEntry();

    /**
     * Set the text in the edittextview originally associated with this chip
     * before any reverse lookups.
     */
    void setOriginalText(String text);

    /**
     * Set the text in the edittextview originally associated with this chip
     * before any reverse lookups.
     */
    CharSequence getOriginalText();
}
