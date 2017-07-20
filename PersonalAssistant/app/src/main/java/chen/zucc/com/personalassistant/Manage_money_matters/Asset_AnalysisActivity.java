package chen.zucc.com.personalassistant.Manage_money_matters;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.kimkevin.slidingicontablayout.wigets.SlidingIconTabLayout;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.R;


public class Asset_AnalysisActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MainTabAdapter mAdapter;
    int lastSelectedPosition = 2;
    private ImageButton imageButtonback;
    private ImageButton imageButtonadd;
    private DataBaseHelper dbHelper;
    private SQLiteOpenHelper dbhelper;
    private SQLiteDatabase db;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_asset__analysis);

        mAdapter = new MainTabAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);

        imageButtonback = (ImageButton) findViewById(R.id.imageButton_back);

        imageButtonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Asset_AnalysisActivity.this, Manager_money_mattersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageButtonadd = (ImageButton) findViewById(R.id.imageButton_add);

        imageButtonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Asset_AnalysisActivity.this, InvestmentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SlidingIconTabLayout mSlidingTabLayout = (SlidingIconTabLayout) findViewById(R.id.tabs);

        mSlidingTabLayout.setCustomTabView(R.layout.tab_txt_layout, R.id.tab_name_txt);//放的是导航栏的内容

        mSlidingTabLayout.setCustomTabColorizer(new SlidingIconTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.grey);  //导航栏下面的横线的颜色
            }
        });

        mSlidingTabLayout.setViewPager(mViewPager);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setText(String.valueOf(getsum1()));
        tv2 = (TextView) findViewById(R.id.textView3);
        tv2.setText(String.valueOf(getsum()));
        tv3 = (TextView) findViewById(R.id.textView5);
        tv3.setText(String.valueOf(getsum2()));

    }

    public double getsum() {
        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor sum0 = db.rawQuery("select sum(SortMoney_money) from SortMoney where SortMoney_state=0", null);
        sum0.moveToNext();
        double benjin = sum0.getDouble(sum0.getColumnIndex("sum(SortMoney_money)"));
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
        Cursor sum0 = db.rawQuery("select sum(SortMoney_capital) from SortMoney", null);
        sum0.moveToNext();

        double income = sum0.getDouble(sum0.getColumnIndex("sum(SortMoney_capital)"));

//        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
        return income;
    }

}


