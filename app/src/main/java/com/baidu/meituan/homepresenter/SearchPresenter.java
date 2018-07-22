package com.baidu.meituan.homepresenter;

import com.baidu.meituan.fragment.activity.SearchActivity;
import com.baidu.meituan.homebean.SearchBean;
import com.baidu.meituan.homemodel.IsearchModel;
import com.baidu.meituan.homemodel.SearchModel;
import com.baidu.meituan.homeview.IsearchView;

public class SearchPresenter implements IsearchPresenter {
    private IsearchView isearchView;
    private SearchModel searchModel;

    public SearchPresenter(IsearchView isearchView) {
        this.isearchView=isearchView;
        this.searchModel=new SearchModel();
    }

    public void setDatas(String keyword){
        searchModel.getDatas(new IsearchModel() {
            @Override
            public void onSuccess(SearchBean searchBean) {
                isearchView.onSuccess(searchBean);
            }

            @Override
            public void onError(String s) {
                isearchView.onError(s);
            }
        },keyword);
    }

    @Override
    public void onDestroys() {
        if (isearchView!=null){
            isearchView=null;
        }
    }
}
