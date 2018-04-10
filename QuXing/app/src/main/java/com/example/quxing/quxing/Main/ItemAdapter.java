package com.example.quxing.quxing.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quxing.quxing.R;

import java.util.List;

/**
 * Created by 陈若韬 on 2018/3/27.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private List<Item> mItem;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView itemImage;
        TextView itemName;

        public ViewHolder(View view){
            super(view);
            itemView = view;
            itemImage=(ImageView)view.findViewById(R.id.item_image);
            itemName=(TextView)view.findViewById(R.id.item_name);

        }
    }

    public ItemAdapter(List<Item>itemList){
        mItem = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder .getAdapterPosition();
                Item item = mItem.get(position);
//                Intent intent = new Intent(ItemAdapter.this, ItemActivity.class);
//                startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Item item = mItem.get(position);
        holder.itemImage.setImageResource(item.getImageId());
        holder.itemName.setText(item.getName());
    }

    @Override
    public int getItemCount(){
        return mItem.size();
    }
}
