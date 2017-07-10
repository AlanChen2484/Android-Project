package chen.zucc.com.personalassistant.Manage_money_matters;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import chen.zucc.com.personalassistant.R;

public class InvestmentActivity extends AppCompatActivity {

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private TextView DateView1;
    private TextView DateView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_investment);

        ImageButton imageButton1 =(ImageButton)findViewById(R.id.imageBtn_1);
        final TextView DateView1 = (TextView)findViewById(R.id.DateView1);
        ImageButton imageButton2 =(ImageButton)findViewById(R.id.imageBtn_2);
        final TextView DateView2 = (TextView)findViewById(R.id.DateView2);


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InvestmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        DateView1.setText(""+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2017,6,14).show();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InvestmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        DateView2.setText(""+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2017,6,14).show();
            }
        });
    }
}
