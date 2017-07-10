package chen.zucc.com.personalassistant.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.Schedule.ScheduleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
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
        dbHelper = new DataBaseHelper(this, "BookStore2.db", null, 1);
        dbHelper.getWritableDatabase();


        etUsername = (EditText)findViewById(R.id.et_username);
        etPassword = (EditText)findViewById(R.id.et_password);
        btGo = (Button)findViewById(R.id.bt_go);
        btTrd = (Button)findViewById(R.id.bt_trd);
        cv = (CardView)findViewById(R.id.cv);
        fab = (FloatingActionButton)findViewById(R.id.fab);
    }



    @OnClick({R.id.bt_go, R.id.fab, R.id.bt_trd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent,options.toBundle());

                break;
            case R.id.bt_go:

                Intent i2 = new Intent(this,ScheduleActivity.class);
                startActivity(i2);
                finish();

                break;
            case R.id.bt_trd:

//                Intent i3 = new Intent(this,PersonalInformationActivity.class);
//                startActivity(i3);
//                finish();

                break;
        }
    }
}