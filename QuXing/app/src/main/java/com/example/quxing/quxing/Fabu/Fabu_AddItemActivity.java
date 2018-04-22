package com.example.quxing.quxing.Fabu;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quxing.quxing.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.Manifest;

public class Fabu_AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int GET_PHOTO = 3;
    private Button takePhoto;
    private Button getPhoto;
    private ImageView picture;
    private Uri headImgUri;
    Intent i;
    File f;
    private String currentPath = null;
    public static final int REQUEST_CODE_GET_PHOTO = 1;

    private ImageButton btn;
    private ImageButton btn2;
    private TextView tv;
    private TextView tv2;
    private LinearLayout l1;
    private LinearLayout l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu__additem);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_fabu_item);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        takePhoto = (Button) findViewById(R.id.takephoto_imgbutton);
        getPhoto = (Button) findViewById(R.id.add_imgbutton);
        picture = (ImageView) findViewById(R.id.item_image);

        takePhoto.setOnClickListener(this);
        getPhoto.setOnClickListener(this);


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
                        tv.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2017, 6, 9).show();
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
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takephoto_imgbutton:
                takePhoto();
                break;
            case R.id.add_imgbutton:
                getPhoto();
                break;
            default:
                break;
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
            default:
                break;
        }
    }
}

//        picture = (ImageView) findViewById(R.id.item_image);
//        Button takePhoto = (Button) findViewById(R.id.takephoto_imgbutton);
//        Button chooseFromAlbum = (Button) findViewById(R.id.add_imgbutton);
//
//        takePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
//                try {
//                    if (outputImage.exists()) {
//                        outputImage.delete();
//                    }
//                    outputImage.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (Build.VERSION.SDK_INT >= 24) {
//                    imageUri = FileProvider.getUriForFile(Fabu_AddItemActivity.this,
//                            "com.example.quxing.quxing", outputImage);
//                } else {
//                    imageUri = Uri.fromFile(outputImage);
//                }
//                //启动相机程序
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent, TAKE_PHOTO);
//            }
//        });
//
//        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(Fabu_AddItemActivity.this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Fabu_AddItemActivity.this, new
//                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                } else {
//                    openAlbum();
//                }
//            }
//        });
//    }
//
//
//    private void openAlbum() {
//        Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                           int[] grantResults) {
//        switch (requestCode) {
//            case TAKE_PHOTO:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    openAlbum();
//                } else {
//                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case TAKE_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    try {
//                        //将拍摄的照片显示出来
//                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().
//                                openInputStream(imageUri));
//                        picture.setImageResource(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            case CHOOSE_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    //判断手机系统版本号
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        //4.4及以上系统使用此方法处理图片
//                        handleImageOnKitKat(data);
//                    } else {
//                        //4.4及以下系统使用此方法处理图片
//                        handleImageBeforeKitKat(data);
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    @TargetApi(19)
//    private void handleImageOnKitKat(Intent data) {
//        String imagePath = null;
//        Uri uri = data.getData();
//        if (DocumentsContract.isDocumentUri(this, uri)) {
//            //如果是document类型的Uri，则通过document id 处理
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                String id = docId.split(":")[1];//解析出数字格式id
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
//                        Long.valueOf(docId));
//                imagePath = getImagePath(contentUri, null);
//            }
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            //如果是content类型的Uri，则使用普遍方式处理
//            imagePath = getImagePath(uri, null);
//        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            //如果是file类型的Uri，则直接获取图片路径
//            imagePath = uri.getPath();
//        }
//        displayImage(imagePath);//根据图片路径显示图片
//    }
//
//    private void handleImageBeforeKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = getImagePath(uri, null);
//        displayImage(imagePath);
//    }
//
//    private String getImagePath(Uri uri, String selection) {
//        String path = null;
//        //通过Uri和Selection来获取真实图片路径
//        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }
//
//    private void displayImage(String imagePath) {
//        if (imagePath != null) {
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            picture.setImageBitmap(bitmap);
//        } else {
//            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
