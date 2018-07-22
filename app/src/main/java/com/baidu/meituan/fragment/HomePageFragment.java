package com.baidu.meituan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.meituan.R;
import com.baidu.meituan.fragment.activity.LocationActivity;
import com.baidu.meituan.fragment.activity.SearchActivity;
import com.baidu.meituan.homeadapter.HomeAdapter;
import com.baidu.meituan.homeadapter.MyGridViewAdapter;
import com.baidu.meituan.homeadapter.MyViewPagerAdapter;
import com.baidu.meituan.homebean.HomeBean;
import com.baidu.meituan.homebean.ProductListBean;
import com.baidu.meituan.homepresenter.HomePresenter;
import com.baidu.meituan.homeview.IhomeView;
import com.baidu.meituan.homeview.MyView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements IhomeView, ViewPager.OnPageChangeListener, View.OnClickListener {
    private PullToRefreshScrollView pullToRefreshScrollView;
    private RecyclerView recyclerView;
    private HomePresenter homePresenter;
    private HomeAdapter adapter;
    private MyView myView;
    private int page=0;
    private ViewPager viewPager;
    private int totalPage;//总的页数
    private int mPageSize = 8;//每页显示的最大数量
    private List<ProductListBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private static final int CODE_UPDATE_HOME_LIST = 1;
    private List<HomeBean.DataBean> list =new ArrayList<>();
    private LinearLayout linearLayout;
    private TextView textView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_UPDATE_HOME_LIST:
                    HomeBean homeBean = (HomeBean) msg.obj;
                    list.addAll(homeBean.getData());
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    //private int currentPage;//当前页
    private int[] imgs={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,
            R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k};
    private String[] proName = {"美食","美团超市","生鲜果蔬","下午茶","正餐优选","汉堡披萨",
            "跑腿代购","快餐简餐","地方菜","炸鸡美食","免费送费"};

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
       pullToRefreshScrollView=view.findViewById(R.id.pull_to_refresh);
       recyclerView=view.findViewById(R.id.recyler_view);
       viewPager =view.findViewById(R.id.home_viewpager);
       myView=view.findViewById(R.id.home_sousuo);
       linearLayout=view.findViewById(R.id.home_dingwei);
       textView=view.findViewById(R.id.home_addr);
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       linearLayout.setOnClickListener(this);
        homePresenter=new HomePresenter(this);
        homePresenter.setData(page);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        adapter = new HomeAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page=1;
                homePresenter.setData(page);
                pullToRefreshScrollView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page++;
                homePresenter.setData(page);
                pullToRefreshScrollView.onRefreshComplete();
            }
        });
        myView.setSetOnCallBack(new MyView.SetOnCallBack() {
            @Override
            public void tiao() {
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });
        //模拟数据源
        setDatas();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        //总的页数，取整（这里有三种类型：Math.ceil(3.5)=4:向上取整，只要有小数都+1  Math.floor(3.5)=3：向下取整  Math.round(3.5)=4:四舍五入）
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for(int i=0;i<totalPage;i++){
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview,viewPager,false);
            gridView.setAdapter(new MyGridViewAdapter(getActivity(),listDatas,i,mPageSize));
            //添加item点击监听
            /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + currentPage*mPageSize;
                    Log.i("TAG","position的值为："+position + "-->pos的值为："+pos);
                    Toast.makeText(MainActivity.this,"你点击了 "+listDatas.get(pos).getProName(),Toast.LENGTH_SHORT).show();
                }
            });*/
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        //设置ViewPager滑动监听
        viewPager.addOnPageChangeListener(this);
    }
    private void setDatas() {
        listDatas = new ArrayList<>();
        for(int i=0;i<proName.length;i++){
            listDatas.add(new ProductListBean(proName[i],imgs[i]));
        }
    }

    @Override
    public void onSuccess(final HomeBean homeBean) {
        //子线程切换到主线程中
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = CODE_UPDATE_HOME_LIST;
                message.obj = homeBean;
                mHandler.sendMessage(message);

            }
        });
    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void onDestroy() {
        homePresenter.onDestroys();
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_dingwei:
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                //在fragment中开启Activity跳转不需要用上下文调用
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==101){
            String address = data.getStringExtra("address");
            Toast.makeText(getActivity(),address,Toast.LENGTH_SHORT).show();
            textView.setText(address);
        }
    }
}
