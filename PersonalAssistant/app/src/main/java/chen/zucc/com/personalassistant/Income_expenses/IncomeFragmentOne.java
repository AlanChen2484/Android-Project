package chen.zucc.com.personalassistant.Income_expenses;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import chen.zucc.com.personalassistant.R;

/**
 * Created by chenchongkang on 2017/7/7.
 */

public class IncomeFragmentOne extends Fragment {
    private ImageButton btn;
    private TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_incomeone,container,false);
        btn=(ImageButton)view.findViewById(R.id.imageBtn_s1);
        tv= (TextView)view.findViewById(R.id.textView_s1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv.setText(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2017,6,9).show();
            }
        });
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