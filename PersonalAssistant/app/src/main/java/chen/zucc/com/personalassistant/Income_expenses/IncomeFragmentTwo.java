package chen.zucc.com.personalassistant.Income_expenses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenchongkang on 2017/7/7.
 */
public class IncomeFragmentTwo extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(chen.zucc.com.personalassistant.R.layout.fragment_incometwo,container,false);
        return view;
    }
}
