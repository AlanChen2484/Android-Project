package com.example.quxing.quxing.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quxing.quxing.R;

import java.util.List;

/**
 * Created by 陈若韬 on 2018/3/27.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    private int resourceId;

    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertVIew, ViewGroup parent) {
        Item item = getItem(position);//获取当前项的item实例
        View view;
        ViewHolder viewHolder;
        if (convertVIew == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemImage = (ImageView) view.findViewById(R.id.item_image);
            viewHolder.itemName = (TextView) view.findViewById(R.id.item_name);
            view.setTag(viewHolder);
        } else {
            view = convertVIew;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.itemImage.setImageResource(item.getImageId());
        viewHolder.itemName.setText(item.getName());
        return view;
    }

    class ViewHolder {
        ImageView itemImage;
        TextView itemName;
    }
}
