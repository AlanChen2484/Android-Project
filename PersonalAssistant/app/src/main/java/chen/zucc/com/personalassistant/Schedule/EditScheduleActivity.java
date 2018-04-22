package chen.zucc.com.personalassistant.Schedule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.model.ScheduleModel;

import static chen.zucc.com.personalassistant.R.id.view;

/**
 * Created by chenchongkang on 2017/7/6.
 */

public class EditScheduleActivity extends Activity implements View.OnClickListener {

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


    private ImageButton mMenuTv;
    private PopupWindow mPopWindow;
    private ScheduleModel scheduleModel;
    private TextView scheduleDescription;
    private TextView scheduleTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editschedule);
        takePhoto = (Button) findViewById(R.id.take_photo);
        getPhoto = (Button) findViewById(R.id.choose_from_album);
        picture = (ImageView) findViewById(R.id.picture);

        takePhoto.setOnClickListener(this);
        getPhoto.setOnClickListener(this);

//        takePhoto = (Button) findViewById(R.id.take_photo1);
//        picture = (ImageView) findViewById(R.id.picture);
//        takePhoto.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
//
//                try {
//                    if (outputImage.exists())
//                    {
//                        outputImage.delete();
//
//                    }outputImage.createNewFile();
//
//                } catch (IOException e) {
//
//                    e.printStackTrace();
//                }
//                imageUri = Uri.fromFile(outputImage);
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//                // android 7.0系统解决拍照的问题
//                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//                StrictMode.setVmPolicy(builder.build());
//                builder.detectFileUriExposure();
//                startActivityForResult(intent,TAKE_PHOTO);
//            }
//        });

        scheduleModel = (ScheduleModel) getIntent().getSerializableExtra("data");
        scheduleDescription= (TextView) findViewById(R.id.schedule_description);
        scheduleTime= (TextView) findViewById(R.id.schedule_begintime);
        scheduleDescription.setText(scheduleModel.getSchedule_details());
        scheduleTime.setText(scheduleModel.getSchedule_beigntime());

        mMenuTv = (ImageButton) findViewById(R.id.imageBtn_2);
        mMenuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageBtn_1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                takePhoto();
                break;
            case R.id.choose_from_album:
                getPhoto();
                break;
            default:
                break;
        }

    }

    // 拍照
    private void takePhoto() {
        //创建File对象，用于存储拍照后的图片
        //将此图片存储于SD卡的根目录下
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
        //将File对象转换成Uri对象
        //Uri表标识着图片的地址
        headImgUri = Uri.fromFile(outputImage);
        //隐式调用照相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //拍下的照片会被输出到output_image.jpg中去
        intent.putExtra(MediaStore.EXTRA_OUTPUT, headImgUri);
        //此处是使用的startActivityForResult（）
        //因此在拍照完后悔有结果返回到onActivityResult（）中去，返回值即为<span style="font-size: 13.3333px; font-family: Arial, Helvetica, sans-serif;">CUT_PICTURE</span>
        //onActivityResult（）中主要是实现图片裁剪
        // android 7.0系统解决拍照的问题
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
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);// 输出是X方向的比例
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小,不能太大500程序崩溃
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);

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


//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//        switch (requestCode){
//            case TAKE_PHOTO:
//                if ((resultCode==RESULT_OK)){
//                    Intent intent =new Intent("com.android.camera.action.CROP");
//                    intent.setDataAndType(imageUri,"image/*");
//                    intent.putExtra("scale",true);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//                    startActivityForResult(intent,CROP_PHOTO);
//                }
//                break;
//            case CROP_PHOTO:
//                if (requestCode==RESULT_OK){
//                    try {
//                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        picture.setImageBitmap(bitmap);
//                    }catch (FileNotFoundException e){
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            default:
//                break;
//
//        }
//    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(EditScheduleActivity.this).inflate(R.layout.activity_schedule_popuplayout, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView) contentView.findViewById(R.id.pop_manage);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        View rootView = LayoutInflater.from(EditScheduleActivity.this).inflate(R.layout.activity_editschedule, null);
        mPopWindow.showAtLocation(rootView, Gravity.BOTTOM, 300, 1150);
    }

//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.pop_computer: {
//                Toast.makeText(this, "clicked computer", Toast.LENGTH_SHORT).show();
//                mPopWindow.dismiss();
//            }
//            break;
//            case R.id.pop_financial: {
//                Intent intent=new Intent();
//                intent.setClass(v.getContext(), MainActivity.class);
//                v.getContext().startActivity(intent);
//
//                Toast.makeText(this, "clicked financial", Toast.LENGTH_SHORT).show();
//                mPopWindow.dismiss();
//            }
//            break;
//            case R.id.pop_manage: {
//                Toast.makeText(this, "clicked manage", Toast.LENGTH_SHORT).show();
//                mPopWindow.dismiss();
//            }
//            break;
//        }
//    }

}
