package com.baidu.meituan.homepresenter;

import com.baidu.meituan.fragment.activity.DetailsActivity;
import com.baidu.meituan.homebean.RestaurantBean;
import com.baidu.meituan.homemodel.IRestaurantModel;
import com.baidu.meituan.homemodel.RestaurantModel;
import com.baidu.meituan.homeview.IrestaurantView;

public class RestaurantPresenter implements IRestaurantPresenter {
    private IrestaurantView irestaurantView;
    private RestaurantModel restaurantModel;
    public RestaurantPresenter(IrestaurantView irestaurantView) {
        this.irestaurantView=irestaurantView;
        restaurantModel=new RestaurantModel();
    }

    public void setDatas(String page){
        restaurantModel.getDatas(new IRestaurantModel() {
            @Override
            public void onSuccess(RestaurantBean restaurantBean) {
                irestaurantView.onSuccess(restaurantBean);
            }

            @Override
            public void onError(int code) {
                irestaurantView.onError(code);
            }
        },page);
    }

    @Override
    public void onDestroys() {
        if (irestaurantView!=null){
            irestaurantView=null;
        }
    }
}
