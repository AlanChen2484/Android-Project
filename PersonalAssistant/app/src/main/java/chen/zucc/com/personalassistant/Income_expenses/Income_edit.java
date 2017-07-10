package chen.zucc.com.personalassistant.Income_expenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import chen.zucc.com.personalassistant.R;

public class Income_edit extends AppCompatActivity implements View.OnClickListener {
    private IncomeFragmentOne f1;
    private IncomeFragmentTwo f2;
    //底部三个按钮
    private Button foot1;
    private Button foot2;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_edit);
        foot1 = (Button) findViewById(R.id.btn1);
        foot2 = (Button) findViewById(R.id.btn2);
        foot1.setOnClickListener(this);
        foot2.setOnClickListener(this);
        //第一次初始化首页默认显示第一个fragment
        initFragment1();
         ImageButton button=(ImageButton) findViewById(R.id.imageBtn_1);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent_1 = new Intent(Income_edit.this, Income_expensesActivity.class);
                 startActivity(intent_1);
             }
         });
    }
    private void initFragment1(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(f1 == null){
            f1= new IncomeFragmentOne();
            transaction.add(R.id.income_frame_layout, f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);
        transaction.commit();
    }
    //显示第二个fragment
    private void initFragment2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f2 == null){
            f2 = new IncomeFragmentTwo();
            transaction.add(R.id.income_frame_layout,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();
    }
    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
    }
    @Override
    public void onClick(View v) {
        if(v == foot1){
            initFragment1();
        }else if(v == foot2){
            initFragment2();}

    }
}
