package chen.zucc.com.personalassistant.Schedule;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import chen.zucc.com.personalassistant.R;

/**
 * Created by chenchongkang on 2017/7/5.
 */

public class OneFragment extends Fragment {
   private ImageButton imageButton;
    private CardView cardview;
    private TextView tv;
    private String name;
    public OneFragment(String fName){
        this.name = fName;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one,container,false);
//        ImageButton button1=(ImageButton) view.findViewById(R.id.imageButton_3);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_1 = new Intent(getActivity(), ScheduleAddActivity.class);
//               getActivity().startActivity(intent_1);
//            }
//        });
        CardView cardView=(CardView)view.findViewById(R.id.cardView_1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(getActivity(), EditScheduleActivity.class);
                startActivity(intent_1);
            }
        });
        ImageButton imageButton=(ImageButton)view.findViewById(R.id.imageButton_3);
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
                tv.setText("我变了-"+ name);
            }
        });
        return view;
    }



}