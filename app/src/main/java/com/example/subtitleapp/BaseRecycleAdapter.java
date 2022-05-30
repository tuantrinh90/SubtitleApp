package com.example.subtitleapp;

import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class BaseRecycleAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> listItems = new ArrayList<>();
    private boolean isLoading;
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;
    private int itemPerPage = 18;

    public void setLoadMore(RecyclerView recyclerView) {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    totalItemCount = linearLayoutManager.getItemCount();
                    if (totalItemCount < itemPerPage)
                        return;
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        isLoading = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    public void setDataList(List<T> items) {
        if (this.listItems != null)
            listItems.clear();
        addDataList(items);
    }

    private void addDataList(List<T> items) {
        if (this.listItems != null && items != null) {
            this.listItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void clearData(){
        if (this.listItems != null)
            listItems.clear();
    }

    public List<T> getListItems(){
        return listItems;
    }
}

