package com.baidu.meituan.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.meituan.R;
import com.baidu.meituan.homebean.LocationBeans;
import com.baidu.meituan.homepresenter.ILocationPresenter;
import com.baidu.meituan.homepresenter.LocationPresenter;
import com.baidu.meituan.homeview.ILocationView;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener,ILocationView {
    private ImageView imageView;
    private Button button;
    private EditText editText;
    private TextView textView;
    private LocationPresenter locationPresenter;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initView();
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void initView() {
        imageView=findViewById(R.id.addr_img);
        button=findViewById(R.id.addr_btn);
        textView=findViewById(R.id.addr_dianji);
        editText=findViewById(R.id.addr_context);
        locationPresenter=new LocationPresenter((ILocationView) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addr_img:
                finish();
                break;
            case R.id.addr_btn:

                break;
            case R.id.addr_dianji:
                locationPresenter.setDatas();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        locationPresenter.onDestroys();
        super.onDestroy();
    }

    @Override
    public void onSuccess(final LocationBeans locationBeans) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                address = locationBeans.getData().getAddress();
                //Toast.makeText(LocationActivity.this,address,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("address", address);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
                setResult(101, intent);
                //    结束当前这个Activity对象的生命
                finish();
            }
        });
    }

    @Override
    public void onError(int code) {

    }
}
