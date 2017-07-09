package chen.zucc.com.personalassistant.Schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import chen.zucc.com.personalassistant.Income_expenses.Income_expensesActivity;
import chen.zucc.com.personalassistant.Manage_money_matters.Manager_money_mattersActivity;
import chen.zucc.com.personalassistant.Personal_homepage.homepage;
import chen.zucc.com.personalassistant.R;

public class ScheduleActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {
    private OneFragment f1;
    private TwoFragment f2;
    //底部三个按钮
    private Button foot1;
    private Button foot2;
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        foot1 = (Button) findViewById(R.id.btn1);
        foot2 = (Button) findViewById(R.id.btn2);
        foot1.setOnClickListener(this);
        foot2.setOnClickListener(this);
        //第一次初始化首页默认显示第一个fragment
        initFragment1();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//为了隐藏手机状态栏
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);


//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.RED)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_action_schedule, "日程安排").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_bill, "账本记录").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_managemoney, "财富管理").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_person, "个人主页").setActiveColorResource(R.color.blue))
//                .setFirstSelectedPosition(0)
                .setFirstSelectedPosition(lastSelectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void initFragment1(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(f1 == null){
            f1 = new OneFragment("未完成日程");
            transaction.add(R.id.main_frame_layout, f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);
        transaction.commit();
    }
    //显示第二个fragment
    private void initFragment2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f2 == null){
            f2 = new TwoFragment("已完成日程");
            transaction.add(R.id.main_frame_layout,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();
    }
    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
    }
    @Override
    public void onClick(View v) {
        if(v == foot1){
            initFragment1();
        }else if(v == foot2){
            initFragment2();}

    }

    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position) {
            case 0:
                Intent intent0 = new Intent(this, ScheduleActivity.class);
                this.startActivity(intent0);
                break;

            case 1:
                Intent intent1 = new Intent(this, Income_expensesActivity.class);
                this.startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(this, Manager_money_mattersActivity.class);
                this.startActivity(intent2);
                break;

            case 3:
                Intent intent3 = new Intent(this, homepage.class);
                this.startActivity(intent3);
                break;
        }

    }

    public void onTabUnselected(int position) {

    }

    public void onTabReselected(int position) {

    }
}
