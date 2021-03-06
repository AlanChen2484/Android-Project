package com.example.quxing.quxing.Wode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.model.ItemInfoBean;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wode_EvaluationActivity extends AppCompatActivity {

    //ListView
    private List<ItemInfoBean> itemList = new ArrayList<>();
    private TextView itemname;
    private ItemAdapter adapter;
    private String username, password;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode__evaluate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_evaluation);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemname = (TextView) findViewById(R.id.item_name);

        adapter = new ItemAdapter(Wode_EvaluationActivity.this, R.layout.main_item, itemList);
        ListView listView = (ListView) findViewById(R.id.listview_main);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemInfoBean item = itemList.get(i);
                Context context = Wode_EvaluationActivity.this;
                Intent intent = new Intent(context, Item_DetailsActivity.class);
                intent.putExtra("itemname", item.getItemname());
                startActivity(intent);
            }
        });
//        return view;

        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        userid = pref.getInt("userid", 1);
        username = pref.getString("username", "");
        password = pref.getString("password", "");

        new Thread() {
            public void run() {
                parseJOSNWithGSON();
            }
        }.start();
    }

    private void parseJOSNWithGSON() {
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/usertoitem/getusername/"+username);
        Gson gson = new Gson();
        itemList.clear();
        //更新适配器数据
        itemList.addAll((Collection<? extends ItemInfoBean>) gson.fromJson(jsondata, new TypeToken<List<ItemInfoBean>>() {
        }.getType()));
        showToast();
    }

//    private ArrayList<String> getitemname() {
//        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/usertoitem/getusername/" + username);
//        ArrayList<String> list = new ArrayList<String>();
//        try {
//            JSONArray jsonArray = new JSONArray(jsondata);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
//                String itemname = object.getString("itemname");
//                list.add(itemname);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


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
                Intent intent_set = new Intent(Wode_EvaluationActivity.this, WodeActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
