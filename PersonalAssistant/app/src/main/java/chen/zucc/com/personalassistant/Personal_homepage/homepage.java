package chen.zucc.com.personalassistant.Personal_homepage;

import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.math.BigDecimal;

import butterknife.ButterKnife;
import butterknife.OnClick;
import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.Income_expenses.Income_expensesActivity;
import chen.zucc.com.personalassistant.Manage_money_matters.Manager_money_mattersActivity;
import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.Schedule.ScheduleActivity;
import chen.zucc.com.personalassistant.util.DensityUtil;

public class homepage extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    // 默认是日间模式

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 3;
    private ImageButton imageButton;
    private ImageButton imageButton2;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private Switch aSwitch;
    private Button button;
    private CardView cardView1;
    private TextView tv1;

    private UiModeManager mUiModeManager = null;

    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);

        setContentView(R.layout.activity_homepage);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//为了隐藏手机状态栏
        aSwitch = (Switch) findViewById(R.id.switchover);


        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.RED)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_schedule, "日程安排").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_bill, "账本记录").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_managemoney, "财富管理").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_person, "个人主页").setActiveColorResource(R.color.blue))

                .setFirstSelectedPosition(lastSelectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);

        ButterKnife.bind(this);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                show2();
                //break;
            }
        });
        cardView1 = (CardView) findViewById(R.id.cardview1); //点击转到位置的界面
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(homepage.this, Home_mapActivity.class);
                startActivity(intent);
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                } else {
                    mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                }
            }
        });

//
//        BigDecimal bg1 = new BigDecimal(getsum1() / getsum() * 100);//保留两位小数
//        double shouyi = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        tv1 = (TextView) findViewById(R.id.shouyilv);
//        tv1.setText("您的收益率为:" + String.valueOf(shouyi) + "%");

    }

//    public double getsum1() {
//        double income;
//        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
//        db = dbHelper.getReadableDatabase();
//        Cursor sum0 = db.rawQuery("select sum(SortMoney_income) from SortMoney", null);
//        sum0.moveToNext();
//        if (sum0.getCount()!=0) {
//             income = sum0.getDouble(sum0.getColumnIndex("sum(SortMoney_income)"));
//        } else {
//            income = 0;
//        }
////        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
//        return income;
//    }
//
//
//    public double getsum() {
//        double benjin;
//        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
//        db = dbHelper.getReadableDatabase();
//        Cursor sum0 = db.rawQuery("select sum(SortMoney_money) from SortMoney where SortMoney_state=0", null);
//        sum0.moveToNext();
//        Cursor sum1 = db.rawQuery("select sum(SortMoney_money) from SortMoney where SortMoney_state=1", null);
//        sum1.moveToNext();
//        if (sum0.getCount()!= 0 || sum1.getCount()!= 0) {
//            benjin = sum0.getDouble(sum0.getColumnIndex("sum(SortMoney_money)"))
//                    + sum1.getDouble(sum1.getColumnIndex("sum(SortMoney_money)"));
//        } else {
//            benjin = 0;
//        }
////        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
//        return benjin;
//    }


    private void show2() {
        Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_content_circle, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(this, 16f);
        params.bottomMargin = DensityUtil.dp2px(this, 8f);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
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

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


}