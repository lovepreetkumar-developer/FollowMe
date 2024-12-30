package com.techeytech.followme.utils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by jatin on 6/5/2018.
 */

public class VerticalPagination extends RecyclerView.OnScrollListener {
    private int totalItemCount;
    private int visibleItemCount;
    private int pastVisiblesItems;
    private boolean loading;
    private LinearLayoutManager linearLayoutManager;
    private final int VISIBLE_THRESHOLD;
    private VerticalScrollListener onEndLessScrollListener;
    private int firstVisibleItem;
    private int previousTotal;

    public VerticalPagination(LinearLayoutManager linearLayoutManager, int VISIBLE_THRESHOLD) {
        this.linearLayoutManager = linearLayoutManager;
        this.VISIBLE_THRESHOLD = VISIBLE_THRESHOLD;
        loading = false;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) //check for scroll down
        {
            visibleItemCount = linearLayoutManager.getChildCount();
            totalItemCount = linearLayoutManager.getItemCount();
            pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

            if (!loading) {
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount - VISIBLE_THRESHOLD) {
                    loading = true;
                    if (onEndLessScrollListener != null)
                        onEndLessScrollListener.onLoadMore();
                }
            }
        }
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setListener(VerticalScrollListener onEndLessScrollListener) {
        this.onEndLessScrollListener = onEndLessScrollListener;
    }

    public interface VerticalScrollListener {
        void onLoadMore();
    }
}