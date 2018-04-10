package com.example.quxing.quxing.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.quxing.quxing.Auxiliary.Item_DetailsActivity;
import com.example.quxing.quxing.Fabu.FabuActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Wode.WodeActivity;
import com.example.quxing.quxing.Xiaoxi.XiaoxiActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    //RecycleView
    private List<Item> itemList = new ArrayList<>();

    //轮播图ViewPager
    private ViewPager mViewPager;
    private TextView mTvPagerTitle;

    private List<ImageView> mImageList;//轮播的图片集合
    private String[] mImageTitles;//标题集合
    private int previousPosition = 0;//前一个被选中的position
    private List<View> mDots;//小点

    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIOME = 5000;//间隔时间

    // 在values文件假下创建了pager_image_ids.xml文件，并定义了4张轮播图对应的id，用于点击事件
    private int[] imgae_ids = new int[]{R.id.pager_image1, R.id.pager_image2,
            R.id.pager_image3, R.id.pager_image4};

    //状态栏
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;//定义页码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

////在这里调用状态栏辅助类
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_main);
        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //RecycleView
        initItems();//初始化滚动条数据
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

//BottomNavigationBar-底部导航栏
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.RED)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.tuijian, "推荐").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.fabu, "发布").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.xiaoxi, "消息").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.wode, "我的").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);

    }


    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position) {
//            case 0:
//                Intent intent0 = new Intent(this, MainActivity.class);
//                this.startActivity(intent0);
//                finish();
//                break;

            case 1:
                Intent intent1 = new Intent(this, FabuActivity.class);
                this.startActivity(intent1);
                finish();
                break;

            case 2:
                Intent intent2 = new Intent(this, XiaoxiActivity.class);
                this.startActivity(intent2);
                finish();
                break;

            case 3:
                Intent intent3 = new Intent(this, WodeActivity.class);
                this.startActivity(intent3);
                finish();
                break;
        }

    }

    public void onTabUnselected(int position) {

    }

    public void onTabReselected(int position) {

    }

//添加按钮图标
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar1, menu);
//        return true;
//    }

//    public Toolbar initToolbar(int id, int titleId, int titleString) {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
////        toolbar.setTitle("");
//        TextView textView = (TextView) findViewById(R.id.toolbar_title1);
//        textView.setText(titleString);
//        setSupportActionBar(toolbar);
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        return toolbar;
//    }

    private void initItems(){
        for (int i = 0;i < 2; i++){
            Item Act1 = new Item("一个很长的标题一个很长的标题。",R.drawable.ic_wode_personalimage);
            itemList.add(Act1);
            Item Act2 = new Item("Act2",R.drawable.ic_wode_personalbackground);
            itemList.add(Act2);
            Item Act3 = new Item("Act3",R.mipmap.quxing);
            itemList.add(Act3);
            Item Act4 = new Item("Act4",R.mipmap.quxing);
            itemList.add(Act4);
            Item Act5 = new Item("Act5",R.mipmap.quxing);
            itemList.add(Act5);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_city:
                Intent intent_1 = new Intent(this,Main_CityActivity.class);
                startActivity(intent_1);
                break;
            case R.id.main_hot:
                Intent intent_2 = new Intent(this,Main_HotActivity.class);
                startActivity(intent_2);
                break;
            case R.id.main_new:
                Intent intent_3 = new Intent(this,Main_NewActivity.class);
                startActivity(intent_3);
                break;
            default:
                break;
        }
    }

    /**
     * 第一步、初始化控件
     */
    public void init() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTvPagerTitle = (TextView) findViewById(R.id.tv_pager_title);
        initData();//初始化数据
        initView();//初始化View，设置适配器
        autoPlayView();//开启线程，自动播放
    }

    /**
     * 第二步、初始化数据（图片、标题、点击事件）
     */
    public void initData() {
        //初始化标题列表和图片
        mImageTitles = new String[]{"这是一个好看的标题1","这是一个优美的标题2","这是一个快乐的标题3","这是一个开心的标题4"};
        int[] imageRess = new int[]{R.drawable.ic_wode_personalbackground,R.drawable.ic_wode_personalimage,
                R.drawable.ic_wode_personalbackground,R.drawable.ic_wode_personalimage};

        //添加图片到图片列表里
        mImageList = new ArrayList<>();
        ImageView iv;
        for (int i = 0; i < imageRess.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageRess[i]);//设置图片
            iv.setId(imgae_ids[i]);//顺便给图片设置id
            iv.setOnClickListener(new pagerImageOnClick());//设置图片点击事件
            mImageList.add(iv);
        }

        //添加轮播点
        LinearLayout linearLayoutDots = (LinearLayout) findViewById(R.id.lineLayout_dot);
        mDots = addDots(linearLayoutDots,fromResToDrawable(this,R.drawable.ic_dot_normal),mImageList.size());//其中fromResToDrawable()方法是我自定义的，目的是将资源文件转成Drawable


    }

    //图片点击事件
    private class pagerImageOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pager_image1:
                    Intent intent_1 =new Intent(MainActivity.this,Item_DetailsActivity.class);
                    startActivity(intent_1);

                    Toast.makeText(MainActivity.this, "图片1被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image2:
                    Toast.makeText(MainActivity.this, "图片2被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image3:
                    Toast.makeText(MainActivity.this, "图片3被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image4:
                    Toast.makeText(MainActivity.this, "图片4被点击", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    /**
     *  第三步、给PagerViw设置适配器，并实现自动轮播功能
     */
    public void initView(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mImageList, mViewPager);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(mImageList.size()==0){
                    return;
                }
                //伪无限循环，滑到最后一张图片又从新进入第一张图片
                int newPosition = position % (mImageList.size());
                // 把当前选中的点给切换了, 还有描述信息也切换
                mTvPagerTitle.setText(mImageTitles[newPosition]);//图片下面设置显示文本
                //设置轮播点
                LinearLayout.LayoutParams newDotParams = (LinearLayout.LayoutParams) mDots.get(newPosition).getLayoutParams();
                newDotParams.width = 24;
                newDotParams.height = 24;

                LinearLayout.LayoutParams oldDotParams = (LinearLayout.LayoutParams) mDots.get(previousPosition).getLayoutParams();
                oldDotParams.width = 16;
                oldDotParams.height = 16;

                // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
                previousPosition = newPosition;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setFirstLocation();
    }

    /**
     * 第四步：设置刚打开app时显示的图片和文字
     */
    private void setFirstLocation() {
        mTvPagerTitle.setText(mImageTitles[previousPosition]);
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) % (mImageList.size()+1);
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        mViewPager.setCurrentItem(currentPosition);
    }

    /**
     * 第五步: 设置自动播放,每隔PAGER_TIOME秒换一张图片
     */
    private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                    SystemClock.sleep(PAGER_TIOME);
                }
            }
        }).start();
    }

    /**
     * 资源图片转Drawable
     * @param context
     * @param resId
     * @return
     */
    public Drawable fromResToDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }


    /**
     * 动态添加一个点
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount 设置
     * @return
     */
    public int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(this);
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4,0,4,0);
        dot.setLayoutParams(dotParams);
        dot.setBackground(backgount);
        dot.setId(View.generateViewId());
        linearLayout.addView(dot);
        return dot.getId();
    }

    /**
     * 添加多个轮播小点到横向线性布局
     * @param linearLayout
     * @param backgount
     * @param number
     * @return
     */
    public List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number){
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout,backgount);
            dots.add(findViewById(dotId));
        }
        return dots;
    }

}


