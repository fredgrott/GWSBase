package com.grottworkshop.gwsbaselibrary.widget.swipelayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by fgrott on 10/7/2014.
 * @param <T>  the type parameter
 */
public abstract class ArraySwipeAdapter<T> extends ArrayAdapter implements SwipeItemMangerInterface,SwipeAdapterInterface {

    private SwipeItemMangerImpl mItemManger = new SwipeItemMangerImpl(this);
    {}

    /**
     * Instantiates a new Array swipe adapter.
     *
     * @param context the context
     * @param resource the resource
     */
    public ArraySwipeAdapter(Context context, int resource) {
        super(context, resource);
    }

    /**
     * Instantiates a new Array swipe adapter.
     *
     * @param context the context
     * @param resource the resource
     * @param textViewResourceId the text view resource id
     */
    public ArraySwipeAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    /**
     * Instantiates a new Array swipe adapter.
     *
     * @param context the context
     * @param resource the resource
     * @param objects the objects
     */
    public ArraySwipeAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    /**
     * Instantiates a new Array swipe adapter.
     *
     * @param context the context
     * @param resource the resource
     * @param textViewResourceId the text view resource id
     * @param objects the objects
     */
    public ArraySwipeAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    /**
     * Instantiates a new Array swipe adapter.
     *
     * @param context the context
     * @param resource the resource
     * @param objects the objects
     */
    public ArraySwipeAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    /**
     * Instantiates a new Array swipe adapter.
     *
     * @param context the context
     * @param resource the resource
     * @param textViewResourceId the text view resource id
     * @param objects the objects
     */
    public ArraySwipeAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean convertViewIsNull = convertView == null;
        View v = super.getView(position, convertView, parent);
        if(convertViewIsNull){
            mItemManger.initialize(v, position);
        }else{
            mItemManger.updateConvertView(v, position);
        }
        return v;
    }

    @Override
    public void openItem(int position) {
        mItemManger.openItem(position);
    }

    @Override
    public void closeItem(int position) {
        mItemManger.closeItem(position);
    }

    @Override
    public void closeAllExcept(SwipeLayout layout) {
        mItemManger.closeAllExcept(layout);
    }

    @Override
    public List<Integer> getOpenItems() {
        return mItemManger.getOpenItems();
    }

    @Override
    public List<SwipeLayout> getOpenLayouts() {
        return mItemManger.getOpenLayouts();
    }

    @Override
    public void removeShownLayouts(SwipeLayout layout) {
        mItemManger.removeShownLayouts(layout);
    }

    @Override
    public boolean isOpen(int position) {
        return mItemManger.isOpen(position);
    }

    @Override
    public SwipeItemMangerImpl.Mode getMode() {
        return mItemManger.getMode();
    }

    @Override
    public void setMode(SwipeItemMangerImpl.Mode mode) {
        mItemManger.setMode(mode);
    }
}
