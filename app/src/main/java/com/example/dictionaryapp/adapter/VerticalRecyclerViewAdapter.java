package com.example.dictionaryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.model.CategoryTitle;
import com.example.dictionaryapp.model.ItemCategory;

import java.util.List;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalViewHolder> {

    private Context mContext;
    private List<CategoryTitle> allCategoryList;

    public VerticalRecyclerViewAdapter(Context mContext, List<CategoryTitle> allCategoryList) {
        this.mContext = mContext;
        this.allCategoryList = allCategoryList;
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VerticalViewHolder(LayoutInflater.from(mContext).inflate(R.layout.vertical_recycler_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewAdapter.VerticalViewHolder holder, int position) {
        holder.txtvCategoryTitle.setText(allCategoryList.get(position).getCategoryTitle());

        ItemCategoryAdapter itemCategoryAdapter = new ItemCategoryAdapter(mContext, allCategoryList.get(position).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.mRecyclerItem.setLayoutManager(linearLayoutManager);
        holder.mRecyclerItem.setAdapter(itemCategoryAdapter);
    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class VerticalViewHolder extends RecyclerView.ViewHolder {

        TextView txtvCategoryTitle;
        RecyclerView mRecyclerItem;

        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);

            txtvCategoryTitle = itemView.findViewById(R.id.txtvTitle);
            mRecyclerItem = itemView.findViewById(R.id.item_category);
        }
    }
}
