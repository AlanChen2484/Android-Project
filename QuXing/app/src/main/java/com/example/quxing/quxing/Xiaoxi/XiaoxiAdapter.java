package com.example.quxing.quxing.Xiaoxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quxing.quxing.R;

import java.util.List;

/**
 * Created by 陈若韬 on 2018/4/17.
 */

public class XiaoxiAdapter extends ArrayAdapter<Xiaoxi> {
    private int resourceId;

    public XiaoxiAdapter(Context context, int textViewResourceId, List<Xiaoxi> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertVIew, ViewGroup parent) {
        Xiaoxi xiaoxi = getItem(position);//获取当前项的item实例
        View view;
        XiaoxiAdapter.ViewHolder viewHolder;
        if (convertVIew == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new XiaoxiAdapter.ViewHolder();
            viewHolder.xiaoxiImage = (ImageView) view.findViewById(R.id.xiaoxi_image);
            viewHolder.xiaoxiName = (TextView) view.findViewById(R.id.xiaoxi_name);
            view.setTag(viewHolder);
        } else {
            view = convertVIew;
            viewHolder = (XiaoxiAdapter.ViewHolder) view.getTag();
        }
        viewHolder.xiaoxiImage.setImageResource(xiaoxi.getImageId());
        viewHolder.xiaoxiName.setText(xiaoxi.getName());
        return view;
    }

    class ViewHolder {
        ImageView xiaoxiImage;
        TextView xiaoxiName;
    }
}

