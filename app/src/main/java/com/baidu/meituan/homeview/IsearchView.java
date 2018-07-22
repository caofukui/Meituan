package com.baidu.meituan.homeview;

import com.baidu.meituan.homebean.SearchBean;

public interface IsearchView {
    void onSuccess(SearchBean searchBean);
    void onError(String s);
}
