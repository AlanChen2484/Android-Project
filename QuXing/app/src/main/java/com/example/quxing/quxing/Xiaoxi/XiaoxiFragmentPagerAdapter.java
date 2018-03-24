package com.example.quxing.quxing.Xiaoxi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.quxing.quxing.Xiaoxi.XiaoxiActivity;

/**
 * Created by 陈若韬 on 2018/3/7.
 */

public class XiaoxiFragmentPagerAdapter extends FragmentPagerAdapter {


    private final int PAGER_COUNT = 2;
    private MyFragment1 myFragment1 = null;
    private MyFragment2 myFragment2 = null;



    public XiaoxiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case XiaoxiActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case XiaoxiActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
        }
        return fragment;
    }


}
