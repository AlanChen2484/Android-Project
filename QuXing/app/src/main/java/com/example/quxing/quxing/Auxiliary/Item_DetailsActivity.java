package com.example.quxing.quxing.Auxiliary;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.R;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.BMapManager.getContext;


public class Item_DetailsActivity extends AppCompatActivity {

//    int []images = new int[]
//            {
//                    R.mipmap.ic_wode_collection,
//                    R.mipmap.ic_wode_collection1,
//            };

//    int currImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ImageView pic = (ImageView)findViewById(R.id.item_collection);
//        final ImageView image = new ImageView(this);
//
//        image.setImageResource(images[0]);
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                image.setImageResource(images[++currImg % images.length]);
//            }
//        });

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collection:

                Toast.makeText(Item_DetailsActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
//                TextView run_action;
//
//                run_action = (TextView) findViewById(R.id.collection_text);//
//
//                run_action.setOnClickListener((View.OnClickListener) this);
//                if (isActive) {
//                    isActive = false;
//                    run_action.setText("收藏");
//                }else{
//                    isActive = true;
//                    run_action.setText("已收藏");
//                }
//                break;
            case R.id.talking:

//                startNavi();
                foundAddress();

//                Intent intent_2 = new Intent(this,Main_HotActivity.class);
//                startActivity(intent_2);
//                break;
//            case R.id.main_new:
//                Intent intent_3 = new Intent(this,Main_NewActivity.class);
//                startActivity(intent_3);
//                break;
            default:
                break;
        }
    }

    public void foundAddress() {
        Intent i1 = new Intent();
// 百度地图地址解析

        i1.setData(Uri.parse("baidumap://map/geocoder?src=openApiDemo&address=杭州科技馆"));

        startActivity(i1);
    }

//    /**
//     * 启动百度App进行导航
//     * @param address 目的地
//     * @param lat 必填 纬度
//     * @param lon 必填 经度
//     */
//    public static void goToBaiduActivity(Context context, String address, double lat, double lon) {
//        double[] doubles = gcj02_To_Bd09(lat, lon);
//        //启动路径规划页面
//        Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("baidumap://map/direction?origin="+ doubles[0] +","+ doubles[1] +"&destination="+ address +"&mode=driving"));
//        context.startActivity(naviIntent);
//    }

//    /**
//     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
//     *
//     * @param lat
//     * @param lon
//     */
//    public static double[] gcj02_To_Bd09(double lat, double lon) {
//        double x = lon, y = lat;
//        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
//        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
//        double tempLat = z * Math.sin(theta) + 0.006;
//        double tempLon = z * Math.cos(theta) + 0.0065;
//        double[] gps = {tempLat, tempLon};
//        return gps;
//    }


//    //开启百度导航
//    public void startNavi(){
//        //百度地图,从起点是
//        LatLng ll_location = new LatLng(10,10);
//        //终点是
//        LatLng ll = new LatLng(30,10);
//        NaviParaOption para = new NaviParaOption();
//        para.startPoint(ll_location);
//        para.startName("从这里开始");
//        para.endPoint(ll);
//        para.endName("到这里结束");
//        try {
//            BaiduMapNavigation.openBaiduMapNavi(para, getContext());
//        } catch (BaiduMapAppNotSupportNaviException e) {
//            e.printStackTrace();
//            ToastUtil.showToast("您尚未安装百度地图或地图版本过低");
//        }
//    }
//
//    //验证各种导航地图是否安装
//    public static boolean isAvilible(Context context, String packageName) {
//        //获取packagemanager
//        final PackageManager packageManager = context.getPackageManager();
//        //获取所有已安装程序的包信息
//        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
//        //用于存储所有已安装程序的包名
//        List<String> packageNames = new ArrayList<String>();
//        //从pinfo中将包名字逐一取出，压入pName list中
//        if (packageInfos != null) {
//            for (int i = 0; i < packageInfos.size(); i++) {
//                String packName = packageInfos.get(i).packageName;
//                packageNames.add(packName);
//            }
//        }
//        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
//        return packageNames.contains(packageName);
//    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(Item_DetailsActivity.this, MainActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
