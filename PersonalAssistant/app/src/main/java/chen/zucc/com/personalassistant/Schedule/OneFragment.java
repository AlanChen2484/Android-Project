package chen.zucc.com.personalassistant.Schedule;


import android.app.AlertDialog;
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
        ImageButton btn_1=(ImageButton)view.findViewById(R.id.imageBtn_ture);
        btn_1.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {

                                     }
                                 });
        ImageButton imageButton1=(ImageButton)view.findViewById(R.id.imageBtn_ture);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
                builder.setTitle("完成日程" ) ;
                builder.setMessage("是否已经完成了此日程？" ) ;
                builder.setNegativeButton("否",null);
                builder.setPositiveButton("是",null);
                builder.show();
            }
        });
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