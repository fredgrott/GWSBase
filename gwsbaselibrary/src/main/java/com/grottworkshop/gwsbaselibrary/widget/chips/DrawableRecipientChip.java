package com.grottworkshop.gwsbaselibrary.widget.chips;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by fgrott on 10/8/2014.
 */
public interface DrawableRecipientChip extends BaseRecipientChip {
    /**
     * Get the bounds of the chip; may be 0,0 if it is not visibly rendered.
     */
    Rect getBounds();

    /**
     * Draw the chip.
     */
    void draw(Canvas canvas);
}
