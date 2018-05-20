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
import com.example.quxing.quxing.model.CommunicationBean;
import com.example.quxing.quxing.model.ItemInfoBean;

import java.util.List;


/**
 * Created by 陈若韬 on 2018/3/27.
 */

public class CommunicationAdapter extends ArrayAdapter<CommunicationBean> {

    private int resourceId;
    private Context context;

    public CommunicationAdapter(Context context, int textViewResourceId, List<CommunicationBean> object) {
        super(context, textViewResourceId, object);
        this.context = context;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        Item item = getItem(position);
        CommunicationBean communicationBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView communicationinfo = (TextView) view.findViewById(R.id.xiaoxi_text);
        TextView username = (TextView) view.findViewById(R.id.xiaoxi_username);

        communicationinfo.setText(communicationBean.getComcontentinfo());
        username.setText(communicationBean.getUsername());

        return view;
    }
}