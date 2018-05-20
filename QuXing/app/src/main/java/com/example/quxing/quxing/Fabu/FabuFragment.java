package com.example.quxing.quxing.Fabu;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.quxing.quxing.Auxiliary.Item_DetailsActivity;
import com.example.quxing.quxing.DataBaseHelper.DataBaseHelper;
import com.example.quxing.quxing.Main.Item;
import com.example.quxing.quxing.Main.ItemAdapter;
import com.example.quxing.quxing.Main.MainActivity;
import com.example.quxing.quxing.R;
import com.example.quxing.quxing.Tools.HttpHandler;
import com.example.quxing.quxing.model.ItemInfoBean;
import com.example.quxing.quxing.model.ItembackupInfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by 陈若韬 on 2018/3/31.
 */

public class FabuFragment extends Fragment {
    private static final String ARG_POSITION = "pos";
    private ListView listView;
    private ListView lv;
    private List<ItembackupInfoBean> data;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private RecyclerView mRvTextList;

    private List<ItemInfoBean> itemsList = new ArrayList<ItemInfoBean>();
    private TextView tv;
    private ItemAdapter itemadapter;
    private String username;
    private String password;

//    private SimpleCursorAdapter adapter = null;
//    private SQLiteDatabase dbRead;

//    public static final int REQUEST_CODE_ADD_NOTE = 1;
//    public static final int REQUEST_CODE_EDIT_NOTE = 2;

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
        if (pos == 0) {
//            /**
//             * 适配器
//             */
//            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fabu, container, false);
//            lv = (ListView) view.findViewById(R.id.list);
//            /**
//             * 获取将要绑定的数据设置到data中
//             */
//
////            data = getData1();
////            MyAdapter adapter = new MyAdapter(getActivity());
////            lv.setAdapter(adapter);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Toast.makeText(getActivity(),i+"",Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(getActivity(), InvestmentActivity.class);
////                    intent.putExtra("data", data.get(i));
////                    startActivity(intent);
//                }
//            });
//            return view;
            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fabu, container, false);
            itemadapter = new ItemAdapter(getContext(), R.layout.main_item, itemsList);
            ListView listView = (ListView) view.findViewById(R.id.list);

            listView.setAdapter(itemadapter);
//            tv = (TextView) view.findViewById(R.id.tv);

            SharedPreferences pref = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            username = pref.getString("username", "");
            password = pref.getString("password", "");

            new Thread() {
                public void run() {
                    parseJOSNWithGSON();
                }
            }.start();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ItemInfoBean item = itemsList.get(i);
                    Context context = getActivity();
                    Intent intent = new Intent(context, Item_DetailsActivity.class);
                    intent.putExtra("itemname", item.getItemname());
                    startActivity(intent);
                }
            });

            return view;


        } else {
            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fabu, container, false);
            lv = (ListView) view.findViewById(R.id.list);
            /**
             * 获取将要绑定的数据设置到data中
             */
            data = getData2();
            MyAdapter adapter = new MyAdapter(getActivity());
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(),i+"",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), Fabu_AddItemActivity.class);
                    intent.putExtra("data", data.get(i));
                    startActivity(intent);
                }
            });
            return view;
        }
    }


    private void parseJOSNWithGSON() {
        String jsondata = HttpHandler.executeHttpGet("http://192.168.43.34:8082/itemget/getusername/" + username);
        Gson gson = new Gson();
        itemsList.clear();
        //更新适配器数据。
        itemsList.addAll((Collection<? extends ItemInfoBean>) gson.fromJson(jsondata, new TypeToken<List<ItemInfoBean>>() {
        }.getType()));
        showToast();
    }

    private void showToast() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                itemadapter.notifyDataSetChanged();
            }
        });
    }

//    private List<MoneyModel> getData1() {
//        List<MoneyModel> list = new ArrayList<>();
//        dbHelper = new DataBaseHelper(getActivity(), "PersonalAssistant.db", null, 1);
//        db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from Money where Money_state = 1 " +
//                "order by Money_endDate", new String[]{});
//        while (cursor.moveToNext()) {
////            String a1 = cursor.getString(cursor.getColumnIndex("Schedule_details"));
////            String a2 = cursor.getString(cursor.getColumnIndex("Schedule_beginschedule"));
//            MoneyModel moneyModel = new MoneyModel();
//            moneyModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
//            moneyModel.setMoney_assetName(cursor.getString(cursor.getColumnIndex("Money_assetName")));
//            moneyModel.setMoney_asset(cursor.getDouble(cursor.getColumnIndex("Money_asset")));
//            moneyModel.setMoney_beginDate(cursor.getString(cursor.getColumnIndex("Money_beginDate")));
//            moneyModel.setMoney_endDate(cursor.getString(cursor.getColumnIndex("Money_endDate")));
//            moneyModel.setMoney_yield(cursor.getDouble(cursor.getColumnIndex("Money_yield")));
//            moneyModel.setMoney_remark(cursor.getString(cursor.getColumnIndex("Money_remark")));
//            moneyModel.setMoney_anticipated_income(cursor.getDouble(cursor.getColumnIndex("Money_anticipated_income")));
//            list.add(moneyModel);
////            Log.e("test",moneyModel.getMoney_beginDate() +";" + moneyModel.get);
//        }
//        db.close();
//        return list;
//    }

    private List<ItembackupInfoBean> getData2() {
        List<ItembackupInfoBean> list = new ArrayList<>();
        dbHelper = new DataBaseHelper(getActivity(), "QuXing.db", null, 1);
        db = dbHelper.getReadableDatabase();
//        db.execSQL("create table aa(id_rows integer primary key)");
//        db.execSQL("insert into aa (id_rows) values(1)");
//        db.execSQL("insert into Itembackupinfo (itemid) values(1)");
        Cursor cursor = db.rawQuery("select * from Itembackupinfo order by itemid", new String[]{});
        while (cursor.moveToNext()) {
//            String a1 = cursor.getString(cursor.getColumnIndex("Schedule_details"));
//            String a2 = cursor.getString(cursor.getColumnIndex("Schedule_beginschedule"));
            ItembackupInfoBean itembackupInfoBean = new ItembackupInfoBean();
            itembackupInfoBean.setItemid(cursor.getInt(cursor.getColumnIndex("itemid")));
            itembackupInfoBean.setItemname(cursor.getString(cursor.getColumnIndex("itemname")));
            itembackupInfoBean.setItemtime(cursor.getString(cursor.getColumnIndex("itemtime")));
            itembackupInfoBean.setEnrolment(cursor.getInt(cursor.getColumnIndex("enrolment")));
            itembackupInfoBean.setFollownumber(cursor.getInt(cursor.getColumnIndex("follownumber")));
            itembackupInfoBean.setHostname(cursor.getString(cursor.getColumnIndex("hostname")));
            itembackupInfoBean.setItemlabel(cursor.getInt(cursor.getColumnIndex("itemlabel")));
            itembackupInfoBean.setCreatetime(cursor.getString(cursor.getColumnIndex("createtime")));
            itembackupInfoBean.setCallnumber(cursor.getString(cursor.getColumnIndex("callnumber")));
            itembackupInfoBean.setDetails(cursor.getString(cursor.getColumnIndex("details")));
            itembackupInfoBean.setMoney(cursor.getInt(cursor.getColumnIndex("money")));
            itembackupInfoBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            list.add(itembackupInfoBean);
        }
        db.close();
        dbHelper.close();
        return list;
    }
    /**
     * 条目点击时被调用，打开编辑界面，同时传入当前信息
     */

    /**
     * ViewHolder静态类
     */
    static class ViewHolder {
        public TextView date;
        public TextView itemname;
        public TextView address;
        public TextView money;
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
//            return 1;//测试数据
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
                convertView = mInflater.inflate(R.layout.main_item, null);

                holder.date = (TextView) convertView.findViewById(R.id.item_date);
                holder.itemname = (TextView) convertView.findViewById(R.id.item_name);
                holder.address = (TextView) convertView.findViewById(R.id.item_address);
                holder.money = (TextView) convertView.findViewById(R.id.item_money);
                /**
                 * 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                 */
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

//            int pos = getArguments().getInt(ARG_POSITION);
//            if (pos == 2) {
            holder.date.setText(String.valueOf(data.get(position).getItemtime()));
            holder.itemname.setText(String.valueOf(data.get(position).getItemname()));
            holder.address.setText(String.valueOf(data.get(position).getAddress()));
            holder.money.setText(String.valueOf(data.get(position).getMoney()));
            return convertView;
//            } else {
//                return null;
//            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        onCreate(null);
    }

}






