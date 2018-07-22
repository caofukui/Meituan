package com.baidu.meituan.homeadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.meituan.R;
import com.baidu.meituan.homebean.SearchBean;
import com.bumptech.glide.Glide;

import java.util.List;


public class SearchAdapter extends BaseAdapter{
    private Context context;
    private List<SearchBean.DataBean> list;

    public SearchAdapter(Context context, List<SearchBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public SearchBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.aserch_item,null,false);
            holder=new ViewHolder();
            holder.imageView=convertView.findViewById(R.id.search_item_img);
            holder.tvTitle=convertView.findViewById(R.id.search_item_title);
            holder.tvTime=convertView.findViewById(R.id.search_item_time);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvTime.setText(list.get(position).getDelivery_time_tip());
        holder.tvTitle.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getPic_url()).into(holder.imageView);
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView tvTitle;
        TextView tvTime;
    }

}
