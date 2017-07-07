package chen.zucc.com.personalassistant.Personal_homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.login.LoginActivity;


public class SettingActivity extends AppCompatActivity {
    private LinearLayout accountSet;
    private TextView setting;
    private TextView retroaction;
    private TextView remove;
    private TextView logout;
    private TextView aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//为了隐藏手机状态栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.setting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initView();
        accountSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_as = new Intent(SettingActivity.this, AccountsActivity.class);
                startActivity(intent_as);
            }
        });

        setting = (TextView)findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, PersonalSettingActivity.class);
                SettingActivity.this.startActivity(intent);
            }
        });

        aboutus = (TextView)findViewById(R.id.aboutus);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AboutUsActivity.class);
                SettingActivity.this.startActivity(intent);
            }
        });

        remove = (TextView)findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "内存已清理", Toast.LENGTH_SHORT).show();
            }
        });

        retroaction = (TextView)findViewById(R.id.retroaction);
        retroaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, RetroactionActivity.class);
                SettingActivity.this.startActivity(intent);
            }
        });

        logout = (TextView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                Toast.makeText(getApplicationContext(), "您已退出登录", Toast.LENGTH_SHORT).show();
                SettingActivity.this.startActivity(intent);
            }
        });

    }
    //初始化
    private void initView() {
        accountSet = (LinearLayout) findViewById(R.id.accout_set);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(SettingActivity.this, homepage.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

