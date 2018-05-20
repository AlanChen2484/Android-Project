package com.example.quxing.quxing.Wode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.quxing.quxing.DataBaseHelper.DBOperate;
import com.example.quxing.quxing.DataBaseHelper.DataBaseHelper;
import com.example.quxing.quxing.Fabu.FabuActivity;
import com.example.quxing.quxing.Fabu.Fabu_AddItemActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.makeramen.roundedimageview.RoundedImageView;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.OnClick;

import static com.example.quxing.quxing.Auxiliary.ToastUtil.showToast;

public class Wode_PersonalSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int GET_PHOTO = 3;
    private Uri headImgUri;

    private RoundedImageView picture;
    private Button personalimage;
    private DataBaseHelper dbHelper;
    private String url;
    private DBOperate dbOperate;
    private Spinner userlabel, sex1;
    private String username;
    private int userid;
    private JSONObject jsonObject;

    MaterialEditText name, city, personhomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode__personal_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        picture = (RoundedImageView) findViewById(R.id.roundedImageView);
        picture.setOnClickListener(this);

        name = (MaterialEditText) findViewById(R.id.name);
        city = (MaterialEditText) findViewById(R.id.city);
        personhomepage = (MaterialEditText) findViewById(R.id.personHomepage);
        userlabel = (Spinner) findViewById(R.id.label);
        sex1 = (Spinner) findViewById(R.id.sex);
//        if (getDrawable().size() != 0) {
//            picture.setImageDrawable(getDrawable().get(0));
//        }
//        setContentView(picture);
//        init();
//        readImage();
//        personalimage = (Button) findViewById(R.id.personalimage_button);
//        personalimage.setOnClickListener(this);
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        username = pref.getString("username", "");
        userid = pref.getInt("userid", 1);

        new Thread() {
            public void run() {
                parseJOSNWithGSON1();
            }
        }.start();

    }
    //    private ArrayList<Drawable> getDrawable() {
//        DataBaseHelper pd = new DataBaseHelper(this);
//        SQLiteDatabase sd = pd.getWritableDatabase();
//
//        ArrayList<Drawable> drawables = new ArrayList<Drawable>();
//
//        //查询数据库
//        Cursor c = sd.query("picture", null, null, null, null, null, null);
//
//        //遍历数据
//        if (c != null && c.getCount() != 0) {
//            while (c.moveToNext()) {
//                //获取数据
//                byte[] b = c.getBlob(c.getColumnIndexOrThrow(DataBaseHelper.PictureColumns.PICTURE));
//                //将获取的数据转换成drawable
//                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
//                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//                Drawable drawable = bitmapDrawable;
//                drawables.add(drawable);
//            }
//        }
//        return drawables;
//    }

    Thread post = new Thread() {
        public void run() {
            //定义传入字符串数据
            String name1 = name.getText().toString().trim();
            String city1 = city.getText().toString().trim();
            String personhomepage1 = personhomepage.getText().toString().trim();
            int userlabel = sprinner();//取下拉框的活动标签
            int sex = sprinner1();//取下拉框的性别
            jsonObject = new JSONObject();//构建即直接实例化一个JSONObject对象
            try {
                //调用其put()方法,将数据写入
                jsonObject.put("name", name1);
                jsonObject.put("city", city1);
                jsonObject.put("personhomepage", personhomepage1);
                jsonObject.put("userlabel", userlabel);
                jsonObject.put("sex", sex);
                jsonObject.put("username", username);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String s = HttpHandler.executeHttpPost("http://192.168.43.34:8082/userinfo", jsonObject.toString());

//        if ("success".equals(s)) {
//            showToast("活动已发布");
//            Intent intent_set = new Intent(Wode_PersonalSettingActivity.this, FabuActivity.class);
//            finish();
//            startActivity(intent_set);
//        } else if ("fail".equals(s)) {
//            showToast("活动已存在");
//        }
        }
    };

    private void parseJOSNWithGSON1() {
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/userget/getusername/" + username);
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String userlabel1 = object.getString("userlabel");
                String city1 = object.getString("city");
                String name1 = object.getString("name");
                String personhomepage1 = object.getString("personhomepage");

                showUI(name1);
                showUI2(city1);
                showUI3(personhomepage1);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showUI(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                name.setText(text);
            }
        });
    }

    private void showUI2(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                city.setText(text);
            }
        });
    }

    private void showUI3(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                personhomepage.setText(text);
            }
        });
    }


    //取下拉框的性别
    public int sprinner1() {
        String sex = sex1.getSelectedItem().toString();
        if ("男".equals(sex))
            return 1;
        if ("女".equals(sex))
            return 2;
        else {
            return 0;
        }
    }

    //取下拉框的个人兴趣标签
    public int sprinner() {
        String itemlabel = userlabel.getSelectedItem().toString();
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

    //取下拉框的活动标签
    public String sprinner3(final String text) {
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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roundedImageView:
                getPhoto();
                break;
            default:
                break;
        }
    }

//    private void init(){
//        dbOperate=new DBOperate(this);
//    }

//    @OnClick({R.id.save_btn,R.id.read_btn})
//    void onClick(View view){
//        switch (view.getId()){
//            case R.id.read_btn:
//                readImage();
//                break;
//            case R.id.save_btn:
//                saveImage(url);
//                break;
//            default:
//                break;
//
//        }
//    }

//    private void readImage(){
//        byte[] imgData=dbOperate.readImage();
//        if (imgData!=null) {
//            //将字节数组转化为位图
//            Bitmap imagebitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
//            //将位图显示为图片
//            picture.setImageBitmap(imagebitmap);
//        }else {
//            picture.setBackgroundResource(android.R.mipmap.sym_def_app_icon);
//        }
//    }

//    private void saveImage(String url){
//        dbOperate.saveImage();
//    }

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
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);// 输出是X方向的比例
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小,不能太大500程序崩溃
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                saveImage(url);

                post.start();

                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GET_PHOTO:
                if (resultCode == RESULT_OK) {
                    crop(data.getData());
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap cropbitmap = data.getParcelableExtra("data");
                    picture.setImageBitmap(cropbitmap);
                }
                break;
            default:
        }
    }

}