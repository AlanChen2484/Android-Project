package chen.zucc.com.personalassistant.Income_expenses;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.R;

/**
 * Created by chenchongkang on 2017/7/7.
 */

public class IncomeFragmentOne extends Fragment {
    private DataBaseHelper dbHelper;
    private ImageButton btn;
    private TextView tv;
    private TextView tv_1;
    private EditText edt;
    private TextView tv_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_incomeone,container,false);
        dbHelper = new DataBaseHelper(getActivity(), "PersonalAssistant.db", null, 1);
        Button btn_cancel=(Button)view.findViewById(R.id.income_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_1 = new Intent(getActivity(), Income_expensesActivity.class);
                startActivity(intent_1);
            }
        });
        btn=(ImageButton)view.findViewById(R.id.imageBtn_s1);
        tv= (TextView)view.findViewById(R.id.income_time);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv.setText(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2017,6,9).show();
            }
        });


        edt=(EditText)view.findViewById(R.id.muchMoney);
        tv_1=(TextView)view.findViewById(R.id.income_income);
        tv_2 = (TextView)view.findViewById(R.id.income_time);

        Button btn_true = (Button) view.findViewById(R.id.income_true);
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(getActivity(), Income_expensesActivity.class);
                startActivity(intent_1);
                /**
                 * 将数据保存至数据库
                 */
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String IncomeIncome = edt.getText().toString();
                String IncomeClasses=tv_1.getText().toString();
                String IncomeTime=tv_2.getText().toString();
                ContentValues values = new ContentValues();
                values.put("Income_income", IncomeIncome);
                values.put("Income_classes", IncomeClasses);
                values.put("Income_time", IncomeTime);
                db.insert("Income", null, values);
                db.close();
            }
        });
        return view;

    }
}