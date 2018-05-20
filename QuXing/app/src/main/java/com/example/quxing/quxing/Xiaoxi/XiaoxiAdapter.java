package com.example.quxing.quxing.Xiaoxi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.model.ItemInfoBean;

import java.util.List;

/**
 * Created by 陈若韬 on 2018/4/17.
 */

public class XiaoxiAdapter extends ArrayAdapter<ItemInfoBean> {
    private int resourceId;
    private Context context;

    public XiaoxiAdapter(Context context, int textViewResourceId, List<ItemInfoBean> object) {
        super(context, textViewResourceId, object);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        Item item = getItem(position);
        ItemInfoBean itemInfoBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.xiaoxi_image);
        TextView itemname = (TextView) view.findViewById(R.id.xiaoxi_name);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.imageButton_talking);
        Glide.with(context)
                .load("http://192.168.43.34:8082/getLocalImage/itemid/" + itemInfoBean.getItemid())
                .placeholder(R.drawable.ic_bg_collection)
                .into(imageView);

        itemname.setText(itemInfoBean.getItemname());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemInfoBean item = getItem(position);
                Intent intent = new Intent(context, Xiaoxi_DetailsActivity.class);
                intent.putExtra("itemname", item.getItemname());
                context.startActivity(intent);
            }
        });
        return view;
    }
}

//    private int resourceId;
//
//    public XiaoxiAdapter(Context context, int textViewResourceId, List<Xiaoxi> objects) {
//        super(context, textViewResourceId, objects);
//        resourceId = textViewResourceId;
//    }
//
//    @Override
//    public View getView(int position, View convertVIew, ViewGroup parent) {
//        Xiaoxi xiaoxi = getItem(position);//获取当前项的item实例
//        View view;
//        XiaoxiAdapter.ViewHolder viewHolder;
//        if (convertVIew == null) {
//            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//            viewHolder = new XiaoxiAdapter.ViewHolder();
//            viewHolder.xiaoxiImage = (ImageView) view.findViewById(R.id.xiaoxi_image);
//            viewHolder.xiaoxiName = (TextView) view.findViewById(R.id.xiaoxi_name);
//            view.setTag(viewHolder);
//        } else {
//            view = convertVIew;
//            viewHolder = (XiaoxiAdapter.ViewHolder) view.getTag();
//        }
//        viewHolder.xiaoxiImage.setImageResource(xiaoxi.getImageId());
//        viewHolder.xiaoxiName.setText(xiaoxi.getName());
//        return view;
//    }
//
//    class ViewHolder {
//        ImageView xiaoxiImage;
//        TextView xiaoxiName;
//    }


