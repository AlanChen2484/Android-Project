package chen.zucc.com.personalassistant.Manage_money_matters;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.github.kimkevin.slidingicontablayout.wigets.SlidingIconTabLayout;

import chen.zucc.com.personalassistant.R;

public class Asset_AnalysisActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MainTabAdapter mAdapter;
    int lastSelectedPosition = 2;
    private ImageButton imageButtonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_asset__analysis);

        mAdapter = new MainTabAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);

        imageButtonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SlidingIconTabLayout mSlidingTabLayout = (SlidingIconTabLayout) findViewById(R.id.tabs);

        mSlidingTabLayout.setCustomTabView(R.layout.tab_txt_layout, R.id.tab_name_txt);//放的是导航栏的内容

        mSlidingTabLayout.setCustomTabColorizer(new SlidingIconTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.grey);  //导航栏下面的横线的颜色
            }
        });

        mSlidingTabLayout.setViewPager(mViewPager);

    }
}
