package chen.zucc.com.personalassistant.Manage_money_matters;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.Income_expenses.Income_expensesActivity;
import chen.zucc.com.personalassistant.Personal_homepage.homepage;
import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.Schedule.ScheduleActivity;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Manager_money_mattersActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 2;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private ImageButton imageButton;
    private TextView tv1;
    private TextView tv2;
    private PieChartView pieChart;
    private PieChartData pieChardata;
    List<SliceValue> values = new ArrayList<SliceValue>();
    private double[] data = new double[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_money_matters);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//为了隐藏手机状态栏
        data[0] = getsum2();
        data[1] = getsum3();
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
                .setFirstSelectedPosition(lastSelectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Manager_money_mattersActivity.this, Asset_AnalysisActivity.class);
                startActivity(intent);
            }
        });

        tv1 = (TextView) findViewById(R.id.textView2);
        tv1.setText(String.valueOf(getsum()));

        tv2 = (TextView) findViewById(R.id.textView4);
        tv2.setText("+ " + String.valueOf(getsum1()));

        pieChart = (PieChartView) findViewById(R.id.pie_chart);
        pieChart.setOnValueTouchListener(selectListener);//设置点击事件监听

        setPieChartData();
        initPieChart();

    }

    /**
     * 获取数据
     */
    private void setPieChartData() {
        int[] color = {Color.argb(123, 18, 205, 219), Color.argb(125, 225, 64, 125)};
        for (int i = 0; i < data.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) data[i], color[i]);
            values.add(sliceValue);
        }
    }


    /**
     * 初始化
     */
    private void initPieChart() {
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pieChardata.setCenterText1("投资统计(持有&完成）");//环形中间的文字1
        pieChardata.setCenterText1Color(Color.LTGRAY);//文字颜色
        pieChardata.setCenterText1FontSize(14);//文字大小

        pieChardata.setCenterText2Color(Color.BLACK);
        pieChardata.setCenterText2FontSize(18);
        /**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/
//      Typeface tf = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");
//      data.setCenterText1Typeface(tf);
        pieChart.setPieChartData(pieChardata);
        pieChart.setValueSelectionEnabled(true);//选择饼图某一块变大
        pieChart.setAlpha(0.9f);//设置透明度
        pieChart.setCircleFillRatio(1f);//设置饼图大小

    }


    /**
     * 监听事件
     */
    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            // TODO Auto-generated method stub
            Toast.makeText(Manager_money_mattersActivity.this, "Selected: " + value.getValue(), Toast.LENGTH_SHORT).show();
        }
    };

    public double getsum() {
        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor sum0 = db.rawQuery("select sum(SortMoney_money) from SortMoney where SortMoney_state=0", null);
        sum0.moveToNext();

        double benjin = sum0.getDouble(sum0.getColumnIndex("sum(SortMoney_money)"))


//        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
        return benjin;
    }

    public double getsum1() {
        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor sum0 = db.rawQuery("select sum(SortMoney_income) from SortMoney", null);
        sum0.moveToNext();

        double income = sum0.getDouble(sum0.getColumnIndex("sum(SortMoney_income)"));

//        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
        return income;
    }

    public double getsum2() {
        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor sum0 = db.rawQuery("select sum(SortMoney_money) from SortMoney where SortMoney_state=0", null);
        sum0.moveToNext();

        double income = sum0.getDouble(sum0.getColumnIndex("sum(SortMoney_money)"));

//        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
        return income;
    }

    public double getsum3() {
        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor sum1 = db.rawQuery("select sum(SortMoney_money) from SortMoney where SortMoney_state=1", null);
        sum1.moveToNext();

        double income = sum1.getDouble(sum1.getColumnIndex("sum(SortMoney_money)"));

//        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
        return income;
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
