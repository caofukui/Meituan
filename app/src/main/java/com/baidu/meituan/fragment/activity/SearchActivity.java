package com.baidu.meituan.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.meituan.R;
import com.baidu.meituan.homeadapter.SearchAdapter;
import com.baidu.meituan.homebean.SearchBean;
import com.baidu.meituan.homepresenter.SearchPresenter;
import com.baidu.meituan.homeview.IsearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener,IsearchView, AdapterView.OnItemClickListener{
    private EditText editText;
    private Button button;
    private ImageView imageView;
    private SearchPresenter searchPresenter;
    private String keyword;
    private ListView listView;
    //一定要初始化数据集合
    private List<SearchBean.DataBean> list = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earch);
        editText=findViewById(R.id.earch_context);
        button=findViewById(R.id.earch_btn);
        imageView=findViewById(R.id.earch_img);
        listView=findViewById(R.id.search_listview);
        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
        searchPresenter=new SearchPresenter((IsearchView) this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.earch_img:
                finish();
                break;
            case R.id.earch_btn:
                sousuo();
                break;
        }
    }

    private void sousuo() {
        keyword = editText.getText().toString();
        if (!TextUtils.isEmpty(keyword)){
            searchPresenter.setDatas(keyword);
            listView.setVisibility(View.VISIBLE);
        }else if (keyword==null||keyword==""){
            Toast.makeText(SearchActivity.this,"没有此店",Toast.LENGTH_SHORT).show();
            editText.setText("");
            list.clear();
            listView.setVisibility(View.GONE);
        }else{
            Toast.makeText(SearchActivity.this,"没有此店",Toast.LENGTH_SHORT).show();
            editText.setText("");
            list.clear();
            listView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onSuccess(final SearchBean searchBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list = searchBean.getData();
                adapter = new SearchAdapter(SearchActivity.this, list);
                listView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onError(String s) {

    }

    @Override
    protected void onDestroy() {
        searchPresenter.onDestroys();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "listview的item被点击了！，点击的位置是-->" + position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
        String id1 = list.get(position).getId();
        String name = list.get(position).getName();
        intent.putExtra("id",id1);
        intent.putExtra("name",name);
        startActivity(intent);
    }


}
