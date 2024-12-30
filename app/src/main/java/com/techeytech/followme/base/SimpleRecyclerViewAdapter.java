package com.techeytech.followme.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleRecyclerViewAdapter<M, B extends ViewDataBinding> extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.SimpleViewHolder<B>> {

    @LayoutRes
    private int layoutResId;
    private int modelVariableId;
    private SimpleCallback<M> callback;
    private List<M> dataList = new ArrayList<>();


    public void removeItem(int i) {
        try {
            if (i != -1) {
                dataList.remove(i);
                notifyItemRemoved(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void set(int i, M scanResult) {
        if (scanResult == null)
            return;
        dataList.add(i, scanResult);
        notifyItemChanged(i);
    }


    public interface SimpleCallback<M> {
        void onClick(View v, M m);
    }


    public SimpleRecyclerViewAdapter(@LayoutRes int layoutResId, int modelVariableId, SimpleCallback<M> callback) {
        this.layoutResId = layoutResId;
        this.modelVariableId = modelVariableId;
        this.callback = callback;
    }


    @NonNull
    @Override
    public SimpleViewHolder<B> onCreateViewHolder(ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutResId, parent, false);
        binding.setVariable(BR.callback, callback);
        return new SimpleViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        holder.binding.setVariable(modelVariableId, dataList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setList(@Nullable List<M> newDataList) {
        if (newDataList == null) {
            newDataList = Collections.emptyList();
        }
        dataList.clear();
        dataList.addAll(newDataList);
        notifyDataSetChanged();

    }

    public List<M> getList() {
        return dataList;

    }

    public void addToList(@Nullable List<M> newDataList) {
        if (newDataList == null) {
            newDataList = Collections.emptyList();
        }

        int positionStart = dataList.size();
        int itemCount = newDataList.size();
        dataList.addAll(newDataList);
        notifyItemRangeInserted(positionStart, itemCount);

    }

    public void clearList() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public void addData(M data) {
        int positionStart = dataList.size();
        dataList.add(data);
        notifyItemInserted(positionStart);
    }

    /**
     * Simple view holder for this adapter
     *
     * @param <S>
     */
    static class SimpleViewHolder<S extends ViewDataBinding> extends RecyclerView.ViewHolder {
        final S binding;

        public SimpleViewHolder(S binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
