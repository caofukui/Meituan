package com.baidu.meituan.homemodel;

import com.baidu.meituan.homebean.RestaurantBean;

public interface IRestaurantModel {
    void onSuccess(RestaurantBean restaurantBean);
    void onError(int code);
}
