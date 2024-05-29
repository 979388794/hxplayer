package com.example.hxplay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hxplay.utils.SSLHelper;
import com.example.hxplay.view.MyRecyclerView;
import com.youth.banner.Banner;

import okhttp3.OkHttpClient;


public abstract class BaseFragment extends Fragment {
    private Context mContext;
    public OkHttpClient client;
    MyRecyclerView rootview;
    Banner banner;
    Handler handler;

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
        return initView();
    }

    public abstract View initView();


    @Override
    public void onStart() {
        super.onStart();
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
        initBanner();
    }

    public void initBanner() {
    }


    public void initData() {

    }


}
