
package com.example.quxing.quxing.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;
import android.widget.Toast;

import com.example.quxing.quxing.R;

import java.io.ByteArrayOutputStream;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_NAME_NOTE_DATE = "date";
    public static final String TABLE_NAME_NOTES = "notes";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_NOTE_NAME = "name";
    public static final String COLUMN_NAME_NOTE_CONTENT = "content";
    private static DataBaseHelper dbHelper;
    //表名
    private static final String TABLE_NAME = "picture";

    //数据库的字段
    public static class PictureColumns implements BaseColumns {
        public static final String PICTURE = "picture";
    }

    public DataBaseHelper(Context context) {
        super(context, "QuXing", null, 1);
    }

    public static final String CREATE_Itembackupinfo = "create table Itembackupinfo("
            + "itemid integer primary key autoincrement,"
            + "itemname text ,"         //活动名称
            + "itemtime text ,"       //活动时间
            + "enrolment integer ,"     //参与数
            + "follownumber integer ,"   //关注数
            + "createtime text , "    //创建时间
            + "hostname text ,"      //举办者名称
            + "itemlabel integer ,"
            + "callnumber text ,"
            + "money integer ,"
            + "address text ,"
            + "details text)";
    //    private final String createTb = "CREATE TABLE User (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//            "name VARCHAR2,avatar BLOB)";

    public static final String CREATE_Communcation = "create table Communcation("
            + "communcationid integer primary key autoincrement,"
            + "communcationtexttime text ,"
            + "communcationtext text )";         //活动名称

    private Context mContext;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_Itembackupinfo);
        db.execSQL(CREATE_Communcation);
//        String sql = "Create table " + TABLE_NAME + "(" + BaseColumns._ID
//                + " integer primary key autoincrement," + PictureColumns.PICTURE
//                + " blob not null);";

        //初始化
        initDataBase(db, mContext);
//        db.execSQL(createTb);
        Toast.makeText(mContext, "Create DataBase succeeded", Toast.LENGTH_SHORT).show();
    }

    //将转换后的图片存入到数据库中
    private void initDataBase(SQLiteDatabase db, Context context) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_main_dog);
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(drawable));
        db.insert(TABLE_NAME, null, cv);
    }

    //将drawable转换成可以用来存储的byte[]类型
    private byte[] getPicture(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        return os.toByteArray();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Itembackupinfo");
        db.execSQL("drop table if exists Communcation");
        String sql = " DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

//    public static DataBaseHelper getInstance(Context context) {
//        if (dbHelper == null) { //单例模式
//            dbHelper = new DataBaseHelper(context);
//        }
//        return dbHelper;
//    }
}