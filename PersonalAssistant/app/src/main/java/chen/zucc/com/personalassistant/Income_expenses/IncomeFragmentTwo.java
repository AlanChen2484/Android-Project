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
public class IncomeFragmentTwo extends Fragment{
    private ImageButton btn;
    private TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_incometwo, container, false);
        btn = (ImageButton) view.findViewById(R.id.imageBtn_s2);
        tv = (TextView) view.findViewById(R.id.textView_s2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2017, 6, 9).show();
            }
        });
        return view;
    }
}
