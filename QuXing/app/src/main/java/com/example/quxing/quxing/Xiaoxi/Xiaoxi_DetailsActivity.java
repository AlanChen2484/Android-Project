package com.example.quxing.quxing.Xiaoxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.quxing.quxing.Main.ItemAdapter;
import com.example.quxing.quxing.R;

import java.util.ArrayList;
import java.util.List;


public class Xiaoxi_DetailsActivity extends AppCompatActivity {

    //ListView
    private List<Communication> comList = new ArrayList<>();

    private CommunicationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi__details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_xiaoxidetails);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        initItems();//初始化滚动条数据
//
//        adapter = new ItemAdapter(Xiaoxi_DetailsActivity.this, R.layout.xiaoxi_itemtext, comList);
//        ListView listView = (ListView) findViewById(R.id.listview_xiaoxidetails);
//        listView.setAdapter(adapter);

    }

    private void initItems() {
        for (int i = 0; i < 2; i++) {
            Communication Act1 = new Communication("让我们一起去看星空");
            comList.add(Act1);
            Communication Act2 = new Communication("小游戏爱好者的聚会");
            comList.add(Act2);
        }
    }
}
