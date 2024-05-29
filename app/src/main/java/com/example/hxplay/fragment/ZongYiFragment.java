package com.example.hxplay.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.hxplay.R;
import com.example.hxplay.activity.PlayVideoActivity;
import com.example.hxplay.adapter.MovieAdapter;
import com.example.hxplay.bean.VideoBean;
import com.example.hxplay.glide.GlideApp;
import com.example.hxplay.utils.API;
import com.example.hxplay.view.MyGridLayoutManager;
import com.example.hxplay.view.MyRecyclerView;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: henry.xue
 * @date: 2024-05-16
 */
public class ZongYiFragment extends BaseFragment {
    String TAG = this.getClass().getSimpleName();
    private List<VideoBean.Movie> movieList;
    private MovieAdapter movieAdapter;
    private Banner banner;
    private Context mContext;
    View rootview;
    MyRecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }


    @Override
    public View initView() {
        Log.d(TAG, "onCreateView-----");
        if (rootview == null) {
            Log.d(TAG, "onCreateView--------rootview为null");
            rootview = View.inflate(getContext(), R.layout.fragment_main, null);
            banner = rootview.findViewById(R.id.banner);
            recyclerView = rootview.findViewById(R.id.recyclerview);
            //启用嵌套滚动
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setFocusableInTouchMode(false);
            recyclerView.setflingScale(3.0); // 设置速度缩放因子为2.0，使滑动速度变快一倍
        }
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume----------");
        // 将数据加载逻辑放到onResume()方法中
        if (movieAdapter == null) {
            Log.d(TAG, "onResume-----------getMoviewData");
            getMoviewData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView------");
    }


    public void initBanner() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.zongyi1);
        imageList.add(R.drawable.zongyi2);
        imageList.add(R.drawable.zongyi3);
        banner.setAdapter(new BannerImageAdapter<Integer>(imageList) {
                    @Override
                    public void onBindView(BannerImageHolder holder, Integer resourceId, int position, int size) {
                        GlideApp.with(holder.itemView)
                                .load(resourceId)
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                .fitCenter()
                                .centerCrop()
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getActivity()));
    }

    /**
     * 电影数据
     */
    public void getMoviewData() {
        Log.d(TAG, "请求数据--------------------getMoviewData");
        Request request = new Request.Builder()
                .url(API.ZONGYI)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    VideoBean movieBean = gson.fromJson(result, VideoBean.class);
                    // 更新数据源
                    List<VideoBean.Movie> newMovieList = movieBean.getData();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 如果适配器已经存在，直接更新数据源并刷新
//                            if (movieAdapter != null) {
//                                movieAdapter.updateData(newMovieList);
//                            } else {
                            // 如果适配器不存在，创建新的适配器并设置
                            movieList = newMovieList;
                            movieAdapter = new MovieAdapter(getActivity(), movieList);
                            MyGridLayoutManager manager = new MyGridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(movieAdapter);
//                            }
                            movieAdapter.setItemClickListener(new MovieAdapter.MyItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                                    intent.putExtra("videoId", movieBean.getData().get(position).getVideoId());
                                    intent.putExtra("title", movieBean.getData().get(position).getTitle());
                                    intent.putExtra("descs", movieBean.getData().get(position).getDescs());
                                    intent.putExtra("cover", movieBean.getData().get(position).getCover());
                                    getActivity().startActivity(intent);
                                }
                            });
                        }
                    }, 100);
                }
            }
        });
    }

}
