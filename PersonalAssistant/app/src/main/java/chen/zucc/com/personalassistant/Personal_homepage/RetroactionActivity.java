package chen.zucc.com.personalassistant.Personal_homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import chen.zucc.com.personalassistant.R;

public class RetroactionActivity extends AppCompatActivity {

    private Button submit;
    private boolean flag = true;

    private ImageButton imageButton;

    private static final int IMAGE = 1;

    //所需权限
//    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_retroaction);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("用户反馈");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        submit = (Button) findViewById(R.id.button1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(RetroactionActivity.this, SettingActivity.class);
                RetroactionActivity.this.startActivity(intent1);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(RetroactionActivity.this, SettingActivity.class);
                startActivity(intent);
                RetroactionActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
