package com.baidu.meituan.homeadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.meituan.R;
import com.baidu.meituan.homebean.HomeBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder>{
    private Context context;
    private List<HomeBean.DataBean> list;

    public HomeAdapter(Context context, List<HomeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_adapter, parent, false);

        return new HomeHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        String name = list.get(position).getName();
        String month_sales_tip = list.get(position).getMonth_sales_tip();
        String s = list.get(position).getDelivery_time_tip() + "/" + list.get(position).getDistance();
        String s1 = list.get(position).getMin_price_tip() + "|" + list.get(position).getShipping_fee_tip() + "|" + list.get(position).getAverage_price_tip();
        String info = list.get(position).getDiscounts2().get(0).getInfo();
        String info1 = list.get(position).getDiscounts2().get(1).getInfo();
        String pic_url = list.get(position).getPic_url();
        String icon_url = list.get(position).getDiscounts2().get(0).getIcon_url();
        String icon_url1 = list.get(position).getDiscounts2().get(1).getIcon_url();
        holder.bingContext(name,month_sales_tip,s,s1,info,info1,pic_url,icon_url,icon_url1);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeHolder extends RecyclerView.ViewHolder{
        private Context context;
        TextView home_item_title;
        TextView home_item_month_sales_tip;
        TextView home_item_distance;
        TextView home_item_min_price_tip;
        TextView home_item_discounts2_imfo1;
        TextView home_item_discounts2_imfo2;
        ImageView home_item_discounts2_icon1;
        ImageView home_item_discounts2_icon2;
        ImageView home_item_image;
        public HomeHolder(View itemView, Context context) {
            super(itemView);
            this.context=context;
            home_item_title = itemView.findViewById(R.id.home_item_title);
            home_item_month_sales_tip = itemView.findViewById(R.id.home_item_month_sales_tip);
            home_item_distance = itemView.findViewById(R.id.home_item_distance);
            home_item_min_price_tip = itemView.findViewById(R.id.home_item_min_price_tip);
            home_item_discounts2_imfo1 = itemView.findViewById(R.id.home_item_discounts2_imfo1);
            home_item_discounts2_imfo2 = itemView.findViewById(R.id.home_item_discounts2_imfo2);
            home_item_discounts2_icon1 = itemView.findViewById(R.id.home_item_discounts2_icon1);
            home_item_discounts2_icon2 = itemView.findViewById(R.id.home_item_discounts2_icon2);
            home_item_image = itemView.findViewById(R.id.home_item_image);

        }


        public void bingContext(String name, String month_sales_tip, String s, String s1, String info, String info1, String pic_url, String icon_url, String icon_url1) {
            home_item_title.setText(name);
            home_item_month_sales_tip.setText(month_sales_tip);
            home_item_distance.setText(s);
            home_item_min_price_tip.setText(s1);
            home_item_discounts2_imfo1.setText(info);
            home_item_discounts2_imfo2.setText(info1);
            Glide.with(context).load(pic_url).into(home_item_image);
            Glide.with(context).load(icon_url).into(home_item_discounts2_icon1);
            Glide.with(context).load(icon_url1).into(home_item_discounts2_icon2);
        }
    }

}


