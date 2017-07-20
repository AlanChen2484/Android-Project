package chen.zucc.com.personalassistant.Schedule;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
 * Created by chenchongkang on 2017/7/5.
 */

public class OneFragment extends Fragment {
    private ListView lv;
    private List<ScheduleModel> data;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private TextView tv;
    private String name;
    public OneFragment(String fName) {
        this.name = fName;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one, container, false);
        /**
         * 适配器
         */
        lv = (ListView) view.findViewById(R.id.list);
        /**
         * 获取将要绑定的数据设置到data中
         */
        data = getData();
        MyAdapter adapter = new MyAdapter(getActivity());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), EditScheduleActivity.class);
                intent.putExtra("data",data.get(i));
                startActivity(intent);
            }
        });
        /**
         * fragment 界面跳转
         */
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.imageButton_3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_2 = new Intent(getActivity(), ScheduleAddActivity.class);
                startActivity(intent_2);
            }
        });
        tv = (TextView) view.findViewById(R.id.fragment_tv);
        tv.setText(name);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("我变了-" + name);
            }
        });
        return view;
    }
    private List<ScheduleModel> getData() {
        List<ScheduleModel> list = new ArrayList<>();
        dbHelper = new DataBaseHelper(getActivity(), "PersonalAssistant.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id,Schedule_details,Schedule_beginschedule from Schedule order by Schedule_beginschedule", new String[]{});
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
    public static class ViewHolder extends SeachscheduleActivity.ViewHolder {
        public ImageView img;
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
          //  holder.img.setBackgroundResource((Integer) data.get(position).get("img"));
            holder.title.setText(data.get(position).getSchedule_details());
            holder.info.setText(data.get(position).getSchedule_beigntime());
//            holder.img1.setBackgroundResource((Integer) data.get(position).get("img1"));
            return convertView;
        }
    }
}
