package chen.zucc.com.personalassistant.Personal_homepage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import chen.zucc.com.personalassistant.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountsActivity extends AppCompatActivity {
    private LinearLayout phone_set;
    private LinearLayout email_set;
    private LinearLayout qq_set;
    private LinearLayout password_set;
    private LinearLayout wechat_set;

    private TextView phone_text;
    private TextView email_text;
    private TextView password_text;
    private TextView qq_text;
    private TextView wechat_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//为了隐藏手机状态栏

        toolbar.setTitle(R.string.account_set);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initView();

        phone_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone_text.length() == 0)
                    setPhoneDialog("绑定手机号");
                else setPhoneDialog("修改手机号");
            }
        });
        email_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email_text.length() == 0)
                    setEmailDialog("绑定邮箱");
                else setEmailDialog("修改邮箱");
            }
        });
        password_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password_text.length() == 0)
                    setPasswordDialog("设置密码");
                else setPasswordDialog("修改密码");
            }
        });
        qq_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qq_text.length() == 0)
                    setQqDialog("设置QQ");
                else setQqDialog("修改QQ");
            }
        });
        wechat_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wechat_text.length() == 0)
                    setWechatDialog("设置WeChat");
                else setWechatDialog("修改WeChat");
            }
        });
    }

    // 初始化
    private void initView() {

        phone_set = (LinearLayout) findViewById(R.id.phone_set);
        phone_text = (TextView) findViewById(R.id.phone_text);

        email_set = (LinearLayout) findViewById(R.id.email_set);
        email_text = (TextView) findViewById(R.id.email_text);

        password_set = (LinearLayout) findViewById(R.id.password_set);
        password_text = (TextView) findViewById(R.id.password_text);

        qq_set = (LinearLayout) findViewById(R.id.qq_set);
        qq_text = (TextView) findViewById(R.id.qq_text);

        wechat_set = (LinearLayout) findViewById(R.id.wechat_set);
        wechat_text = (TextView) findViewById(R.id.wechat_text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(AccountsActivity.this, SettingActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //手机号设置对话框
    private void setPhoneDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.phone_set_dialog, null);
        final EditText phone_edit = (EditText) linearLayout.findViewById(R.id.phone_edit);
        final String phone_reg = getResources().getString(R.string.phone_reg);
        if (phone_text.getText().length() != 0) {
            phone_edit.setHint(phone_text.getText().toString());
        }
        builder.setView(linearLayout)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phone = phone_edit.getText().toString();
                        if (verification(phone_reg, phone))
                            phone_text.setText(phone);
                        else
                            Toast.makeText(AccountsActivity.this, "请输入有效的手机号", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    //Email设置对话框
    private void setEmailDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.email_set_dialog, null);
        final EditText email_edit = (EditText) linearLayout.findViewById(R.id.email_edit);
        final String email_reg = getResources().getString(R.string.email_reg);
        if (email_text.getText().length() != 0)
            email_edit.setHint(email_text.getText().toString());
        builder.setView(linearLayout)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = email_edit.getText().toString();
                        if (verification(email_reg, email)) {
                            email_text.setText(email);
                        } else
                            Toast.makeText(AccountsActivity.this, "请输入合法的邮箱", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    //密码设置对话框
    private void setPasswordDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.password_set_dialog, null);
        final EditText password_edit = (EditText) linearLayout.findViewById(R.id.password_edit);
        final String password_reg = getResources().getString(R.string.password_reg);
        final EditText password_edit_confirm = (EditText) linearLayout.findViewById(R.id.password_edit_confirm);
        builder.setView(linearLayout)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String password = password_edit.getText().toString();
                        String password_confirm = password_edit_confirm.getText().toString();
                        if (!verification(password_reg, password)) {
                            Toast.makeText(AccountsActivity.this, "请输入合法的密码，" +
                                    "密码的强度必须是包含大小写字母和数字的组合，" +
                                    "不能使用特殊字符，长度在8-10之间", Toast.LENGTH_SHORT).show();
                        } else if (!(password_confirm.equals(password))) {
                            Toast.makeText(AccountsActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                        } else password_text.setText("******");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    //QQ设置对话框
    private void setQqDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.qq_set_dialog, null);
        final EditText qq_edit = (EditText) linearLayout.findViewById(R.id.qq_edit);
        final String qq_reg = getResources().getString(R.string.qq_reg);
        if (qq_text.getText().length() != 0) {
            qq_edit.setHint(qq_text.getText().toString());
        }
        builder.setView(linearLayout)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String qq = qq_edit.getText().toString();
                        if (verification(qq_reg, qq))
                            qq_text.setText(qq);
                        else
                            Toast.makeText(AccountsActivity.this, "请输入合法的qq", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    //Wechat设置对话框
    private void setWechatDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.wechat_set_dialog, null);
        final EditText wechat_edit = (EditText) linearLayout.findViewById(R.id.wechat_edit);
        final String wechat_reg = getResources().getString(R.string.wechat_reg);
        if (wechat_text.getText().length() != 0) {
            wechat_edit.setHint(wechat_text.getText().toString());
        }
        builder.setView(linearLayout)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String wechat = wechat_edit.getText().toString();
                        if (verification(wechat_reg, wechat))
                            wechat_text.setText(wechat);
                        else
                            Toast.makeText(AccountsActivity.this, "请输入合法的wechat,微信账号仅支持6-20个字母、数字、下划线或减号，以字母开头", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    /**
     * @param regular 正则表达式
     * @param str     要校验的字符串
     * @return 校验值
     * @see verification 校验函数
     */
    public boolean verification(String regular, String str) {
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}