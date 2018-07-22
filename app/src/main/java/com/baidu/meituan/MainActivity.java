package com.baidu.meituan;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.baidu.meituan.fragment.HomePageFragment;
import com.baidu.meituan.fragment.MyFragment;
import com.baidu.meituan.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private HomePageFragment homePageFragment;
    private OrderFragment orderFragment;
    private MyFragment myFragment;
    private FragmentManager mFragmentManager ;
    private FragmentTransaction mFragmentTransaction ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setClick(0);

    }

    private void setClick(int type) {
        //开启事务
        mFragmentTransaction  = mFragmentManager.beginTransaction();
        //显示之前将所有的fragment都隐藏起来,在去显示我们想要显示的fragment
        hideFragment(mFragmentTransaction);
        switch (type) {
            case 0:
                //如果fragment是null的话,就创建一个
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    //加入事务
                    mFragmentTransaction.add(R.id.main_frame, homePageFragment);
                } else {
                    //如果fragment不为空就显示出来
                    mFragmentTransaction.show(homePageFragment);
                }
                break;
            case 1:
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    //加入事务
                    mFragmentTransaction.add(R.id.main_frame, orderFragment);
                } else {
                    //如果fragment不为空就显示出来
                    mFragmentTransaction.show(orderFragment);
                }
                break;
            case 2:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    //加入事务
                    mFragmentTransaction.add(R.id.main_frame, myFragment);
                } else {
                    //如果fragment不为空就显示出来
                    mFragmentTransaction.show(myFragment);
                }
                break;
        }
        //提交事务
        mFragmentTransaction.commit();
    }

    /**
     * 用来隐藏fragment的方法
     *
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果此fragment不为空的话就隐藏起来
        if (homePageFragment != null) {
            fragmentTransaction.hide(homePageFragment);
        }
        if (orderFragment != null) {
            fragmentTransaction.hide(orderFragment);
        }
        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }

    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.main_rg);
        mFragmentManager = getSupportFragmentManager();//获取到fragment的管理对象
        //RadioGroup的选中监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_home:
                        setClick(0);
                        break;
                    case R.id.btn_order:
                        setClick(1);
                        break;
                    case R.id.btn_my:
                        setClick(2);
                        break;
                }
            }
        });
    }




}
