package com.example.quxing.quxing.Xiaoxi;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.quxing.quxing.Fabu.FabuActivity;
import com.example.quxing.quxing.Main.*;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Wode.WodeActivity;

import java.util.ArrayList;
import java.util.List;

public class XiaoxiActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private List<Xiaoxi> xiaoxiList = new ArrayList<>();

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 2;//定义页码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //ListView
        initItems();//初始化滚动条数据

        XiaoxiAdapter adapter = new XiaoxiAdapter(XiaoxiActivity.this, R.layout.xiaoxi_item, xiaoxiList);
        ListView listView = (ListView) findViewById(R.id.listview_main);
        listView.setAdapter(adapter);

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

//            case 2:
//                Intent intent2 = new Intent(this, XiaoxiActivity.class);
//                this.startActivity(intent2);
//                finish();
//                break;

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

//    public Toolbar initToolbar(int id, int titleId, int titleString) {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
////        toolbar.setTitle("");
//        TextView textView = (TextView) findViewById(R.id.toolbar_title3);
//        textView.setText(titleString);
//        setSupportActionBar(toolbar);
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        return toolbar;
//    }


////切换Fragment
//    private void bindViews() {
//        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
//        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
//        rb_message = (RadioButton) findViewById(R.id.rb_message);
//        rg_tab_bar.setOnCheckedChangeListener(this);
//
//        vpager = (ViewPager) findViewById(R.id.vpager);
//        vpager.setAdapter(mAdapter);
//        vpager.setCurrentItem(0);
//        vpager.addOnPageChangeListener(this);
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId) {
//            case R.id.rb_channel:
//                vpager.setCurrentItem(PAGE_ONE);
//                break;
//            case R.id.rb_message:
//                vpager.setCurrentItem(PAGE_TWO);
//                break;
//        }
//    }
//
//
//    //重写ViewPager页面切换的处理方法
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
//        if (state == 2) {
//            switch (vpager.getCurrentItem()) {
//                case PAGE_ONE:
//                    rb_channel.setChecked(true);
//                    break;
//                case PAGE_TWO:
//                    rb_message.setChecked(true);
//                    break;
//
//            }
//        }
//    }

    private void initItems() {
        for (int i = 0; i < 1; i++) {
            Xiaoxi Act1 = new Xiaoxi("让我们一起去看星空", R.drawable.ic_wode_personalbackground);
            xiaoxiList.add(Act1);
            Xiaoxi Act2 = new Xiaoxi("小游戏爱好者的聚会", R.drawable.ic_wode_personalimage);
            xiaoxiList.add(Act2);
        }
    }
}

