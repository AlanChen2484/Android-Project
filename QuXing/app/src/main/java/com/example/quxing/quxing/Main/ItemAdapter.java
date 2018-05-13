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
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.model.ItemInfoBean;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by 陈若韬 on 2018/3/27.
 */

public class ItemAdapter extends ArrayAdapter<ItemInfoBean> {

    private int resourceId;
    private Context context;

    public ItemAdapter(Context context, int textViewResourceId, List<ItemInfoBean> object) {
        super(context, textViewResourceId, object);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Item item = getItem(position);
        ItemInfoBean itemInfoBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
        TextView itemname = (TextView) view.findViewById(R.id.item_name);
        TextView address = (TextView) view.findViewById(R.id.item_address);
        TextView itemtime = (TextView) view.findViewById(R.id.item_date);
        TextView money = (TextView) view.findViewById(R.id.item_money);
//        imageView.setImageResource(news.getPictureid());

        Glide.with(context)
                .load("http://192.168.43.34:8082/getLocalImage/itemid/" + itemInfoBean.getItemid())
                .placeholder(R.drawable.ic_bg_collection)
                .into(imageView);

        itemname.setText(itemInfoBean.getItemname());
        address.setText(itemInfoBean.getAddress());
        itemtime.setText(itemInfoBean.getItemtime());
        money.setText(String.valueOf(itemInfoBean.getMoney()));

        return view;
    }

}

//    private int resourceId;
//
//    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
//        super(context, textViewResourceId, objects);
//        resourceId = textViewResourceId;
//    }
//
//    @Override
//    public View getView(int position, View convertVIew, ViewGroup parent) {
//        Item item = getItem(position);//获取当前项的item实例
//        View view;
//        ViewHolder viewHolder;
//        if (convertVIew == null) {
//            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//            viewHolder = new ViewHolder();
//            viewHolder.itemImage = (ImageView) view.findViewById(R.id.item_image);
//            viewHolder.itemName = (TextView) view.findViewById(R.id.item_name);
//            view.setTag(viewHolder);
//        } else {
//            view = convertVIew;
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        viewHolder.itemImage.setImageResource(item.getImageId());
//        viewHolder.itemName.setText(item.getName());
//        return view;
//    }
//
//    class ViewHolder {
//        ImageView itemImage;
//        TextView itemName;