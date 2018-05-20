package com.example.quxing.quxing.Xiaoxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quxing.quxing.Auxiliary.Item_DetailsActivity;
import com.example.quxing.quxing.Fabu.FabuActivity;
import com.example.quxing.quxing.Main.ItemAdapter;
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.Main.Main_CityActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.model.CommunicationBean;
import com.example.quxing.quxing.model.ItemInfoBean;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Xiaoxi_DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    //ListView
    private List<CommunicationBean> comList = new ArrayList<>();

    private CommunicationAdapter adapter;

    Button talkingput;
    private String username, password;
    private int userid;
    EditText Xiaoxitalking;

    //    private String itemname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi__details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_xiaoxidetails);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new CommunicationAdapter(Xiaoxi_DetailsActivity.this, R.layout.xiaoxi_itemtext, comList);
        ListView listView = (ListView) findViewById(R.id.listview_xiaoxidetails);
        listView.setAdapter(adapter);

        talkingput = (Button) findViewById(R.id.button_talkingput);
        talkingput.setOnClickListener(this);

        Xiaoxitalking =(EditText)findViewById(R.id.xiaoxi_talking);

        Intent intent = getIntent();
        String itemname = intent.getStringExtra("itemname");

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
        Intent intent = getIntent();
        String itemname = intent.getStringExtra("itemname");
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/comget/getitemname/" + itemname);
        Gson gson = new Gson();
        comList.clear();
        //更新适配器数据
        comList.addAll((Collection<? extends CommunicationBean>) gson.fromJson(jsondata, new TypeToken<List<CommunicationBean>>() {
        }.getType()));
        showToast();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_talkingput:
                talkingpost.start();
                showToast1("消息已发送");
                Intent intent = new Intent(this, XiaoxiActivity.class);
                this.startActivity(intent);
                finish();
            default:
                break;
        }
    }

    Thread talkingpost = new Thread() {
        public void run() {
            //定义传入字符串数据
            Intent intent = getIntent();
            String itemname = intent.getStringExtra("itemname");
            int itemid1 = getItemid();
            String talking = Xiaoxitalking.getText().toString().trim();

            JSONObject jsonObject = new JSONObject();//构建即直接实例化一个JSONObject对象
            try {
                //调用其put()方法,将数据写入
                jsonObject.put("itemname", itemname);
                jsonObject.put("itemid", itemid1);
                jsonObject.put("username", username);
                jsonObject.put("comcontentinfo", talking);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String s = HttpHandler.executeHttpPost("http://192.168.43.34:8082/com", jsonObject.toString());

//            //点击按钮清空EditText
//            Xiaoxitalking.getText().clear();

        }
    };

    //获取itemid
    public int getItemid() {
        Intent intent = getIntent();
        String itemname = intent.getStringExtra("itemname");
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/itemget/getname/" + itemname);
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int itemid1 = object.getInt("itemid");
                return itemid1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void showToast1(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Xiaoxi_DetailsActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(Xiaoxi_DetailsActivity.this, XiaoxiActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showToast() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
