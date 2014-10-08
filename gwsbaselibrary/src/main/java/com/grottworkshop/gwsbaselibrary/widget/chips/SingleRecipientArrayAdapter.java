package com.grottworkshop.gwsbaselibrary.widget.chips;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by fgrott on 10/8/2014.
 */
class SingleRecipientArrayAdapter extends ArrayAdapter<RecipientEntry> {
    private final DropdownChipLayouter mDropdownChipLayouter;

    public SingleRecipientArrayAdapter(Context context, RecipientEntry entry,
                                       DropdownChipLayouter dropdownChipLayouter) {
        super(context, dropdownChipLayouter.getAlternateItemLayoutResId(), new RecipientEntry[] {
                entry
        });

        mDropdownChipLayouter = dropdownChipLayouter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mDropdownChipLayouter.bindView(convertView, parent, getItem(position), position,
                DropdownChipLayouter.AdapterType.SINGLE_RECIPIENT, null);
    }
}
