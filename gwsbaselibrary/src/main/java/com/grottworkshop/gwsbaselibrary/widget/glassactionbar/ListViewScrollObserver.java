/*
 * Copyright (C) 2013 Manuel Peinado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grottworkshop.gwsbaselibrary.widget.glassactionbar;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;


/**
 *
 *
 * Created by fgrott on 10/9/2014.
 */
public class ListViewScrollObserver implements OnScrollListener {
    private OnListViewScrollListener listener;
    private int lastFirstVisibleItem;
    private int lastTop;
    private int scrollPosition;
    private int lastHeight;

    /**
     * The interface On list view scroll listener.
     */
    public interface OnListViewScrollListener {
        /**
         * On scroll up down changed.
         *
         * @param delta the delta
         * @param scrollPosition the scroll position
         * @param exact the exact
         */
        void onScrollUpDownChanged(int delta, int scrollPosition, boolean exact);

        /**
         * On scroll idle.
         */
        void onScrollIdle();
    }

    /**
     * Instantiates a new List view scroll observer.
     *
     * @param listView the list view
     */
    public ListViewScrollObserver(ListView listView) {
        listView.setOnScrollListener(this);
    }

    /**
     * Sets on scroll up and down listener.
     *
     * @param listener the listener
     */
    public void setOnScrollUpAndDownListener(OnListViewScrollListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View firstChild = view.getChildAt(0);
        if (firstChild == null) {
            return;
        }
        int top = firstChild.getTop();
        int height = firstChild.getHeight();
        int delta;
        int skipped = 0;
        if (lastFirstVisibleItem == firstVisibleItem) {
            delta = lastTop - top;
        } else if (firstVisibleItem > lastFirstVisibleItem) {
            skipped = firstVisibleItem - lastFirstVisibleItem - 1;
            delta = skipped * height + lastHeight + lastTop - top;
        } else {
            skipped = lastFirstVisibleItem - firstVisibleItem - 1;
            delta = skipped * -height + lastTop - (height + top);
        }
        boolean exact = skipped == 0;
        scrollPosition += -delta;
        if (listener != null) {
            listener.onScrollUpDownChanged(-delta, scrollPosition, exact);
        }
        lastFirstVisibleItem = firstVisibleItem;
        lastTop = top;
        lastHeight = firstChild.getHeight();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (listener != null && scrollState == SCROLL_STATE_IDLE) {
            listener.onScrollIdle();
        }
    }
}
