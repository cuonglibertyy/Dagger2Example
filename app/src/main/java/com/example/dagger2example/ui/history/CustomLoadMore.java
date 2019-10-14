package com.example.dagger2example.ui.history;

import com.example.dagger2example.R;

class CustomLoadMore extends com.chad.library.adapter.base.loadmore.LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.item_history_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.item_history_load_more_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.item_history_load_more_fail;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
