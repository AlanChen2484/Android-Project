package com.example.quxing.quxing.Fabu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quxing.quxing.Auxiliary.Item_DetailsActivity;
import com.example.quxing.quxing.DataBaseHelper.DataBaseHelper;
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.Main.Main_CityActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.Wode.Wode_AboutActivity;
import com.example.quxing.quxing.login.LoginActivity;
import com.example.quxing.quxing.login.RegisterActivity;
import com.example.quxing.quxing.model.ItembackupInfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.jar.Manifest;

public class Fabu_AddItemActivity extends AppCompatActivity implements View.OnClickListener {
    private DataBaseHelper dbHelper;
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int GET_PHOTO = 3;
    public static final int GET_ADDRESS = 4;
    public static int itemid;
    private Button takePhoto;
    private Button getPhoto;
    private ImageView picture;
    private Uri headImgUri;
    Intent i;
    File f;
    private String currentPath = null;
    public static final int REQUEST_CODE_GET_PHOTO = 1;
    private SQLiteDatabase db;
    private ImageButton btn;
    private ImageButton btn2;
    private TextView tv;
    private TextView tv2;
    private LinearLayout l1;
    private LinearLayout l2;
    private EditText itemname1;
    private EditText hostname1;
    private EditText address1;
    private EditText callnumber1;
    private EditText details1;
    private EditText money1;
    private Spinner itemlabel1;
    //    private SharedPreferences pref;
//    private SharedPreferences.Editor editor;
    private String username, password;
    private int userid;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu__additem);
        ItembackupInfoBean itembackupInfoBean = (ItembackupInfoBean) getIntent().getSerializableExtra("data");
        dbHelper = new DataBaseHelper(this, "QuXing.db", null, 1);//创建数据库

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_fabu_item);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        takePhoto = (Button) findViewById(R.id.takephoto_imgbutton);
        getPhoto = (Button) findViewById(R.id.add_imgbutton);
        picture = (ImageView) findViewById(R.id.item_image);
        takePhoto.setOnClickListener(this);
        getPhoto.setOnClickListener(this);

        Button address = (Button) findViewById(R.id.button_address);
        Button put = (Button) findViewById(R.id.button_put);
        Button save = (Button) findViewById(R.id.button_save);

        address.setOnClickListener(this);
        put.setOnClickListener(this);
        save.setOnClickListener(this);

        itemname1 = (EditText) findViewById(R.id.editText_itemname);
        hostname1 = (EditText) findViewById(R.id.editText_host);
        address1 = (EditText) findViewById(R.id.editText_address);
        callnumber1 = (EditText) findViewById(R.id.editText_phone);
        details1 = (EditText) findViewById(R.id.editText_details);
        itemlabel1 = (Spinner) findViewById(R.id.itemlabel);
        money1 = (EditText) findViewById(R.id.editText_money);

//        pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        userid = pref.getInt("userid", 1);
        username = pref.getString("username", "");
        password = pref.getString("password", "");

        /**
         *获取日期
         */
        btn = (ImageButton) this.findViewById(R.id.imageBtn_schedule);
        tv = (TextView) this.findViewById(R.id.textView_schedule);
        l1 = (LinearLayout) this.findViewById(R.id.l1_date);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Fabu_AddItemActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        //获取系统的日期
                        //年
                        int year1 = calendar.get(Calendar.YEAR);
                        //月
                        int month1 = calendar.get(Calendar.MONTH) + 1;
                        //日
                        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
//    //小时
//    int hour = calendar.get(Calendar.HOUR_OF_DAY);
//    //分钟
//    int minute = calendar.get(Calendar.MINUTE);
//    //秒
//    int second = calendar.get(Calendar.SECOND);
//
//    String str = (year + "-" + month + day + hour + minute + second);
                        if (year > year1) {
                            tv.setText(String.format("%d/%d/%d", year, monthOfYear + 1, dayOfMonth));
                        } else if (year == year1 && (monthOfYear + 1) > month1) {
                            tv.setText(String.format("%d/%d/%d", year, monthOfYear + 1, dayOfMonth));
                        } else if (year == year1 && (monthOfYear + 1) == month1 && dayOfMonth > day1) {
                            tv.setText(String.format("%d/%d/%d", year, monthOfYear + 1, dayOfMonth));
                        } else {
                            showToast("日期输入有误");
                        }
                    }
                }, 2018, 1, 1).show();
            }
        });

        /*
         *获取时间
         */
        l2 = (LinearLayout) this.findViewById(R.id.l1_item);
        btn2 = (ImageButton) this.findViewById(R.id.imageBtn_time);
        tv2 = (TextView) this.findViewById(R.id.textView_time);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Fabu_AddItemActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv2.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                    //0,0指的是时间，true表示是否为24小时，true为24小时制
                }, 0, 0, true).show();

            }
        });

        //传入数据内容
        if (itembackupInfoBean != null) {
//            dbHelper = new DataBaseHelper(this, "QuXing.db", null, 1);
//            db = dbHelper.getReadableDatabase();
//        db.execSQL("create table aa(id_rows integer primary key)");
//        db.execSQL("insert into aa (id_rows) values(1)");
//        db.execSQL("insert into Itembackupinfo (itemid) values(1)");
//            Cursor cursor = db.rawQuery("select itemid from Itembackupinfo ", new String[]{});
            itemid = itembackupInfoBean.getItemid();
            itemname1.setText(String.valueOf(itembackupInfoBean.getItemname()));
            hostname1.setText(String.valueOf(itembackupInfoBean.getHostname()));
            address1.setText(String.valueOf(itembackupInfoBean.getAddress()));
            callnumber1.setText(String.valueOf(itembackupInfoBean.getCallnumber()));
            details1.setText(String.valueOf(itembackupInfoBean.getDetails()));

            money1.setText(String.valueOf(itembackupInfoBean.getMoney()));
        } else {
            money1.setText("0");
        }
    }

    //获取当前时间
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date curDate = new Date(System.currentTimeMillis());
    String str = formatter.format(curDate);

    //取下拉框的活动标签
    public int sprinner() {
        String itemlabel = itemlabel1.getSelectedItem().toString();
//        showToast(itemlabel + "123");
//        if("教育".equals(itemlabel))
        if (itemlabel.equals("教育"))
            return 1;
        else if (itemlabel.equals("文艺")) {
            return 2;
        } else if (itemlabel.equals("户外")) {
            return 3;
        } else if (itemlabel.equals("旅行")) {
            return 4;
        } else if (itemlabel.equals("校园")) {
            return 5;
        } else if (itemlabel.equals("交友")) {
            return 6;
        } else if (itemlabel.equals("游戏")) {
            return 7;
        } else {
            return 0;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_put:
                if (checkEdit()) {
                    //删除已选数据
                    dbHelper = new DataBaseHelper(this, "QuXing.db", null, 1);
                    db = dbHelper.getReadableDatabase();
                    db.execSQL("delete from Itembackupinfo where itemid =" + itemid, new String[]{});
                    db.close();
                    dbHelper.close();
                    post.start();

                    Intent intent = new Intent(this, FabuActivity.class);
                    this.startActivity(intent);
                    finish();
//                    new Thread() {
//                        public void run() {
//                            //定义传入字符串数据
//                            String itemname = itemname1.getText().toString().trim();
//                            String hostname = hostname1.getText().toString().trim();
//                            String address = address1.getText().toString().trim();
//                            String callnumber = callnumber1.getText().toString().trim();
//                            String details = details1.getText().toString().trim();
//                            int money = Integer.parseInt(money1.getText().toString());
//                            String itemtime = (tv.getText().toString() + tv2.getText().toString()).trim();
//                            String releasetime = str.toString().trim();
//                            JSONObject jsonObject = new JSONObject();//构建即直接实例化一个JSONObject对象
//                            try {
//                                //调用其put()方法,将数据写入
//                                jsonObject.put("itemname", itemname);
//                                jsonObject.put("hostname", hostname);
//                                jsonObject.put("address", address);
//                                jsonObject.put("callnumber", callnumber);
//                                jsonObject.put("details", details);
//                                jsonObject.put("money", money);
//                                jsonObject.put("itemtime", itemtime);
//                                jsonObject.put("releasetime", releasetime);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            String s = HttpHandler.executeHttpPost("http://192.168.43.34:8081/item", jsonObject.toString());
//
//                            if ("success".equals(s)) {
//                                showToast("项目已存储");
//                                Intent intent_set = new Intent(Fabu_AddItemActivity.this, FabuActivity.class);
//                                finish();
//                                startActivity(intent_set);
//                            } else if ("fail".equals(s)) {
//                                showToast("项目已存在");
//                            }
//
////                            post();
//                        }
//                    }.start();
                }
                break;
            case R.id.button_save:
                if (checkEdit()) {
                    save.start();
                    Intent intent = new Intent(this, FabuActivity.class);
                    this.startActivity(intent);
                    finish();
                }
                break;
            case R.id.takephoto_imgbutton:
                takePhoto();
                break;
            case R.id.add_imgbutton:
                getPhoto();
                break;
            case R.id.button_address:
                Intent intent = new Intent(this, BaiduMapActivity.class);
                startActivityForResult(intent, 4);
                break;
            default:
                break;
        }

    }

    Thread save = new Thread() {
        public void run() {
            /**
             * 将数据保存至数据库
             */
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //定义传入字符串数据
            String itemname = itemname1.getText().toString().trim();
            String hostname = hostname1.getText().toString().trim();
            String address = address1.getText().toString().trim();
            String callnumber = callnumber1.getText().toString().trim();
            String details = details1.getText().toString().trim();
            int money = Integer.parseInt(money1.getText().toString());
            String itemtime = (tv.getText().toString() + " " + tv2.getText().toString()).trim();
            String createtime = str.toString().trim();
            int itemlabel = sprinner();//取下拉框的活动标签
            ContentValues values = new ContentValues();
            values.put("itemname", itemname);
            values.put("hostname", hostname);
            values.put("address", address);
            values.put("callnumber", callnumber);
            values.put("details", details);
            values.put("money", money);
            values.put("itemtime", itemtime);
            values.put("createtime", createtime);
            values.put("itemlabel", itemlabel);
            values.put("username", username);//获取当前创建者用户名
            db.insert("Itembackupinfo", null, values);
            db.close();
            showToast("活动信息已保存");
        }
    };

    Thread post = new Thread() {
        public void run() {
            //定义传入字符串数据
            String itemname = itemname1.getText().toString().trim();
            String hostname = hostname1.getText().toString().trim();
            String address = address1.getText().toString().trim();
            String callnumber = callnumber1.getText().toString().trim();
            String details = details1.getText().toString().trim();
            int money = Integer.parseInt(money1.getText().toString());
            String itemtime = (tv.getText().toString() + " " + tv2.getText().toString()).trim();
            String releasetime = str.toString().trim();
            int itemlabel = sprinner();//取下拉框的活动标签
            JSONObject jsonObject = new JSONObject();//构建即直接实例化一个JSONObject对象
            try {
                //调用其put()方法,将数据写入
                jsonObject.put("itemname", itemname);
                jsonObject.put("hostname", hostname);
                jsonObject.put("address", address);
                jsonObject.put("callnumber", callnumber);
                jsonObject.put("details", details);
                jsonObject.put("money", money);
                jsonObject.put("itemtime", itemtime);
                jsonObject.put("releasetime", releasetime);
                jsonObject.put("itemlabel", itemlabel);
                jsonObject.put("username", username);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String s = HttpHandler.executeHttpPost("http://192.168.43.34:8082/item", jsonObject.toString());

            if ("success".equals(s)) {
                showToast("活动已发布");
                parseJOSNWithGSON1();
                parseJOSNWithGSON2();
                Intent intent_set = new Intent(Fabu_AddItemActivity.this, FabuActivity.class);
                finish();
                startActivity(intent_set);
            } else if ("fail".equals(s)) {
                showToast("活动已存在");
            }
        }
    };

    //活动存储至连接表
    private void parseJOSNWithGSON1() {
        int itemid1 = getItemid();
        String itemname = itemname1.getText().toString().trim();
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
    }

    //获取itemid
    public int getItemid() {
        String itemname = itemname1.getText().toString().trim();
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

    //信息存储至交流信息表
    private void parseJOSNWithGSON2() {
        int itemid1 = getItemid();
        String itemname = itemname1.getText().toString().trim();
        JSONObject jsonObject = new JSONObject();//构建即直接实例化一个JSONObject对象
        try {
            //调用其put()方法,将数据写入
            jsonObject.put("itemid", itemid1);
            jsonObject.put("itemname", itemname);
            jsonObject.put("username", username);
            jsonObject.put("comcontentinfo", "欢迎来到" + itemname + "活动");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String s = HttpHandler.executeHttpPost("http://192.168.43.34:8082/com", jsonObject.toString());
    }

    //判定条件
    public boolean checkEdit() {

        if (itemname1.getText().toString().equals("")) {
            showToast("活动名不能为空");
            return false;
        }
        if (address1.getText().toString().equals("")) {
            showToast("活动地址不能为空");
            return false;
        }
        if (callnumber1.getText().toString().equals("")) {
            showToast("联系方式不能为空");
            return false;
        }
        if (money1.getText().toString().equals("")) {
            showToast("活动费用不能为空");
            return false;
        }
        if (tv.getText().toString().equals("2018/01/01 00:00")) {
            showToast("活动时间未设置");
            return false;
        }

        return true;
    }

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Fabu_AddItemActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent_set = new Intent(Fabu_AddItemActivity.this, FabuActivity.class);
                finish();
                startActivity(intent_set);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void takePhoto() {   // 拍照
        //创建File对象，用于存储拍照后的图片 //将此图片存储于SD卡的根目录下
        File outputImage = new File(Environment.getExternalStorageDirectory(),
                "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将File对象转换成Uri对象 //Uri表标识着图片的地址
        headImgUri = Uri.fromFile(outputImage);
        //隐式调用照相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //拍下的照片会被输出到output_image.jpg中去
        intent.putExtra(MediaStore.EXTRA_OUTPUT, headImgUri);
        //此处是使用的startActivityForResult（）
        //因此在拍照完后会有结果返回到onActivityResult（）中去，返回值即为<span style="font-size: 13.3333px; font-family: Arial, Helvetica, sans-serif;">CUT_PICTURE</span>
        //onActivityResult（）中主要是实现图片裁剪  // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        startActivityForResult(intent, TAKE_PHOTO);
    }

    // 定向到图片库
    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GET_PHOTO);
    }

    /**
     * 裁剪
     */
    private void crop(Uri uri) {

        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边
//        // 裁剪框的比例，1：1
//        intent.putExtra("aspectX", 1);// 输出是X方向的比例
//        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小,不能太大500程序崩溃
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 250);
        // 图片格式
        /* intent.putExtra("outputFormat", "JPEG"); */
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:返回uri，false：不返回uri

        // 同一个地址下 裁剪的图片覆盖拍照的图片
        intent.putExtra(MediaStore.EXTRA_OUTPUT, headImgUri);
        startActivityForResult(intent, CROP_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GET_PHOTO:
                if (resultCode == RESULT_OK) {
                    crop(data.getData());
                }
                break;
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    crop(headImgUri);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap cropbitmap = data.getParcelableExtra("data");
                    picture.setImageBitmap(cropbitmap);
                }
                break;
            case GET_ADDRESS:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                    showToast(returnedData);
                    address1.setText(returnedData);
                }
                break;
            default:
        }
    }
}

