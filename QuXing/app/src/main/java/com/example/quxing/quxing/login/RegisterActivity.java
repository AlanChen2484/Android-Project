package com.example.quxing.quxing.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quxing.quxing.DataBaseHelper.DataBaseHelper;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

//    private DataBaseHelper dbHelper;

    FloatingActionButton fab;
    CardView cvAdd;
    Button btregister;

    private EditText username1;
    private EditText userpwd1;
    private EditText userpwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        cvAdd = (CardView) findViewById(R.id.cv_add);
        btregister = (Button) findViewById(R.id.bt_register);

        username1=(EditText)findViewById(R.id.et_username_register);

        userpwd1=(EditText)findViewById(R.id.et_password_register);

        userpwd2=(EditText)findViewById(R.id.et_repeat_password);

        ShowEnterAnimation();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });

        btregister.setOnClickListener(this);

//        dbHelper = new DataBaseHelper(this, "QuXingdb.db", null, 1);

    }


    public void onClick(View v) {
        final String username = username1.getText().toString().trim();
        final String userpwd = userpwd1.getText().toString().trim();//获得对象的字段的值,然后转成string类型
        switch (v.getId()) {
            case R.id.bt_register:
                if (checkEdit()) {
                    new Thread() {
                        public void run() {
                            JSONObject jsonObject = new JSONObject();//构建即直接实例化一个JSONObject对象
                            try {
                                //调用其put()方法,将数据写入
                                jsonObject.put("password", userpwd);
                                jsonObject.put("username", username);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String s = HttpHandler.executeHttpPost("http://192.168.43.34:8081/register", jsonObject.toString());

                            if ("success".equals(s)) {
                                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent1);
                            } else {
                                showToast(s);
                            }
                        }
                    }.start();
                }
                break;
            default:
                break;
        }

    }

    public boolean checkEdit() {

        if (username1.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userpwd1.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userpwd2.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "再次输入密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!userpwd2.getText().toString().equals(userpwd1.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "两次输入的密码不同", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public boolean logon() {
//        //SQLiteDatabase db=dbHelper.getWritableDatabase();
//        EditText editText1 = (EditText) findViewById(R.id.et_username);
//        EditText editText2 = (EditText) findViewById(R.id.et_password);
//        EditText editText3 = (EditText) findViewById(R.id.et_repeatpassword);
//        String newname = editText1.getText().toString();
//        String password1 = editText2.getText().toString();
//        String password2 = editText3.getText().toString();
//        if (CheckIsDataAlreadyInDBorNot(newname)) {
//            Toast.makeText(this, "该用户名已被注册，注册失败", Toast.LENGTH_SHORT).show();
//            return false;
//        } else {
//            if (password2.equals(password1)) {
//                if (register(newname, password1)) {
//                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//            } else {
//                Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }
//        return false;
//    }

//    //向数据库插入数据
//    public boolean register(String username, String password) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = "insert into Login(Login_UserName,Login_Password) value(?,?)";
//        Object obj[] = {username, password};
//        db.execSQL(sql, obj);
//        ContentValues values = new ContentValues();
//        values.put("Login_UserName", username);
//        values.put("Login_Password", password);
//        db.insert("Login", null, values);
//        db.close();
//
////        db.execSQL("insert into Login (Login_UserName,Login_Password) values (?,?)", new String[]{username, password});
//
//        return true;
//    }

//    //检验用户名是否已存在
//    public boolean CheckIsDataAlreadyInDBorNot(String value) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String Query = "Select * from Login where Login_UserName =?";
//        Cursor cursor = db.rawQuery(Query, new String[]{value});
//        if (cursor.getCount() > 0) {
//            cursor.close();
//            return true;
//        }
//        cursor.close();
//        return false;
//    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.mipmap.ic_action_plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void onBackPressed() {
        animateRevealClose();
    }
}
