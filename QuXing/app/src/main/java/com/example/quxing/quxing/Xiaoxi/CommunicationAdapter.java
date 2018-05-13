package com.example.quxing.quxing.Xiaoxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quxing.quxing.R;
import com.example.quxing.quxing.model.Communication;

import java.util.List;


/**
 * Created by 陈若韬 on 2018/3/27.
 */

public class CommunicationAdapter extends ArrayAdapter<Communication> {

    private int resourceId;
    private Context context;

    public CommunicationAdapter(Context context, int textViewResourceId, List<Communication> object) {
        super(context, textViewResourceId, object);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertVIew, ViewGroup parent) {
        Communication xiaoxitest = getItem(position);//获取当前项的item实例
        View view;
        ViewHolder viewHolder;
        if (convertVIew == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new CommunicationAdapter.ViewHolder();
//            viewHolder.xiaoxiImage = (ImageView) view.findViewById(R.id.xiaoxi_image);
            viewHolder.xiaoxiName = (TextView) view.findViewById(R.id.xiaoxi_text);
            view.setTag(viewHolder);
        } else {
            view = convertVIew;
            viewHolder = (CommunicationAdapter.ViewHolder) view.getTag();
        }
//        viewHolder.xiaoxiImage.setImageResource(xiaoxitest.getImageId());
//        viewHolder.xiaoxiName.setText(xiaoxitest.getName());
        return view;
    }

    class ViewHolder {
        ImageView xiaoxiImage;
        TextView xiaoxiName;
    }
}