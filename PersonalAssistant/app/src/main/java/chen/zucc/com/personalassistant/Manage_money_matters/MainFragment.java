package chen.zucc.com.personalassistant.Manage_money_matters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import chen.zucc.com.personalassistant.R;

public class MainFragment extends Fragment {
    private static final String ARG_POSITION = "position";
    private ListView listView;
    private MyAdapterSx adapter;
    private RecyclerView mRvTextList;

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

        //私信
        if (position == 1) {
            View view = inflater.inflate(R.layout.fragment_main, null);
            //绑定xml中的ListView，作为Item的容器
            GetListSx.getNumber(getContext());
            listView = (ListView)view.findViewById(R.id.lv);
            adapter = new MyAdapterSx(GetListSx.lists, getContext());
            listView.setAdapter(adapter);

            return view;
        }

        //动态
        else {
            View view = inflater.inflate(R.layout.fragment_second, null);
            mRvTextList= (RecyclerView) view.findViewById(R.id.rv_text_list);
            mRvTextList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
            mRvTextList.setAdapter(new DynamicAdapter(getActivity()));
            return view;
        }
    }
}

