package chen.zucc.com.personalassistant.Schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import chen.zucc.com.personalassistant.R;

/**
 * Created by chenchongkang on 2017/7/6.
 */

public class EditScheduleActivity extends Activity {
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editschedule);
        ImageButton imageButton=(ImageButton)findViewById(R.id.imageBtn_1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(EditScheduleActivity.this, ScheduleActivity.class);
                startActivity(intent_1);
            }
        });
    }
}
