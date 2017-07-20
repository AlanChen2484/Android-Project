package chen.zucc.com.personalassistant.Schedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.R;
import chen.zucc.com.personalassistant.model.ScheduleModel;

/**
 * Created by chenchongkang on 2017/7/10.
 */

public class SeachscheduleActivity extends Activity {
    private TextView mTextView;
    private EditText mEditText;
    private ImageView mImageView;
    private ListView mListView;

    private ListView lv;
    private List<ScheduleModel> data;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_seach);
        initView();
    }

    private List<ScheduleModel> getData() {
        List<ScheduleModel> list = new ArrayList<>();
        dbHelper = new DataBaseHelper(SeachscheduleActivity.this, "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();
        String str = mEditText.getText().toString();
        Cursor cursor = db.rawQuery("select * from Schedule where Schedule_details like '%" + str + "%'", new String[]{});
        while (cursor.moveToNext()) {
            ScheduleModel sm = new ScheduleModel();
            sm.setId(cursor.getInt(cursor.getColumnIndex("id")));
            sm.setSchedule_details(cursor.getString(cursor.getColumnIndex("Schedule_details")));
            sm.setSchedule_beigntime(cursor.getString(cursor.getColumnIndex("Schedule_beginschedule")));
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
    public static class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
        public ImageButton img1;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private MyAdapter(Context context) {
            /**
             * 根据context上下文加载布局，这里的是Activity本身，即this
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
                convertView = mInflater.inflate(R.layout.fragment_one_list, null);
                holder.img = (ImageView) convertView.findViewById(R.id.imageView_2);
                holder.title = (TextView) convertView.findViewById(R.id.one_list_textView_1);
                holder.info = (TextView) convertView.findViewById(R.id.one_list_textView_2);
                holder.img1 = (ImageButton) convertView.findViewById(R.id.imageBtn_true);
                /**
                 * 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                 */
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//              holder.img.setBackgroundResource((Integer) data.get(position).get("img"));
            holder.title.setText(data.get(position).getSchedule_details());
            holder.info.setText(data.get(position).getSchedule_beigntime());
//              holder.img1.setBackgroundResource((Integer) data.get(position).get("img1"));
            return convertView;
        }
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textview);
        mEditText = (EditText) findViewById(R.id.edittext);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mListView = (ListView) findViewById(R.id.listview);
        //设置删除图片的点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把EditText内容设置为空
                mEditText.setText("");
                //把ListView隐藏
                mListView.setVisibility(View.GONE);
            }
        });
        //EditText添加监听
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            //文本改变之前执行
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度为0
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    mImageView.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    mImageView.setVisibility(View.VISIBLE);
                    //显示ListView
                    showListView();
                }
            }

            private void showListView() {
                /**
                 * 适配器
                 * 获取将要绑定的数据设置到data中
                 */
                lv = (ListView) findViewById(R.id.listview);
                mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data = getData();
                        MyAdapter adapter = new MyAdapter(SeachscheduleActivity.this);
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(SeachscheduleActivity.this, EditScheduleActivity.class);
                                intent.putExtra("data", data.get(i));
                                startActivity(intent);
                            }
                        });
                    }
                });
            }

            //文本改变之后执行
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
