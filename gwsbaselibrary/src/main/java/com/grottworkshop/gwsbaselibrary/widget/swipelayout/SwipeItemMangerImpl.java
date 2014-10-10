package com.grottworkshop.gwsbaselibrary.widget.swipelayout;

import android.view.View;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 * Created by fgrott on 10/7/2014.
 */
public class SwipeItemMangerImpl implements SwipeItemMangerInterface {

    private Mode mode = Mode.Single;
    /**
     * The INVALID _ pOSITION.
     */
    public final int INVALID_POSITION = -1;

    /**
     * The M open position.
     */
    protected int mOpenPosition = INVALID_POSITION;

    /**
     * The M open positions.
     */
    protected Set<Integer> mOpenPositions = new HashSet<Integer>();
    /**
     * The M shown layouts.
     */
    protected Set<SwipeLayout> mShownLayouts = new HashSet<SwipeLayout>();

    /**
     * The M adapter.
     */
    protected BaseAdapter mAdapter;

    /**
     * Instantiates a new Swipe item manger impl.
     *
     * @param adapter the adapter
     */
    public SwipeItemMangerImpl(BaseAdapter adapter) {
        if(adapter == null)
            throw new IllegalArgumentException("Adapter can not be null");

        if(!(adapter instanceof SwipeItemMangerInterface))
            throw new IllegalArgumentException("adapter should implement the SwipeAdapterInterface");

        this.mAdapter = adapter;
    }

    /**
     * The enum Mode.
     */
    public enum Mode{
        /**
         * The Single.
         */
        Single, /**
         * The Multiple.
         */
        Multiple
    };

    public Mode getMode(){
        return mode;
    }

    public void setMode(Mode mode){
        this.mode = mode;
        mOpenPositions.clear();
        mShownLayouts.clear();
        mOpenPosition = INVALID_POSITION;
    }

    /**
     * Initialize void.
     *
     * @param target the target
     * @param position the position
     */
    public void initialize(View target, int position) {
        int resId = getSwipeLayoutId(position);

        OnLayoutListener onLayoutListener = new OnLayoutListener(position);
        SwipeLayout swipeLayout = (SwipeLayout)target.findViewById(resId);
        if(swipeLayout == null)
            throw new IllegalStateException("can not find SwipeLayout in target view");

        SwipeMemory swipeMemory = new SwipeMemory(position);
        swipeLayout.addSwipeListener(swipeMemory);
        swipeLayout.addOnLayoutListener(onLayoutListener);
        swipeLayout.setTag(resId, new ValueBox(position, swipeMemory, onLayoutListener));

        mShownLayouts.add(swipeLayout);
    }

    /**
     * Update convert view.
     *
     * @param target the target
     * @param position the position
     */
    public void updateConvertView(View target, int position) {
        int resId = getSwipeLayoutId(position);

        SwipeLayout swipeLayout = (SwipeLayout)target.findViewById(resId);
        if(swipeLayout == null)
            throw new IllegalStateException("can not find SwipeLayout in target view");

        ValueBox valueBox = (ValueBox)swipeLayout.getTag(resId);
        valueBox.swipeMemory.setPosition(position);
        valueBox.onLayoutListener.setPosition(position);
        valueBox.position = position;
    }

    private int getSwipeLayoutId(int position){
        return ((SwipeAdapterInterface)(mAdapter)).getSwipeLayoutResourceId(position);
    }

    @Override
    public void openItem(int position) {
        if(mode == Mode.Multiple){
            if(!mOpenPositions.contains(position))
                mOpenPositions.add(position);
        }else{
            mOpenPosition = position;
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void closeItem(int position) {
        if(mode == Mode.Multiple){
            mOpenPositions.remove(position);
        }else{
            if(mOpenPosition == position)
                mOpenPosition = INVALID_POSITION;
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void closeAllExcept(SwipeLayout layout) {
        for(SwipeLayout s : mShownLayouts){
            if(s != layout)
                s.close();
        }
    }

    @Override
    public void removeShownLayouts(SwipeLayout layout) {
        mShownLayouts.remove(layout);
    }

    @Override
    public List<Integer> getOpenItems() {
        if(mode == Mode.Multiple){
            return new ArrayList<Integer>(mOpenPositions);
        }else{
            return Arrays.asList(mOpenPosition);
        }
    }

    @Override
    public List<SwipeLayout> getOpenLayouts() {
        return new ArrayList<SwipeLayout>(mShownLayouts);
    }

    @Override
    public boolean isOpen(int position) {
        if(mode == Mode.Multiple){
            return mOpenPositions.contains(position);
        }else{
            return mOpenPosition == position;
        }
    }

    /**
     * The type Value box.
     */
    class ValueBox {
        /**
         * The On layout listener.
         */
        OnLayoutListener onLayoutListener;
        /**
         * The Swipe memory.
         */
        SwipeMemory swipeMemory;
        /**
         * The Position.
         */
        int position;

        /**
         * Instantiates a new Value box.
         *
         * @param position the position
         * @param swipeMemory the swipe memory
         * @param onLayoutListener the on layout listener
         */
        ValueBox(int position, SwipeMemory swipeMemory, OnLayoutListener onLayoutListener) {
            this.swipeMemory = swipeMemory;
            this.onLayoutListener = onLayoutListener;
            this.position = position;
        }
    }

    /**
     * The type On layout listener.
     */
    class OnLayoutListener implements SwipeLayout.OnLayout{

        private int position;

        /**
         * Instantiates a new On layout listener.
         *
         * @param position the position
         */
        OnLayoutListener(int position) {
            this.position = position;
        }

        /**
         * Set position.
         *
         * @param position the position
         */
        public void setPosition(int position){
            this.position = position;
        }

        @Override
        public void onLayout(SwipeLayout v) {
            if(isOpen(position)){
                v.open(false, false);
            }else{
                v.close(false, false);
            }
        }

    }

    /**
     * The type Swipe memory.
     */
    class SwipeMemory extends SimpleSwipeListener {

        private int position;

        /**
         * Instantiates a new Swipe memory.
         *
         * @param position the position
         */
        SwipeMemory(int position) {
            this.position = position;
        }

        @Override
        public void onClose(SwipeLayout layout) {
            if(mode == Mode.Multiple){
                mOpenPositions.remove(position);
            }else{
                mOpenPosition = INVALID_POSITION;
            }
        }

        @Override
        public void onStartOpen(SwipeLayout layout) {
            if(mode == Mode.Single) {
                closeAllExcept(layout);
            }
        }

        @Override
        public void onOpen(SwipeLayout layout) {
            if (mode == Mode.Multiple)
                mOpenPositions.add(position);
            else {
                closeAllExcept(layout);
                mOpenPosition = position;
            }
        }

        /**
         * Set position.
         *
         * @param position the position
         */
        public void setPosition(int position){
            this.position = position;
        }
    }

}
