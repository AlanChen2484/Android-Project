package chen.zucc.com.personalassistant.Schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.R;
public class ScheduleAddActivity extends AppCompatActivity {
    private DataBaseHelper dbHelper;
    private ImageButton btn;
    private ImageButton btn2;
    private TextView tv;
    private TextView tv2;
    private TextView tv_1;
    private TextView tv_2;
    private EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_add);
        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
        /**
         * 跳转至日程首页
         * */
        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(ScheduleAddActivity.this, ScheduleActivity.class);
                startActivity(intent_1);
            }
        });
        /**
         * 跳转至日程首页
         * */
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(ScheduleAddActivity.this, ScheduleActivity.class);
                startActivity(intent_1);
                /**
                 * 将数据保存至数据库
                 */
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edt = (EditText) findViewById(R.id.schedule_add_editText_1);
                tv_1 = (TextView) findViewById(R.id.textView_schedule);
                tv_2 = (TextView) findViewById(R.id.textView_time);
                String Scheduledetails = edt.getText().toString();
                String Schedulebeginschedule = tv_1.getText().toString();
                String Schedulebegintime = tv_2.getText().toString();
                ContentValues values = new ContentValues();
                values.put("Schedule_details", Scheduledetails);
                values.put("Schedule_beginschedule", Schedulebeginschedule);
                values.put("Schedule_begintime", Schedulebegintime);
                db.insert("Schedule", null, values);
                db.close();
            }
        });
        /**
         *获取日期
         */
        btn = (ImageButton) this.findViewById(R.id.imageBtn_schedule);
        tv = (TextView) this.findViewById(R.id.textView_schedule);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ScheduleAddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2017, 6, 9).show();
            }
        });
        /**
         *获取时间
         */
        btn2 = (ImageButton) this.findViewById(R.id.imageBtn_time);
        tv2 = (TextView) this.findViewById(R.id.textView_time);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ScheduleAddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv2.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                    //0,0指的是时间，true表示是否为24小时，true为24小时制
                }, 0, 0, true).show();
            }
        });
    }
}
