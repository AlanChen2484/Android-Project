package chen.zucc.com.personalassistant.Manage_money_matters;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
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

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private TextView DateView1;
    private TextView DateView2;

//    public static final String EXTRA_NOTE_ID = "noteId";
//    public static final String EXTRA_NOTE_NAME = "noteName";
//    public static final String EXTRA_NOTE_CONTENT = "noteContent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_investment);

        assetName = (EditText) findViewById(R.id.editText1);
        asset = (EditText)findViewById(R.id.editText2);
        yield = (EditText)findViewById(R.id.editText3);
//        beginDate = (TextView)findViewById(R.id.DateView1);
//        endDate = (TextView)findViewById(R.id.DateView2);
        remark = (EditText)findViewById(R.id.editText4);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageBtn_1);
         final TextView beginDate = (TextView) findViewById(R.id.DateView1);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageBtn_2);
         final TextView endDate = (TextView) findViewById(R.id.DateView2);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.imgbtnpublish);

        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);

        dbRead = dbHelper.getReadableDatabase();
        dbWrite = dbHelper.getWritableDatabase();

//        noteId = getIntent().getIntExtra(EXTRA_NOTE_ID, -1);
//
//        if (noteId > -1) {
//            etName.setText(getIntent().getStringExtra(EXTRA_NOTE_NAME));
//            etContent.setText(getIntent().getStringExtra(EXTRA_NOTE_CONTENT));

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InvestmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        beginDate.setText("" + String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2017, 6, 14).show();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InvestmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endDate.setText("" + String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2017, 6, 14).show();
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveNote();
                Toast.makeText(InvestmentActivity.this, "项目添加成功", Toast.LENGTH_SHORT).show();
                finish();
                setResult(RESULT_OK);
            }
        });

    }


    public int saveNote() {
        ContentValues cv = new ContentValues();
        cv.put("Money_assetName", assetName.getText().toString());
        cv.put("Money_asset", asset.getText().toString());
        cv.put("Money_yield", yield.getText().toString());

//        cv.put("Money_beginDate", beginDate.getText().toString());
//        cv.put("Money_endDate", endDate.getText().toString());

        cv.put("Money_remark", remark.getText().toString());

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
