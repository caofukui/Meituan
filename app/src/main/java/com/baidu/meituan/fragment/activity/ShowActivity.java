package com.baidu.meituan.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.meituan.R;
import com.baidu.meituan.homeadapter.HomeAdapter;
import com.baidu.meituan.homebean.HomeBean;
import com.baidu.meituan.homepresenter.HomePresenter;
import com.baidu.meituan.homeview.IhomeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class ShowActivity extends AppCompatActivity implements IhomeView, View.OnClickListener {
    private PullToRefreshScrollView pullToRefreshScrollView;
    private RecyclerView recyclerView;
    private HomePresenter homePresenter;
    private int page=0;
    private HomeAdapter adapter;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        pullToRefreshScrollView=findViewById(R.id.show_pull_to_refresh);
        recyclerView=findViewById(R.id.show_recycler);
        textView=findViewById(R.id.show_title);
        imageView=findViewById(R.id.show_back);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        textView.setText(title);
        imageView.setOnClickListener(this);
        homePresenter = new HomePresenter(this);
        homePresenter.setData(page);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowActivity.this,LinearLayoutManager.VERTICAL,false));
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page=1;
                homePresenter.setData(page);
                pullToRefreshScrollView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page++;
                homePresenter.setData(page);
                pullToRefreshScrollView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onSuccess(final HomeBean homeBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new HomeAdapter(ShowActivity.this, homeBean.getData());
                recyclerView.setAdapter(adapter);
                pullToRefreshScrollView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_back:
                finish();
                break;
        }
    }
}
