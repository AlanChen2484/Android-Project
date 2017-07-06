package chen.zucc.com.personalassistant.Personal_homepage;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import chen.zucc.com.personalassistant.Income_expenses.Income_expensesActivity;
import chen.zucc.com.personalassistant.Manage_money_matters.Manager_money_mattersActivity;
import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.Schedule.ScheduleActivity;
import chen.zucc.com.personalassistant.util.DensityUtil;

public class homepage extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 3;
    private ImageButton imageButton;
    private ImageButton imageButton2;

//    PopupMenu popupMenu;
//    Menu menu;
//    private String TAG = BottomNavigationBarDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//为了隐藏手机状态栏

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.RED)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_schedule, "日程安排").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_bill, "账本记录").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_managemoney, "财富管理").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_person, "个人主页").setActiveColorResource(R.color.blue))

                .setFirstSelectedPosition(lastSelectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
                 bottomNavigationBar.setTabSelectedListener(this);

        ButterKnife.bind(this);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    show2();
                    //break;
                }
        });

    }

//    @OnClick({
//            R.id.imageButton2})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.imageButton2:
//                show2();
//                break;
//        }
//    }

//        bottomNavigationBar.setTabSelectedListener(this);
//        Dialog mCameraDialog = new Dialog(this, R.style.my_dialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
//                R.layout.layout_camera_control, null);
//        root.findViewById(R.id.btn_open_camera).setOnClickListener(btnlistener);
//        root.findViewById(R.id.btn_choose_img).setOnClickListener(btnlistener);
//        root.findViewById(R.id.btn_cancel).setOnClickListener(btnlistener);
//        mCameraDialog.setContentView(root);
//        Window dialogWindow = mCameraDialog.getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = 120; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//        lp.alpha = 9f; // 透明度
//        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
//        mCameraDialog.show();

//        popupMenu = new PopupMenu(this, findViewById(R.id.imageButton2));
//        menu = popupMenu.getMenu();
//
////        // 通过代码添加菜单项
////        menu.add(Menu.NONE, Menu.FIRST + 0, 0, "复制");
////        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "粘贴");
//
//        // 通过XML文件添加菜单项
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_homepage, menu);
//    }
//
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.night:
//                Toast.makeText(homepage.this, "夜间模式",
//                        Toast.LENGTH_LONG).show();
//                break;
//            case R.id.open:
//                Toast.makeText(homepage.this, "新建",
//                        Toast.LENGTH_LONG).show();
//                break;
//            default:
//                break;
//        }
//        return false;
//    }
//
//
//
//public void popupmenu(View v) {
//        popupMenu.show();
//        }
//

    private void show2() {
        Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_content_circle, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(this, 16f);
        params.bottomMargin = DensityUtil.dp2px(this, 8f);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
    }


    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position) {
            case 0:
                Intent intent0 = new Intent(this, ScheduleActivity.class);
                this.startActivity(intent0);
                break;

            case 1:
                Intent intent1 = new Intent(this, Income_expensesActivity.class);
                this.startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(this, Manager_money_mattersActivity.class);
                this.startActivity(intent2);
                break;

            case 3:
                Intent intent3 = new Intent(this, homepage.class);
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