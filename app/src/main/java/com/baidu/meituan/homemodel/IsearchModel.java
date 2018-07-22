package com.baidu.meituan.homemodel;

import com.baidu.meituan.homebean.SearchBean;

public interface IsearchModel {
    void onSuccess(SearchBean searchBean);
    void onError(String s);
}
