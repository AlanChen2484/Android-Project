package com.example.quxing.quxing.login;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.model.UserLoginBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.quxing.quxing.Auxiliary.ToastUtil.showToast;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    Button btGo;
    Button btTrd;
    CardView cv;
    FloatingActionButton fab;

    private DataBaseHelper dbHelper;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ButterKnife.inject(this);
        ButterKnife.bind(this);
//        dbHelper = new DataBaseHelper(this, "QuXingdb.db", null, 1);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btGo = (Button) findViewById(R.id.bt_go);
//        btTrd = (Button) findViewById(R.id.bt_trd);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

    }

//    //点击登录按钮
//    public boolean loginClicked() {
//        etUsername = (EditText) findViewById(R.id.et_username);
//        etPassword = (EditText) findViewById(R.id.et_password);
//        String userName = etUsername.getText().toString();
//        String passWord = etPassword.getText().toString();
//        if (login(userName, passWord)) {
//            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//            return true;
//        } else {
//            Toast.makeText(LoginActivity.this, "用户名密码不正确", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }

//    public boolean login(String username, String password) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = "select * from Login where Login_UserName=? and Login_Password=?";
//        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
//        if (cursor.moveToFirst()) {
//            cursor.close();
//            return true;
//        }
//        return false;
//    }

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

                //获取用户名密码
                final String username = etUsername.getText().toString().trim();
                final String pwd = etPassword.getText().toString().trim();

                new Thread() {
                    public void run() {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("username", username);
                            jsonObject.put("password", pwd);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        String s = HttpHandler.executeHttpPost("http://192.168.43.34:8082/login", jsonObject.toString());
//                        if ("登录成功".equals(s)) {
//
//                            Intent intent_1 = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent_1);
//
//                        } else {
//                            showToast(s);
//                        }
                        String s = HttpHandler.executeHttpPost("http://192.168.43.34:8082/login", jsonObject.toString());
                        Gson gson = new Gson();
                        if (s.contains("error")) {
                            try {
                                showToast(new JSONObject(s).getString("error"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            UserLoginBean user = gson.fromJson(s, UserLoginBean.class);
                            SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username", username);
                            editor.putString("password", pwd);
                            editor.putInt("id", user.getUserid());
                            editor.commit();
                            editor.apply();
                            Intent intent_1 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent_1);
                        }
                    }
                }.start();
                break;


            default:
                break;
        }
    }

    //                Intent i2 = new Intent(this, MainActivity.class);
//                startActivity(i2);
//                finish();
//                }
//                break;
//            case R.id.bt_trd:
//                Intent i3 = new Intent(this,PersonalInformationActivity.class);
//                startActivity(i3);
//                finish();
//                break;

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "登录失败,用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

