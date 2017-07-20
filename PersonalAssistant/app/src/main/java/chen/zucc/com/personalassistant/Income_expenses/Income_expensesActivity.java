package chen.zucc.com.personalassistant.Income_expenses;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.Manage_money_matters.Manager_money_mattersActivity;
import chen.zucc.com.personalassistant.Personal_homepage.homepage;
import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.Schedule.ScheduleActivity;
import chen.zucc.com.personalassistant.model.IncomeModel;


public class Income_expensesActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private ListView lv;
    private List<IncomeModel> data;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    int lastSelectedPosition = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expenses);
        dbHelper = new DataBaseHelper(this, "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();

        TextView re=(TextView)findViewById(R.id.remaining);
        Float sum=getsum2()-getsum1();
        re.setText(sum.toString());

        TextView totalExpenses=(TextView)findViewById(R.id.totalExpenses);
        Cursor cousor2 = db.rawQuery("select SUM(Income_expense) from Income ", new String[]{});
        while (cousor2.moveToNext()) {
            Float sm =(cousor2.getFloat(cousor2.getColumnIndex("SUM(Income_expense)")));
            totalExpenses.setText(sm.toString());
    }
        TextView totalIncome=(TextView)findViewById(R.id.totalIncome);
        Cursor cousor1 = db.rawQuery("select SUM(Income_income) from Income ", new String[]{});
        while (cousor1.moveToNext()) {
            Float sm =(cousor1.getFloat(cousor1.getColumnIndex("SUM(Income_income)")));
            totalIncome.setText(sm.toString());
        }
//        db.close();
        /**
         * 适配器
         */
        lv = (ListView)findViewById(R.id.income_list);
        /**
         * 获取将要绑定的数据设置到data中
         */
        data = getData();
        MyAdapter adapter = new MyAdapter(this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Income_expensesActivity.this,Income_edit.class);
                intent.putExtra("data",data.get(i));
                startActivity(intent);
            }
        });
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        RoundedImageView viewbtn=(RoundedImageView)findViewById(R.id.roundedImageView);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(Income_expensesActivity.this, Income_edit.class);
                startActivity(intent_1);
            }
        });

        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_action_schedule, "日程安排").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_bill, "账本记录").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_managemoney, "财富管理").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_person, "个人主页").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    public Float getsum1() {
        db = dbHelper.getReadableDatabase();
        Cursor sum0 = db.rawQuery("select SUM(Income_expense) from Income ", new String[]{});
        sum0.moveToNext();
        Float income =(sum0.getFloat(sum0.getColumnIndex("SUM(Income_expense)")));
//        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
        return income;
    }

    public Float getsum2() {
        db = dbHelper.getReadableDatabase();
        Cursor sum0 = db.rawQuery("select SUM(Income_income) from Income ", new String[]{});
        sum0.moveToNext();
        Float income =(sum0.getFloat(sum0.getColumnIndex("SUM(Income_income)")));
//        Log.d( "SUMDAY ", "SUMDAY "+ sum.getDouble(sum.getColumnIndex("sum(SortMoney_money)")));
        return income;
    }

    private List<IncomeModel> getData() {
        List<IncomeModel> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select id,Income_income,Income_expense,Income_classes from Income order by id", new String[]{});
        while (cursor.moveToNext()) {
            IncomeModel sm = new IncomeModel();
            sm.setId(cursor.getInt(cursor.getColumnIndex("id")));
            sm.setIncome(cursor.getFloat(cursor.getColumnIndex("Income_income")));
            sm.setClassess(cursor.getString(cursor.getColumnIndex("Income_classes")));
            sm.setExpense(cursor.getFloat(cursor.getColumnIndex("Income_expense")));
            list.add(sm);
        }
        db.close();
        return list;
    }

    /**
     * 日程条目点击时被调用，打开编辑日程界面，同时传入当前日程信息
     */

    /**
     * ViewHolder静态类
     */
    static class ViewHolder {
        public RoundedImageView img;
        public TextView title;
        public TextView info;
        public ImageButton img1;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private MyAdapter(Context context) {
            /**
             * 根据context上下文加载布局，这里的是Demo17Activity本身，即this
             */
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            /**
             * 在此适配器中所代表的数据集中的条目数
             */
            return data.size();
        }
        @Override
        public Object getItem(int position) {
            /**
             * 获取数据集中与指定索引对应的数据项
             */
            return position;
        }
        @Override
        public long getItemId(int position) {
            /**
             * 获取在列表中与指定索引对应的行id
             */
            return position;
        }
        /**
         * 获取一个在数据集中指定索引的视图来显示数据
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            /**
             * 如果缓存convertView为空，则需要创建View
             */
            if (convertView == null) {
                holder = new ViewHolder();
                /**
                 * 根据自定义的Item布局加载布局
                 */
                convertView = mInflater.inflate(R.layout.activity_income_expenser_list, null);
                holder.img = (RoundedImageView) convertView.findViewById(R.id.type);
                holder.title = (TextView) convertView.findViewById(R.id.income_type);
                holder.info = (TextView) convertView.findViewById(R.id.income_money);
//                holder.img1 = (ImageButton) convertView.findViewById(R.id.imageBtn_true);
                /**
                 * 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                 */
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //  holder.img.setBackgroundResource((Integer) data.get(position).get("img"));
            holder.title.setText(data.get(position).getClassess() + "");

            if ((int)data.get(position).getIncome() != 0) {
                holder.info.setText(data.get(position).getIncome() + "");
            } else {
                holder.info.setText(data.get(position).getExpense() + "");
            }
//




            //  holder.img1.setBackgroundResource((Integer) data.get(position).get("img1"));
            return convertView;
        }
    }
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        switch (position) {
            case 0:
                Intent intent0 = new Intent(this, ScheduleActivity.class);
                this.startActivity(intent0);
                break;

            case 1:
                Intent intent1 = new Intent(this, Income_expensesActivity.class);
                this.startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(this, Manager_money_mattersActivity.class);
                this.startActivity(intent2);
                break;

            case 3:
                Intent intent3 = new Intent(this, homepage.class);
                this.startActivity(intent3);
                break;
        }

    }
    public void onTabUnselected(int position) {

    }
    public void onTabReselected(int position) {

    }
}

