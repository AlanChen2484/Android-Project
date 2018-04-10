package com.example.quxing.quxing.Fabu;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.Main.ViewPagerAdapter;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Wode.WodeActivity;
import com.example.quxing.quxing.Xiaoxi.XiaoxiActivity;
import com.github.kimkevin.slidingicontablayout.wigets.SlidingIconTabLayout;

import java.util.ArrayList;
import java.util.List;

public class FabuActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private ViewPager mViewPager;
    private FabuTabAdapter mAdapter;

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 1;//定义页码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu);

        mAdapter = new FabuTabAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);

//启用Toolbar，隐藏Title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //BottomNavigationBar-底部导航栏
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar1);
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

        SlidingIconTabLayout mSlidingTabLayout = (SlidingIconTabLayout) findViewById(R.id.tabs);

        mSlidingTabLayout.setCustomTabView(R.layout.tab_txt_layout, R.id.tab_name_txt);//放的是导航栏的内容

        mSlidingTabLayout.setCustomTabColorizer(new SlidingIconTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.lightblue);  //导航栏下面的横线的颜色
            }
        });

        mSlidingTabLayout.setViewPager(mViewPager);

    }


    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position) {
            case 0:
                Intent intent0 = new Intent(this, MainActivity.class);
                this.startActivity(intent0);
                finish();
                break;

//            case 1:
//                Intent intent1 = new Intent(this, FabuActivity.class);
//                this.startActivity(intent1);
//                finish();
//                break;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fabu_add:
                Intent intent = new Intent(this, Fabu_AddItemActivity.class);
                this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}



