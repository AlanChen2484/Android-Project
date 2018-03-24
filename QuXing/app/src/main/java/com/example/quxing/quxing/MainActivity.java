package com.example.quxing.quxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.quxing.quxing.Fabu.FabuActivity;
import com.example.quxing.quxing.Wode.WodeActivity;
import com.example.quxing.quxing.Xiaoxi.XiaoxiActivity;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;//定义页码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

////在这里调用状态栏辅助类
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
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
    }


    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position) {
//            case 0:
//                Intent intent0 = new Intent(this, MainActivity.class);
//                this.startActivity(intent0);
//                finish();
//                break;

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

            case 3:
                Intent intent3 = new Intent(this, WodeActivity.class);
                this.startActivity(intent3);
                finish();
                break;
        }

    }

    public void onTabUnselected(int position) {

    }

    public void onTabReselected(int position) {

    }

//添加按钮图标
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar1, menu);
//        return true;
//    }

//    public Toolbar initToolbar(int id, int titleId, int titleString) {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
////        toolbar.setTitle("");
//        TextView textView = (TextView) findViewById(R.id.toolbar_title1);
//        textView.setText(titleString);
//        setSupportActionBar(toolbar);
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        return toolbar;
//    }

}


