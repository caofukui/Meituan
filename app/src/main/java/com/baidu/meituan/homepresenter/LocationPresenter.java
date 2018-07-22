package com.baidu.meituan.homepresenter;

import com.baidu.meituan.fragment.activity.LocationActivity;
import com.baidu.meituan.homebean.LocationBeans;
import com.baidu.meituan.homemodel.ILocationModel;
import com.baidu.meituan.homemodel.LocationModel;
import com.baidu.meituan.homeview.ILocationView;

public class LocationPresenter implements ILocationPresenter {
    private ILocationView iLocationView;
    private LocationModel locationModel;
    public LocationPresenter(ILocationView iLocationView) {
        this.iLocationView=iLocationView;
        locationModel=new LocationModel();
    }

    public void setDatas(){
        locationModel.getDatas(new ILocationModel() {
            @Override
            public void onSuccess(LocationBeans locationBeans) {
                iLocationView.onSuccess(locationBeans);
            }

            @Override
            public void onError(int code) {
                iLocationView.onError(code);
            }
        });
    }


    @Override
    public void onDestroys() {
        if (iLocationView!=null){
            iLocationView=null;
        }
    }
}
