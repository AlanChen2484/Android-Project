package com.example.quxing.quxing.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.quxing.quxing.DataBaseHelper.DataBaseHelper;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Main.MainActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    Button btGo;
    Button btTrd;
    CardView cv;
    FloatingActionButton fab;

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ButterKnife.inject(this);
        ButterKnife.bind(this);

        dbHelper = new DataBaseHelper(this, "QuXingdb.db", null, 1);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btGo = (Button) findViewById(R.id.bt_go);
//        btTrd = (Button) findViewById(R.id.bt_trd);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    //点击登录按钮
    public boolean loginClicked() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        String userName = etUsername.getText().toString();
        String passWord = etPassword.getText().toString();
        if (login(userName, passWord)) {
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "用户名密码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean login(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from Login where Login_UserName=? and Login_Password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent, options.toBundle());
                break;
            case R.id.bt_go:
//                if (loginClicked()) {
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2);
                finish();
//                }
                break;
//            case R.id.bt_trd:
//                Intent i3 = new Intent(this,PersonalInformationActivity.class);
//                startActivity(i3);
//                finish();
//                break;
        }
    }


}