
package chen.zucc.com.personalassistant.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_NAME_NOTE_DATE = "date";
    public static final String TABLE_NAME_NOTES = "notes";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_NOTE_NAME = "name";
    public static final String COLUMN_NAME_NOTE_CONTENT = "content";

    public DataBaseHelper(Context context) {
        super(context, "PersonalAssistant", null, 1);
    }


    public static final String CREATE_Login = "create table Login("
            + "id integer primary key autoincrement,"
            + "Login_UserName text UNIQUE,"
            + "Login_Password text )";

    public static final String CREATE_Schedule = "create table Schedule("
            + "id integer primary key autoincrement,"
            + "Schedule_details text ,"         //日程详情
            + "Schedule_begintime text ,"
            + "Schedule_endtime text,"
            + "Schedule_priority integer , "    //优先级
            + "Schedule_classes integer ,"      //类别
            + "Schedule_remark text,"           //备注
            + "Schedule_state integer"          //完成状态
            + "Schedule_picture text)";

    public static final String CREATE_Income = "create table Income("
            + "id integer primary key autoincrement,"
            + "Income_income real ,"
            + "Income_expense real ,"
            + "Income_classes integer ,"
            + "Income_time text )";

    public static final String CREATE_SortIncome = "create table SortIncome("
            + "id integer primary key autoincrement,"
            + "SortIncome_money real,"
            + "SortIncome_expense real,"
            + "SortIncome_income real)";

    public static final String CREATE_Money = "create table Money("
            + "id integer primary key autoincrement,"
            + "Money_asset real ,"
            + "Money_assetName text,"
            + "Money_yield real ,"           //收益率
            + "Money_beginDate text ,"
            + "Money_endDate text ,"
            + "Money_remark text,"
            + "Money_state integer,"               //定期状态
            + "Money_anticipated_income real,"     //预期收益
            + "Money_accumulated_income real)";    //累计收益

    public static final String CREATE_SortMoney = "create table SortMoney("
            + "id integer primary key autoincrement,"
            + "SortMoney_money real,"                  //总资产
            + "SortMoney_income real,"
            + "SortMoney_capital real)";               //本金


    private Context mContext;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_Login);
        db.execSQL(CREATE_Schedule);
        db.execSQL(CREATE_Income);
        db.execSQL(CREATE_SortIncome);
        db.execSQL(CREATE_Money);
        db.execSQL(CREATE_SortMoney);

        Toast.makeText(mContext, "Create DataBase succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Login");
        db.execSQL("drop table if exists Schedule");
        db.execSQL("drop table if exists Income");
        db.execSQL("drop table if exists SortIncome");
        db.execSQL("drop table if exists Money");
        db.execSQL("drop table if exists SortMoney");
        onCreate(db);

    }

}