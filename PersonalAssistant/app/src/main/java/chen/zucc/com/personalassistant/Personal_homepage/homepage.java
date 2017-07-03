package chen.zucc.com.personalassistant.Personal_homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import chen.zucc.com.personalassistant.R;

public class homepage extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = BottomNavigationBarDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//为了隐藏手机状态栏
        setContentView(R.layout.activity_homepage);
    }

    BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
            .addItem(new BottomNavigationItem(R.mipmap.ic_action_home, "Home").setActiveColorResource(R.color.cyan_500))
            .addItem(new BottomNavigationItem(R.mipmap.ic_action_find , "find").setActiveColorResource(R.color.colorPrimaryDark))
            .addItem(new BottomNavigationItem(R.mipmap.ic_action_sx, "sixin").setActiveColorResource(R.color.colorPrimary1))
            .addItem(new BottomNavigationItem(R.mipmap.ic_action_person, "person").setActiveColorResource(R.color.colorPrimaryDark1))  //调颜色
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
            .setFirstSelectedPosition(lastSelectedPosition)
            .initialise();

        bottomNavigationBar.setTabSelectedListener(this);//底部导航栏监听

}
    //底部导航栏点击效果
    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position){
            case 0:
                Intent intent0 = new Intent(this, HomeActivity.class);
                this.startActivity(intent0);
                break;

            case 1:
                Intent intent1 = new Intent(this, FoundActivity.class);
                this.startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(this, EmailActivity.class);
                this.startActivity(intent2);
                break;

            case 3:
                Intent intent3 = new Intent(this, PersonalInformationActivity.class);
                this.startActivity(intent3);
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}