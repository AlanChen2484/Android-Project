package chen.zucc.com.personalassistant.Schedule;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import chen.zucc.com.personalassistant.R;

public class ScheduleAddActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_add);
        Button button=(Button)findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(ScheduleAddActivity.this, ScheduleActivity.class);
                startActivity(intent_1);
            }
        });
    }


}
