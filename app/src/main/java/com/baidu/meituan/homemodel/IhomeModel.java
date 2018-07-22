package com.baidu.meituan.homemodel;

import com.baidu.meituan.homebean.HomeBean;

public interface IhomeModel {
    void onSuccess(HomeBean homeBean);
    void onError(String s);
}
