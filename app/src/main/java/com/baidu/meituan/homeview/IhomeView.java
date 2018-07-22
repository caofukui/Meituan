package com.baidu.meituan.homeview;

import com.baidu.meituan.homebean.HomeBean;

public interface IhomeView {
    void onSuccess(HomeBean homeBean);
    void onError(String s);
}
