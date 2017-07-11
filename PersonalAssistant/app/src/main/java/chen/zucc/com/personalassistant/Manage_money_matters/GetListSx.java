package chen.zucc.com.personalassistant.Manage_money_matters;

import android.content.Context;

import chen.zucc.com.personalassistant.R;

import java.util.ArrayList;
import java.util.List;


public class GetListSx {

    public static List<InfoSx> lists = new ArrayList<InfoSx>();

    public static String getNumber(Context context){

        String[] title = {"小明","小红","小白","小钱", "小李", "小陈",
                "小明","小红","小白","小钱", "小李", "小陈"};
        String[] texts = {"你好啊","嘿嘿嘿","哈哈哈","呵呵呵", "嘻嘻嘻", "啧啧啧",
                "你好啊","嘿嘿嘿","哈哈哈","呵呵呵", "嘻嘻嘻", "啧啧啧"};

        int[] resIds = {R.mipmap.ic_action_bill , R.mipmap.ic_action_bill, R.mipmap.ic_action_bill, R.mipmap.ic_action_bill,
                R.mipmap.ic_action_bill, R.mipmap.ic_action_bill, R.mipmap.ic_action_bill ,
                R.mipmap.ic_action_bill, R.mipmap.ic_action_bill,R.mipmap.ic_action_bill, R.mipmap.ic_action_bill, R.mipmap.ic_action_bill};

        for(int i = 0 ; i < title.length ; i++){
            InfoSx infoSx = new InfoSx(title[i], texts[i], resIds[i]);
            lists.add(infoSx);
        }
        return null;
    }
}
