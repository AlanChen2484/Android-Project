package com.example.quxing.quxing.Wode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.quxing.quxing.Fabu.FabuActivity;
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.Xiaoxi.XiaoxiActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WodeActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,
        View.OnClickListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 3;//定义页码

    TextView remarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //BottomNavigationBar-底部导航栏
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.RED)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.tuijian, "推荐").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.fabu, "发布").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.xiaoxi, "消息").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.wode, "我的").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);

        remarks = (TextView) findViewById(R.id.remarks);

        new Thread() {
            public void run() {
                parseJOSNWithGSON();
            }
        }.start();
    }

    private void parseJOSNWithGSON() {
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/userinfo");
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String remarks1 = object.getString("remarks");

//                SimpleDateFormat dateFormater_1 = new SimpleDateFormat("yyyy-MM-dd");
//                Date date_1= new Date(object.getLong("starttime"));
//                actdis_starttime.setText(dateFormater_1.format(date_1));
//                SimpleDateFormat dateFormater_2 = new SimpleDateFormat("yyyy-MM-dd");
//                Date date_2= new Date(object.getLong("starttime"));
//                actdis_endtime.setText(dateFormater_2.format(date_2));
                showUI(remarks1);

//                actdis_content.setText(content);
//                actdis_sourcename.setText(source);
//                actdis_address.setText(address);
//                actdis_state.setText(state);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showUI(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                remarks.setText(text);
            }
        });
    }

    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position) {
            case 0:
                Intent intent0 = new Intent(this, MainActivity.class);
                this.startActivity(intent0);
                finish();
                break;

            case 1:
                Intent intent1 = new Intent(this, FabuActivity.class);
                this.startActivity(intent1);
                finish();
                break;

            case 2:
                Intent intent2 = new Intent(this, XiaoxiActivity.class);
                this.startActivity(intent2);
                finish();
                break;

//            case 3:
//                Intent intent3 = new Intent(this, WodeActivity.class);
//                this.startActivity(intent3);
//                finish();
//                break;
        }

    }


    public void onTabUnselected(int position) {

    }


    public void onTabReselected(int position) {

    }

    //界面按钮点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wode_collection:
                Intent intent_1 = new Intent(this, Wode_CollectionActivity.class);
                startActivity(intent_1);
                break;
            case R.id.wode_evaluation:
                Intent intent_2 = new Intent(this, Wode_EvaluationActivity.class);
                startActivity(intent_2);
                break;
            case R.id.wode_follow:
                Intent intent_3 = new Intent(this, Wode_FollowActivity.class);
                startActivity(intent_3);
                break;
            case R.id.wode_about:
                Intent intent_4 = new Intent(this, Wode_AboutActivity.class);
                startActivity(intent_4);
                break;
            case R.id.wode_setting:
                Intent intent_5 = new Intent(this, Wode_SettingActivity.class);
                startActivity(intent_5);
                break;
            default:
                break;
        }
    }
}
