package com.baidu.meituan.homepresenter;

import com.baidu.meituan.fragment.HomePageFragment;
import com.baidu.meituan.homebean.HomeBean;
import com.baidu.meituan.homemodel.HomeModel;
import com.baidu.meituan.homemodel.IhomeModel;
import com.baidu.meituan.homeview.IhomeView;

public class HomePresenter implements IhomePresenter {
    private IhomeView ihomeView;
    private HomeModel homeModel;

    public HomePresenter(IhomeView ihomeView) {
         this.ihomeView=ihomeView;
         this.homeModel=new HomeModel();
    }

    public void setData(int page){
        homeModel.getData(new IhomeModel() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                ihomeView.onSuccess(homeBean);
            }

            @Override
            public void onError(String s) {
                ihomeView.onError(s);
            }
        },page);
    }

    @Override
    public void onDestroys() {
        if (ihomeView!=null){
            ihomeView=null;
        }
    }
}
