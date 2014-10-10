package com.grottworkshop.gwsbaselibrary.widget.swipelayout;

import java.util.List;

/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public interface SwipeItemMangerInterface {

    /**
     * Open item.
     *
     * @param position the position
     */
    public void openItem(int position);

    /**
     * Close item.
     *
     * @param position the position
     */
    public void closeItem(int position);

    /**
     * Close all except.
     *
     * @param layout the layout
     */
    public void closeAllExcept(SwipeLayout layout);

    /**
     * Gets open items.
     *
     * @return the open items
     */
    public List<Integer> getOpenItems();

    /**
     * Gets open layouts.
     *
     * @return the open layouts
     */
    public List<SwipeLayout> getOpenLayouts();

    /**
     * Remove shown layouts.
     *
     * @param layout the layout
     */
    public void removeShownLayouts(SwipeLayout layout);

    /**
     * Is open.
     *
     * @param position the position
     * @return the boolean
     */
    public boolean isOpen(int position);

    /**
     * Gets mode.
     *
     * @return the mode
     */
    public SwipeItemMangerImpl.Mode getMode();

    /**
     * Sets mode.
     *
     * @param mode the mode
     */
    public void setMode(SwipeItemMangerImpl.Mode mode);
}
