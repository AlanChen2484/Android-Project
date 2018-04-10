package com.example.quxing.quxing.Fabu;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.github.kimkevin.slidingicontablayout.wigets.SlidingIconTabLayout;

/**
 * Created by 陈若韬 on 2018/3/31.
 */

public class FabuTabAdapter extends FragmentPagerAdapter implements SlidingIconTabLayout.TabIconProvider {

    private static final String TAG = FabuTabAdapter.class.getSimpleName();

    private static final String iconTxt[] = {
            "已发布",
            "待编辑"
    };

    public FabuTabAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public Fragment getItem(int position) {
        return FabuFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return iconTxt.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return iconTxt[position];
    }

    @Override
    public int getPageIconResId(int position) {
        return 1;
    }

}

