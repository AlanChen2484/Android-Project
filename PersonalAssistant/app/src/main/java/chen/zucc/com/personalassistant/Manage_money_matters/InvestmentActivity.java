package chen.zucc.com.personalassistant.Manage_money_matters;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.R;

public class InvestmentActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;
    private SQLiteDatabase dbRead, dbWrite;

    private EditText assetName;
    private EditText asset;
    private EditText yield;
    private TextView beginDate;
    private TextView endDate;
    private EditText remark;

    private TextView tvincome1;
    private TextView tvincome2;
    private TextView tvasset;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButtonback;
    private TextView DateView1;
    private TextView DateView2;

    private int mYear;
    private int mMonth;
    private int mDay;

    private String begintime;
    private String endtime;
    private String nowtime;

    private String daystr;
    private int dayint;

    private String day2str;
    private int day2int;

    private String day3str;
    private int day3int;

    private String yieldstr;
    private double yielddb;

    private String assetstr;
    private double assetdb;

    private double income1;
    private double income2;

    private int state;
//    public static final String EXTRA_NOTE_ID = "noteId";
//    public static final String EXTRA_NOTE_NAME = "noteName";
//    public static final String EXTRA_NOTE_CONTENT = "noteContent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_investment);

        tvincome1 = (TextView) findViewById(R.id.textView1);
        tvincome2 = (TextView) findViewById(R.id.textView5);
        tvasset = (TextView) findViewById(R.id.textView3);

        assetName = (EditText) findViewById(R.id.editText1);
        asset = (EditText) findViewById(R.id.editText2);
        yield = (EditText) findViewById(R.id.editText3);
        beginDate = (TextView) findViewById(R.id.DateView1);
        endDate = (TextView) findViewById(R.id.DateView2);
        remark = (EditText) findViewById(R.id.editText4);
        imageButtonback = (ImageButton) findViewById(R.id.imageButton_back);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageBtn_1);
        final TextView beginDate = (TextView) findViewById(R.id.DateView1);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageBtn_2);
        final TextView endDate = (TextView) findViewById(R.id.DateView2);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.imgbtnpublish);

        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);

        dbRead = dbHelper.getReadableDatabase();
        dbWrite = dbHelper.getWritableDatabase();

        // 初始化Calendar日历对象，获取当前时间
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);// 获取年份
        mMonth = c.get(Calendar.MONTH);// 获取月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取天数

        nowtime = String.format("%d/%d/%d 00:00", mYear, mMonth + 1, mDay);


        imageButtonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InvestmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear1, int dayOfMonth1) {
                        beginDate.setText("" + String.format("%d-%d-%d", year1, monthOfYear1 + 1, dayOfMonth1));

                        begintime = String.format("%d/%d/%d 00:00", year1, monthOfYear1 + 1, dayOfMonth1);
                        Log.d("InvestmentActivity ", "time " + begintime);
                    }

                }, mYear, mMonth, mDay + 1).show();
            }
        });


        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InvestmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year2, int monthOfYear2, int dayOfMonth2) {
                        endDate.setText("" + String.format("%d-%d-%d", year2, monthOfYear2 + 1, dayOfMonth2));

                        endtime = String.format("%d/%d/%d 00:00", year2, monthOfYear2 + 1, dayOfMonth2);
                        Log.d("InvestmentActivity ", "time " + endtime);

                    }

                }, mYear, mMonth, mDay + 1).show();

            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                daystr = getTimeExpend(begintime, endtime);
                dayint = Integer.valueOf(daystr).intValue();

                day2str = getTimeExpend(begintime, nowtime);
                day2int = Integer.valueOf(day2str).intValue();

                day3str = getTimeExpend(nowtime, endtime);
                day3int = Integer.valueOf(day3str).intValue();

                if (dayint <= 0) {
                    Toast.makeText(InvestmentActivity.this, "项目日期有误，请重新选择！", Toast.LENGTH_SHORT).show();
                } else {

                    Log.d("InvestmentActivity ", "day1 " + dayint);
                    Log.d("InvestmentActivity ", "day2 " + day2int);
                    Log.d("InvestmentActivity ", "day3 " + day3int);
                    assetstr = asset.getText().toString();
                    assetdb = Double.parseDouble(assetstr);
                    yieldstr = yield.getText().toString();
                    yielddb = Double.parseDouble(yieldstr);

                    income1 = assetdb * yielddb / 100 * dayint / 365;//计算预期收益
                    income2 = assetdb * yielddb / 100 * day2int / 365;//计算累计收益

                    Log.d("InvestmentActivity ", "income1 " + income1);
                    Log.d("InvestmentActivity ", "income2 " + income2);

                    if (day3int <= 0) {   //做状态判断
                        state = 1;    //已完成
                    } else {
                        state = 0;    //未完成
                    }

                    saveNote();
                    tvincome1.setText("+" + String.valueOf(income1));
                    tvasset.setText(String.valueOf(assetdb));
                    if (income2 <= 0) {
                        tvincome2.setText("+0.00");
                    } else {
                        tvincome2.setText("+" + String.valueOf(income2));
                    }

                    Toast.makeText(InvestmentActivity.this, "项目添加成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                }
//                try {
////                    Date d1 = df.parse("2006-05-26 12:00:00");
////                    Date d2 = df.parse("2006-07-02 11:20:00");
//
//                    Date d1 = df.parse("begintime");
//                    Date d2 = df.parse("endtime");
//                    long diff = d2.getTime() - d1.getTime();
//                    long days = diff / (1000 * 60 * 60 * 24);
//                    System.out.println(""+days+"天");
//                    Log.d( "InvestmentActivity ", "day "+ days);
//                } catch (ParseException e) {
//                    System.out.println("error");
//                    e.printStackTrace();
//                }

            }
        });
    }

    private long getTimeMillis(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            Toast.makeText(InvestmentActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return returnMillis;
    }

    private String getTimeExpend(String startTime, String endTime) {
        //传入字串类型 2016/06/28 08:30
        long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        long longDay = longExpend / (60 * 60 * 1000 * 24);//根据时间差来计算天数
//        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
//        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数

        String day = String.valueOf(longDay);
        return day;
    }


    public int saveNote() {
        ContentValues cv = new ContentValues();
        cv.put("Money_assetName", assetName.getText().toString());
        cv.put("Money_asset", assetdb);
        cv.put("Money_yield", yielddb);
        cv.put("Money_beginDate", beginDate.getText().toString());
        cv.put("Money_endDate", endDate.getText().toString());
        cv.put("Money_remark", remark.getText().toString());
        cv.put("Money_anticipated_income", income1);
        cv.put("Money_accumulated_income", income2);
        cv.put("Money_state", state);

//        cv.put(NotesDB.COLUMN_NAME_NOTE_CONTENT, etContent.getText().toString());
//        cv.put(NotesDB.COLUMN_NAME_NOTE_DATE, new SimpleDateFormat(
//                "yyyy-MM-dd hh:mm:ss").format(new Date()));
//
//        if (noteId > -1) {
//            dbWrite.update(NotesDB.TABLE_NAME_NOTES, cv, NotesDB.COLUMN_NAME_ID
//                    + "=?", new String[]{noteId + ""});
//            return noteId;
//        } else {
        return (int) dbWrite.insert("Money", null, cv);

//        }

    }

    /**
     * 复写Activity的生命周期方法，用于关闭读写数据库的操作
     */
    @Override
    protected void onDestroy() {
        dbRead.close();
        dbWrite.close();
        super.onDestroy();
    }

}
