package com.baidu.meituan.homeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.meituan.R;

public class MyView extends LinearLayout {
    private ImageView imageView;
    private TextView textView;
    private LinearLayout my_linear;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = inflate(context, R.layout.my_view, this);
        initView();
        setOnClickListener2();
    }

    private void setOnClickListener2() {
        my_linear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnCallBack.tiao();
            }
        });
    }

    private void initView() {
        imageView=findViewById(R.id.my_img);
        textView=findViewById(R.id.my_text);
        my_linear=findViewById(R.id.my_linear);
    }
    private SetOnCallBack setOnCallBack;

    public void setSetOnCallBack(SetOnCallBack setOnCallBack) {
        this.setOnCallBack = setOnCallBack;
    }

    public interface SetOnCallBack{
        void tiao();
    }
}
