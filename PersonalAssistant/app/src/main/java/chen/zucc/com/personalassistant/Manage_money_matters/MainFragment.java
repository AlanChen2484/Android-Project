package chen.zucc.com.personalassistant.Manage_money_matters;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import chen.zucc.com.personalassistant.DataBaseHelper.DataBaseHelper;
import chen.zucc.com.personalassistant.R;

public class MainFragment extends Fragment {
    private static final String ARG_POSITION = "position";
    private ListView listView;
    private MyAdapterSx adapter;
    private RecyclerView mRvTextList;

//    private SimpleCursorAdapter adapter = null;
//    private DataBaseHelper dbHelper;
//    private SQLiteDatabase dbRead;

//    public static final int REQUEST_CODE_ADD_NOTE = 1;
//    public static final int REQUEST_CODE_EDIT_NOTE = 2;

    public static MainFragment newInstance(int position) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position = getArguments().getInt(ARG_POSITION);

        if (position == 1) {

//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main, container, false);
//            Button btn_1 = (Button) view.findViewById(R.id.btnAddNote);
//            btn_1.setOnClickListener(btnAddNote_clickHandler);

            View view = inflater.inflate(R.layout.fragment_main, null);
            //绑定xml中的ListView，作为Item的容器
            GetListSx.getNumber(getContext());
            listView = (ListView)view.findViewById(R.id.lv);
            adapter = new MyAdapterSx(GetListSx.lists, getContext());
            listView.setAdapter(adapter);

            return view;

        } else {
            View view = inflater.inflate(R.layout.fragment_second, null);
            mRvTextList = (RecyclerView) view.findViewById(R.id.rv_text_list);
            mRvTextList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mRvTextList.setAdapter(new DynamicAdapter(getActivity()));
            return view;
        }
    }

//    private View.OnClickListener btnAddNote_clickHandler = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            // 有返回结果的开启编辑日志的Activity，
//            // requestCode If >= 0, this code will be returned
//            // in onActivityResult() when the activity exits.
//            startActivityForResult(new Intent(getActivity(),
//                    InvestmentActivity.class), REQUEST_CODE_ADD_NOTE);
//        }
//    };
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//
//        // 操作数据库
//        dbHelper = new DataBaseHelper(this);
//        dbRead = dbHelper.getReadableDatabase();
//
//        // 查询数据库并将数据显示在ListView上。
//        // 建议使用CursorLoader，这个操作因为在UI线程，容易引起无响应错误
//        adapter = new SimpleCursorAdapter(MainFragment.this, R.layout.notes_list_cell, null,
//                new String[]{"id", "Money_asset"},
//                new int[]{R.id.tvName, R.id.tvDate});
//        setListAdapter(adapter);
//
//        refreshNotesListView();
//
//    }
//
//    /**
//     * 复写方法，笔记列表中的笔记条目被点击时被调用，打开编辑笔记页面，同时传入当前笔记的信息
//     */
//
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//
//        // 获取当前笔记条目的Cursor对象
//        Cursor c = adapter.getCursor();
//        c.moveToPosition(position);
//
//        // 显式Intent开启编辑笔记页面
//        Intent i = new Intent(getActivity(), InvestmentActivity.class);
//
//        // 传入笔记id，name，content
//        i.putExtra(InvestmentActivity.EXTRA_NOTE_ID,
//                c.getInt(c.getColumnIndex("id")));
//        i.putExtra(InvestmentActivity.EXTRA_NOTE_NAME,
//                c.getString(c.getColumnIndex("Money_assetName")));
////        i.putExtra(InvestmentActivity.EXTRA_NOTE_CONTENT,
////                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_NOTE_CONTENT)));
//
//        // 有返回的开启Activity
////        startActivityForResult(i, REQUEST_CODE_EDIT_NOTE);
//        getActivity().startActivity(i, REQUEST_CODE_ADD_NOTE);
//
//        super.onListItemClick(l, v, position, id);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        switch (requestCode) {
//            case REQUEST_CODE_ADD_NOTE:
//            case REQUEST_CODE_EDIT_NOTE:
//                if (resultCode == Activity.RESULT_OK) {
//                    refreshNotesListView();
//                }
//                break;
//
//            default:
//                break;
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    /**
//     * 刷新笔记列表，内容从数据库中查询
//     */
//    public void refreshNotesListView() {
//        /**
//         * Change the underlying cursor to a new cursor. If there is an existing
//         * cursor it will be closed.
//         *
//         * Parameters: cursor The new cursor to be used
//         */
//        adapter.changeCursor(dbRead.query("Money", null, null,
//                null, null, null, null));
//
//    }
//
//

}





