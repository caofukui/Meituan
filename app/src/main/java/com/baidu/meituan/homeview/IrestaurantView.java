package com.baidu.meituan.homeview;

import com.baidu.meituan.homebean.RestaurantBean;

public interface IrestaurantView {
    void onSuccess(RestaurantBean restaurantBean);
    void onError(int code);
}
