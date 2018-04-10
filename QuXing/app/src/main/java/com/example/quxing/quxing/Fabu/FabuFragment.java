package com.example.quxing.quxing.Fabu;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quxing.quxing.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by 陈若韬 on 2018/3/31.
 */

public class FabuFragment extends Fragment {
    private static final String ARG_POSITION = "pos";
    private ListView listView;
    private ListView lv;
    //    private List<MoneyModel> data;
//    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private RecyclerView mRvTextList;

//    private SimpleCursorAdapter adapter = null;
//    private DataBaseHelper dbHelper;
//    private SQLiteDatabase dbRead;

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_EDIT_NOTE = 2;

    public static FabuFragment newInstance(int pos) {
        FabuFragment fragment = new FabuFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int pos = getArguments().getInt(ARG_POSITION);

        if (pos == 1) {
            /**
             * 适配器
             */
            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fabu, container, false);
            lv = (ListView) view.findViewById(R.id.list);
            /**
             * 获取将要绑定的数据设置到data中
             */
//            data = getData1();
//            MyAdapter adapter = new MyAdapter(getActivity());
//            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(),i+"",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), InvestmentActivity.class);
//                    intent.putExtra("data", data.get(i));
//                    startActivity(intent);
                }
            });
            return view;

        } else {
            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fabu, container, false);
            lv = (ListView) view.findViewById(R.id.list);
            /**
             * 获取将要绑定的数据设置到data中
             */
//            data = getData2();
//            MyAdapter adapter = new MyAdapter(getActivity());
//            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(),i+"",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), InvestmentActivity.class);
//                    intent.putExtra("data", data.get(i));
//                    startActivity(intent);
                }
            });
            return view;
        }
    }

////    private List<MoneyModel> getData1() {
////        List<MoneyModel> list = new ArrayList<>();
////        dbHelper = new DataBaseHelper(getActivity(), "PersonalAssistant.db", null, 1);
////        db = dbHelper.getReadableDatabase();
////        Cursor cursor = db.rawQuery("select * from Money where Money_state = 1 " +
////                "order by Money_endDate", new String[]{});
////        while (cursor.moveToNext()) {
//////            String a1 = cursor.getString(cursor.getColumnIndex("Schedule_details"));
//////            String a2 = cursor.getString(cursor.getColumnIndex("Schedule_beginschedule"));
////            MoneyModel moneyModel = new MoneyModel();
////            moneyModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
////            moneyModel.setMoney_assetName(cursor.getString(cursor.getColumnIndex("Money_assetName")));
////            moneyModel.setMoney_asset(cursor.getDouble(cursor.getColumnIndex("Money_asset")));
////            moneyModel.setMoney_beginDate(cursor.getString(cursor.getColumnIndex("Money_beginDate")));
////            moneyModel.setMoney_endDate(cursor.getString(cursor.getColumnIndex("Money_endDate")));
////            moneyModel.setMoney_yield(cursor.getDouble(cursor.getColumnIndex("Money_yield")));
////            moneyModel.setMoney_remark(cursor.getString(cursor.getColumnIndex("Money_remark")));
////            moneyModel.setMoney_anticipated_income(cursor.getDouble(cursor.getColumnIndex("Money_anticipated_income")));
////            list.add(moneyModel);
//////            Log.e("test",moneyModel.getMoney_beginDate() +";" + moneyModel.get);
////        }
////        db.close();
////        return list;
////    }
//
////    private List<MoneyModel> getData2() {
////        List<MoneyModel> list = new ArrayList<>();
////        dbHelper = new DataBaseHelper(getActivity(), "PersonalAssistant.db", null, 1);
////        db = dbHelper.getReadableDatabase();
////
////        Cursor cursor = db.rawQuery("select * from Money where Money_state = 0 " +
////                "order by Money_endDate", new String[]{});
////        while (cursor.moveToNext()) {
//////            String a1 = cursor.getString(cursor.getColumnIndex("Schedule_details"));
//////            String a2 = cursor.getString(cursor.getColumnIndex("Schedule_beginschedule"));
////            MoneyModel moneyModel = new MoneyModel();
////            moneyModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
////            moneyModel.setMoney_assetName(cursor.getString(cursor.getColumnIndex("Money_assetName")));
////            moneyModel.setMoney_asset(cursor.getDouble(cursor.getColumnIndex("Money_asset")));
////            moneyModel.setMoney_beginDate(cursor.getString(cursor.getColumnIndex("Money_beginDate")));
////            moneyModel.setMoney_endDate(cursor.getString(cursor.getColumnIndex("Money_endDate")));
////            moneyModel.setMoney_yield(cursor.getDouble(cursor.getColumnIndex("Money_yield")));
////            moneyModel.setMoney_remark(cursor.getString(cursor.getColumnIndex("Money_remark")));
////            moneyModel.setMoney_anticipated_income(cursor.getDouble(cursor.getColumnIndex("Money_anticipated_income")));
////            list.add(moneyModel);
//////            Log.e("test",moneyModel.getMoney_beginDate() +";" + moneyModel.get);
////        }
////        db.close();
////        return list;
////    }
//    /**
//     * 条目点击时被调用，打开编辑界面，同时传入当前信息
//     */
//    /**
//     * ViewHolder静态类
//     */
//    static class ViewHolder {
//        public TextView asset;
//        public TextView income;
//        public TextView endDate;
//    }
//
//    public class MyAdapter extends BaseAdapter {
//        private LayoutInflater mInflater = null;
//
//        private MyAdapter(Context context) {
//            /**
//             * 根据context上下文加载布局，这里的是Demo17Activity本身，即this
//             */
//            this.mInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            /**
//             * 在此适配器中所代表的数据集中的条目数
//             */
////          return data.size();
//            return 1;//测试数据
//        }
//
//        @Override
//        public Object getItem(int position) {
//            /**
//             * 获取数据集中与指定索引对应的数据项
//             */
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            /**
//             * 获取在列表中与指定索引对应的行id
//             */
//            return position;
//        }
//
//        /**
//         * 获取一个在数据集中指定索引的视图来显示数据
//         */
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder = null;
//            /**
//             * 如果缓存convertView为空，则需要创建View
//             */
//            if (convertView == null) {
//                holder = new ViewHolder();
//                /**
//                 * 根据自定义的Item布局加载布局
//                 */
////                convertView = mInflater.inflate(R.layout.fragment_money_finish, null);
////
////                holder.asset = (TextView) convertView.findViewById(R.id.textView1);
////                holder.income = (TextView) convertView.findViewById(R.id.textView3);
////                holder.endDate = (TextView) convertView.findViewById(R.id.textView5);
//
//                /**
//                 * 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
//                 */
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
////            holder.asset.setText(String.valueOf(data.get(position).getMoney_asset()));
////            holder.income.setText(String.valueOf(data.get(position).getMoney_anticipated_income()));
////            holder.endDate.setText(data.get(position).getMoney_endDate());
//            return convertView;
//        }
//
//    }
//
//
}






