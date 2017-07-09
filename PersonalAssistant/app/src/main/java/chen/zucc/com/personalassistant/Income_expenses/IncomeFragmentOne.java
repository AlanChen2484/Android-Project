package chen.zucc.com.personalassistant.Income_expenses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenchongkang on 2017/7/7.
 */

public class IncomeFragmentOne extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(chen.zucc.com.personalassistant.R.layout.fragment_incomeone,container,false);
        return view;

//        ImageButton button1=(ImageButton) view.findViewById(R.id.imageButton_3);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_1 = new Intent(getActivity(), ScheduleAddActivity.class);
//               getActivity().startActivity(intent_1);
//            }
//        });
//        CardView cardView=(CardView)view.findViewById(R.id.cardView_1);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_1 = new Intent(getActivity(), EditScheduleActivity.class);
//                startActivity(intent_1);
//            }
//        });
//        ImageButton imageButton=(ImageButton)view.findViewById(R.id.imageButton_3);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_2 = new Intent(getActivity(), ScheduleAddActivity.class);
//                startActivity(intent_2);
//            }
//        });

    }
}