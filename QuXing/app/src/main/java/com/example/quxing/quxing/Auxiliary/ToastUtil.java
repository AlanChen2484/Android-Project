package com.example.quxing.quxing.Auxiliary;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by 陈若韬 on 2018/4/26.
 */

public class ToastUtil {

    private static Toast toast;
    /**
     * 强大的可以连续弹的Toast
     * @param text
     */
    public static void showToast(String text){
        if(toast==null){
            //创建对象
            toast = Toast.makeText(MyApplication.getAppContext(), text, Toast.LENGTH_LONG);
        }else {
            //说明Toast已经存在了，那么则只需要更改当前的文字内容
            toast.setText(text);
        }
        //最后你再show
        toast.show();
    }
}