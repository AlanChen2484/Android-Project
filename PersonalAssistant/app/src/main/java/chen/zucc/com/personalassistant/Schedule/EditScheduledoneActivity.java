package chen.zucc.com.personalassistant.Schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import chen.zucc.com.personalassistant.R;

/**
 * Created by chenchongkang on 2017/7/6.
 */

public class EditScheduledoneActivity extends Activity implements View.OnClickListener{
    private ImageButton mMenuTv;
    private PopupWindow mPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editschedule_done);
        mMenuTv = (ImageButton) findViewById(R.id.imageBtn_2);
        mMenuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        ImageButton imageButton=(ImageButton)findViewById(R.id.imageBtn_1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(EditScheduledoneActivity.this, ScheduleActivity.class);
                startActivity(intent_1);
            }
        });
    }
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(EditScheduledoneActivity.this).inflate(R.layout.activity_schedule_popuplayout, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView)contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        View rootview = LayoutInflater.from(EditScheduledoneActivity.this).inflate(R.layout.activity_editschedule_done, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 300, 1150);    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.pop_computer:{
                Toast.makeText(this, "clicked computer", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_financial:{
                Toast.makeText(this,"clicked financial",Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_manage:{
                Toast.makeText(this,"clicked manage",Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
        }
    }
}
