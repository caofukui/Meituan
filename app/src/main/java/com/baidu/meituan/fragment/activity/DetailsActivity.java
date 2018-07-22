package com.baidu.meituan.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.meituan.R;
import com.baidu.meituan.homebean.RestaurantBean;
import com.baidu.meituan.homepresenter.RestaurantPresenter;
import com.baidu.meituan.homeview.IrestaurantView;
import com.bumptech.glide.Glide;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        Intent intent = getIntent();
        page = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        Toast.makeText(DetailsActivity.this,page,Toast.LENGTH_SHORT).show();
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(DetailsActivity.this).load(restaurantBean.getData().getPic_url()).into(iv_img);
                tvTitle.setText(restaurantBean.getData().getMin_price_tip()+"|"+restaurantBean.getData().getShipping_fee_tip()+"|"+restaurantBean.getData().getDelivery_time_tip());
                tvBulletin.setText(restaurantBean.getData().getBulletin());
            }
        });
    }

    @Override
    public void onError(int code) {

    }
}
