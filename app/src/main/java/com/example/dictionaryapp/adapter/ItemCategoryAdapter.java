package com.example.dictionaryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.model.ItemCategory;

import java.util.List;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ItemCateViewHolder> {

    private Context mContext;
    private List<ItemCategory> itemCategoryList;

    public ItemCategoryAdapter(Context mContext, List<ItemCategory> itemCategoryList) {
        this.mContext = mContext;
        this.itemCategoryList = itemCategoryList;
    }

    @NonNull
    @Override
    public ItemCateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemCateViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_song, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoryAdapter.ItemCateViewHolder holder, int position) {
        holder.txtvItemTitle.setText(itemCategoryList.get(position).getTilte());
        Glide.with(mContext).load(itemCategoryList.get(position).getResource()).into(holder.imgItemCate);
    }

    @Override
    public int getItemCount() {
        return itemCategoryList.size();
    }

    public class ItemCateViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgItemCate;
        private TextView txtvItemTitle;

        public ItemCateViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItemCate = itemView.findViewById(R.id.img_list_song_item);
            txtvItemTitle = itemView.findViewById(R.id.txtvListSongItem);
        }
    }

}
