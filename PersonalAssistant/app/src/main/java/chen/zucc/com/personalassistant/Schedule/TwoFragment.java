package chen.zucc.com.personalassistant.Schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chen.zucc.com.personalassistant.R;

/**
 * Created by chenchongkang on 2017/7/5.
 */

public class TwoFragment extends Fragment {

    private TextView tv;
    private String name;

    public TwoFragment(String fName){
        this.name = fName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_two,container,false);
        tv = (TextView) view.findViewById(R.id.fragment_tv);
        tv.setText(name);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("我变了-"+ name);
            }
        });
        return view;
    }
}