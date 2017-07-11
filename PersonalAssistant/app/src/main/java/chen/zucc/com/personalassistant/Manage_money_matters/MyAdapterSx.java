package chen.zucc.com.personalassistant.Manage_money_matters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import chen.zucc.com.personalassistant.R;

import java.util.List;


public class MyAdapterSx extends BaseAdapter {

    private List<InfoSx> lists;
    private Context context;
    private RelativeLayout relativeLayout;

    public MyAdapterSx(List<InfoSx> lists, Context context){
        this.lists = lists;
        this.context = context;
    }

    /**
     * 返回集合的数量
     * @return
     */
    @Override
    public int getCount() {
        return lists.size();
    }

    /**
     * 获取当前集合的一条数据
     * @param position
     * @return
     */

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    /**
     * 获取当前的ID
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_sx, null);
            holder = new ViewHolder();

            holder.tv1 = (TextView)convertView.findViewById(R.id.tv1);
            holder.tv2 = (TextView)convertView.findViewById(R.id.tv2);
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            holder.listView = (RelativeLayout)convertView.findViewById(R.id.list_item);

            holder.tv1.setText(lists.get(position).getName());
            holder.tv2.setText(lists.get(position).getNumber());
            holder.img.setImageResource(lists.get(position).getResIds());
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
            holder.tv1.setText(lists.get(position).getName());
            holder.tv2.setText(lists.get(position).getNumber());
            holder.img.setImageResource(lists.get(position).getResIds());
        }

//        holder.tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i1 = new Intent(v.getContext(), InvestmentActivity.class);
//                v.getContext().startActivity(i1);
//            }
//        });

        holder.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(v.getContext(), InvestmentActivity.class);
                v.getContext().startActivity(i1);
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        TextView tv1;
        TextView tv2;
        ImageView img;
        RelativeLayout listView;
    }
}
