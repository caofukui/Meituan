package com.baidu.meituan.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.baidu.meituan.R;
import com.baidu.meituan.homebean.RestaurantBean;
import com.baidu.meituan.homepresenter.RestaurantPresenter;
import com.baidu.meituan.homeview.IrestaurantView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener,IrestaurantView {
    private TextView tvBack;
    private TextView textView;
    private TextView textView02;
    private String page;
    private String name;
    private RestaurantPresenter restaurantPresenter;
    private ImageView iv_img;
    private TextView tvTitle;
    private TextView tvBulletin;
    private ViewFlipper viewFlipper;
    private ImageView cus_img;
    private TextView cus_title;

    private List<RestaurantBean.DataBean.Discounts2Bean> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        Intent intent = getIntent();
        page = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
       // Toast.makeText(DetailsActivity.this,page,Toast.LENGTH_SHORT).show();
        textView.setText(name);
        tvBack.setOnClickListener(this);
        restaurantPresenter=new RestaurantPresenter(this);
        restaurantPresenter.setDatas(page);


    }

    private void initView() {
        tvBack=findViewById(R.id.details_back);
        textView=findViewById(R.id.details_titile);
        textView02=findViewById(R.id.details_geng);
        iv_img=findViewById(R.id.de_img);
        tvTitle=findViewById(R.id.de_title);
        tvBulletin=findViewById(R.id.de_bulletin);
        viewFlipper=findViewById(R.id.details_filpper);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(final RestaurantBean restaurantBean) {
        list = restaurantBean.getData().getDiscounts2();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(DetailsActivity.this).load(restaurantBean.getData().getPic_url()).into(iv_img);
                tvTitle.setText(restaurantBean.getData().getMin_price_tip()+"|"+restaurantBean.getData().getShipping_fee_tip()+"|"+restaurantBean.getData().getDelivery_time_tip());
                tvBulletin.setText(restaurantBean.getData().getBulletin());

               //
                for (int i = 0; i < list.size(); i++) {
                    Toast.makeText(DetailsActivity.this,"长度"+list.size(),Toast.LENGTH_SHORT).show();
                    View view = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.layout_custom, null);
                    cus_img= view.findViewById(R.id.cus_img);
                    cus_title= view.findViewById(R.id.cus_text);
                    Glide.with(DetailsActivity.this).load(list.get(i).getIcon_url()).into(cus_img);
                    cus_title.setText(list.get(i).getInfo());
                    viewFlipper.addView(view);
                }
            }
        });
    }

    @Override
    public void onError(int code) {

    }
}
