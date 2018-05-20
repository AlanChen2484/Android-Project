package com.example.quxing.quxing.Auxiliary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.bumptech.glide.Glide;
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.login.LoginActivity;
import com.example.quxing.quxing.login.RegisterActivity;
import com.example.quxing.quxing.model.ItemInfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.baidu.mapapi.BMapManager.getContext;


public class Item_DetailsActivity extends AppCompatActivity implements View.OnClickListener {

//    int []images = new int[]
//            {
//                    R.mipmap.ic_wode_collection,
//                    R.mipmap.ic_wode_collection1,
//            };
    //    int currImg = 0;

    private Context context;
    TextView address;
    private static String address1 = null;
    private TextView item_name, item_join, item_collection, item_time,
            item_address, item_phone, item_details, item_money, item_label;
    private String itemname;
    private int itemid;
    private ItemInfoBean itemInfoBean;
    private String username, password;
    private int userid;
    Button join;

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
        address = (TextView) findViewById(R.id.item_address);
        address.setOnClickListener(this);
        join = (Button) findViewById(R.id.join);
        join.setOnClickListener(this);

        Item_DetailsActivity.address1 = address.getText().toString();

        item_name = (TextView) findViewById(R.id.item_name);
        item_join = (TextView) findViewById(R.id.item_join_number);
        item_collection = (TextView) findViewById(R.id.item_collection_number);
        item_time = (TextView) findViewById(R.id.item_time);
        item_address = (TextView) findViewById(R.id.item_address);
        item_phone = (TextView) findViewById(R.id.phone);
        item_money = (TextView) findViewById(R.id.money);
        item_details = (TextView) findViewById(R.id.item_details);
        item_label = (TextView) findViewById(R.id.item_label);
//        imageView = (ImageView) findViewById(R.id.itemimage);
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        userid = pref.getInt("userid", 1);
        username = pref.getString("username", "");

        Intent intent = getIntent();
        itemname = intent.getStringExtra("itemname");
//        itemid = Integer.parseInt(intent.getStringExtra("itemid"));

        new Thread() {
            public void run() {
                parseJOSNWithGSON();
            }
        }.start();
    }

    private void parseJOSNWithGSON() {
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/itemget/getname/" + itemname);
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String itemname1 = object.getString("itemname");
                String follow1 = object.getString("follownumber");
                String enrolment1 = object.getString("enrolment");
                String time1 = object.getString("itemtime");
                String address1 = object.getString("address");
                String phone1 = object.getString("callnumber");
                String money1 = object.getString("money");
                String details1 = object.getString("details");
                String label = object.getString("itemlabel");
                Integer itemid1 = object.getInt("itemid");
//                itemid = object.getInt("itemid");

//                SimpleDateFormat dateFormater_1 = new SimpleDateFormat("yyyy-MM-dd");
//                Date date_1= new Date(object.getLong("starttime"));
//                actdis_starttime.setText(dateFormater_1.format(date_1));
//                SimpleDateFormat dateFormater_2 = new SimpleDateFormat("yyyy-MM-dd");
//                Date date_2= new Date(object.getLong("starttime"));
//                actdis_endtime.setText(dateFormater_2.format(date_2));
                showUI(itemname1);
                showUI1(follow1);
                showUI2(enrolment1);
                showUI3(time1);
                showUI4(address1);
                showUI5(phone1);
                showUI6(money1);
                showUI7(details1);
                showUI8(sprinner(label));
                showImg(itemid1);
//                actdis_content.setText(content);
//                actdis_sourcename.setText(source);
//                actdis_address.setText(address);
//                actdis_state.setText(state);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                break;
            case R.id.rl_address:
//                startNavi();
                foundAddress();
                break;
            case R.id.join:
                joinitem.start();
//                Toast.makeText(Item_DetailsActivity.this, "已成功参与活动", Toast.LENGTH_SHORT).show();

//            case R.id.main_new:
//                Intent intent_3 = new Intent(this,Main_NewActivity.class);
//                startActivity(intent_3);
//                break;
            default:
                break;
        }
    }

    //活动存储至连接表——参与活动
    Thread joinitem = new Thread() {
        public void run() {
            int itemid1 = getItemid();
            JSONObject jsonObject = new JSONObject();//构建即直接实例化一个JSONObject对象
            try {
                //调用其put()方法,将数据写入
                jsonObject.put("itemid", itemid1);
                jsonObject.put("itemname", itemname);
                jsonObject.put("username", username);
                jsonObject.put("userid", userid);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String s = HttpHandler.executeHttpPost("http://192.168.43.34:8082/usertoitem", jsonObject.toString());
            if ("success".equals(s)) {
                showToast("活动已参与");
            } else {
                showToast("已报名参加活动，请勿重复参加");
            }
        }
    };

    //获取itemid
    public int getItemid() {
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/itemget/getname/" + itemname);
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int itemid1 = object.getInt("itemid");
                return itemid1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Item_DetailsActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void foundAddress() {
        Intent i1 = new Intent();
        // 百度地图地址解析
        i1.setData(Uri.parse("baidumap://map/geocoder?src=openApiDemo&address=" + item_address.getText()));
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

    //取下拉框的活动标签
    public String sprinner(final String text) {
        if (text.equals("1"))
            return "教育";
        else if (text.equals("2")) {
            return "文艺";
        } else if (text.equals("3")) {
            return "户外";
        } else if (text.equals("4")) {
            return "旅行";
        } else if (text.equals("5")) {
            return "校园";
        } else if (text.equals("6")) {
            return "交友";
        } else if (text.equals("7")) {
            return "游戏";
        } else {
            return "教育";
        }
    }

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

    private void showImg(final Integer text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = (ImageView) findViewById(R.id.itemimage);
                Glide.with(Item_DetailsActivity.this)
                        .load("http://192.168.43.34:8082/getLocalImage/itemid/" + text)
                        .placeholder(R.drawable.ic_bg_collection)
                        .into(imageView);

            }
        });
    }

    private void showUI(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_name.setText(text);
            }
        });
    }

    private void showUI1(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_collection.setText(text);
            }
        });
    }

    private void showUI2(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_join.setText(text);
            }
        });
    }

    private void showUI3(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_time.setText(text);
            }
        });
    }

    private void showUI4(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_address.setText(text);
            }
        });
    }

    private void showUI5(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_phone.setText(text);
            }
        });
    }

    private void showUI6(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_money.setText(text);
            }
        });
    }

    private void showUI7(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_details.setText(text);
            }
        });
    }

    private void showUI8(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_label.setText(text);
            }
        });
    }
}
