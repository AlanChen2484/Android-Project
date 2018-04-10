package com.example.quxing.quxing.Wode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quxing.quxing.R;
import com.example.quxing.quxing.login.LoginActivity;

public class Wode_SettingActivity extends AppCompatActivity {

    private LinearLayout accountSet;
    private TextView setting;
    private TextView retroaction;
    private TextView remove;
    private TextView logout;
    private TextView aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode__seeting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

        setting = (TextView)findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode_SettingActivity.this, Wode_PersonalSettingActivity.class);
                Wode_SettingActivity.this.startActivity(intent);

            }
        });

        accountSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_as = new Intent(Wode_SettingActivity.this, Wode_AccountsActivity.class);
                Wode_SettingActivity.this.startActivity(intent_as);

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
                Intent intent = new Intent(Wode_SettingActivity.this, Wode_RetroactionActivity.class);
                Wode_SettingActivity.this.startActivity(intent);

            }
        });

        logout = (TextView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode_SettingActivity.this, LoginActivity.class);
                Toast.makeText(getApplicationContext(), "您已退出登录", Toast.LENGTH_SHORT).show();
                Wode_SettingActivity.this.startActivity(intent);
                finish();
            }
        });

    }
    //初始化
    private void initView() {
        accountSet = (LinearLayout) findViewById(R.id.accout_set);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(Wode_SettingActivity.this, WodeActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
