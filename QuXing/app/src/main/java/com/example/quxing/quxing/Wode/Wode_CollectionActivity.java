package com.example.quxing.quxing.Wode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quxing.quxing.Auxiliary.Item_DetailsActivity;
import com.example.quxing.quxing.Main.ItemAdapter;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.model.ItemInfoBean;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wode_CollectionActivity extends AppCompatActivity {
    //ListView
    private List<ItemInfoBean> itemList = new ArrayList<>();
    private TextView itemname;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode__collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_collection);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemname = (TextView) findViewById(R.id.item_name);

        adapter = new ItemAdapter(Wode_CollectionActivity.this, R.layout.main_item, itemList);
        ListView listView = (ListView) findViewById(R.id.listview_main);
        listView.setAdapter(adapter);

        new Thread() {
            public void run() {
                parseJOSNWithGSON();
            }
        }.start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemInfoBean item = itemList.get(i);
                Context context = Wode_CollectionActivity.this;
                Intent intent = new Intent(context, Item_DetailsActivity.class);
                intent.putExtra("itemname", item.getItemname());
                startActivity(intent);
            }
        });
//        return view;
    }

    private void parseJOSNWithGSON() {
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/itemget");
        Gson gson = new Gson();
        itemList.clear();
        //更新适配器数据
        itemList.addAll((Collection<? extends ItemInfoBean>) gson.fromJson(jsondata, new TypeToken<List<ItemInfoBean>>() {
        }.getType()));
        showToast();
    }

    private void showToast() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(Wode_CollectionActivity.this, WodeActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
