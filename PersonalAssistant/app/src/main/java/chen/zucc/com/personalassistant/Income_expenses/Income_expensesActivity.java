package chen.zucc.com.personalassistant.Income_expenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import chen.zucc.com.personalassistant.Manage_money_matters.Manager_money_mattersActivity;
import chen.zucc.com.personalassistant.Personal_homepage.homepage;
import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.Schedule.ScheduleActivity;

public class Income_expensesActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expenses);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

//     BadgeItem numberBadgeItem = new BadgeItem()
//               .setBorderWidth(4)
//               .setBackgroundColor(Color.RED)
//               .setText("5")
//             .setHideOnSelect(true);
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

