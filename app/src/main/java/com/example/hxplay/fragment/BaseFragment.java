package com.example.hxplay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hxplay.R;
import com.example.hxplay.utils.SSLHelper;
import com.example.hxplay.view.MyRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.youth.banner.Banner;

import okhttp3.OkHttpClient;


public abstract class BaseFragment extends Fragment {
    private Context mContext;
    public OkHttpClient client;
    View rootview;
    Banner banner;
    Handler handler;
    String TAG = getClass().getSimpleName();
    RefreshLayout refreshLayout;
    MyRecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        SSLHelper.configureSSL(okBuilder);
        client = okBuilder.build();
        handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView-----");
        if (rootview == null) {
            Log.d(TAG, "onCreateView--------rootview为null");
            rootview = View.inflate(getContext(), R.layout.fragment_main, null);
            banner = rootview.findViewById(R.id.banner);
            recyclerView = rootview.findViewById(R.id.recyclerview);
            refreshLayout = rootview.findViewById(R.id.refreshLayout);
            refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
            refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
            refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
            refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
//            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//                @Override
//                public void onLoadMore(RefreshLayout refreshlayout) {
//                    Log.d(TAG, "-------onLoadMore---------");
//                    refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
//                    getMoviewData(++number);
//                    movieAdapter.notifyDataSetChanged();
//                }
//            });
            //启用嵌套滚动
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setFocusableInTouchMode(false);
            recyclerView.setflingScale(3.0); // 设置速度缩放因子为3.0，使滑动速度变快一倍
        }
        return rootview;


    }

//    public abstract View initView();


    @Override
    public void onStart() {
        super.onStart();
//        initBanner();
        /**
         * banner已经和fragment生命周期绑定
         */
//        if (banner != null) {
//            banner.start();
//        }

    }

    @Override
    public void onStop() {
        super.onStop();
//        if (banner != null) {
//            banner.stop();
//        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    public void initBanner() {
    }


    public void initData() {

    }


}
