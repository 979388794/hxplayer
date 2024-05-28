package com.example.hxplay.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.hxplay.R;
import com.example.hxplay.adapter.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


public class JianDangFragment extends BaseFragment {
    private TabLayout tab_layout;
    private Banner banner;
    private ViewPager view_pager;
    private ListView jd_listView;
    private ViewPageAdapter newTabAdapter;
    private static final String TAG = JianDangFragment.class.getSimpleName();

    @Override
    public View initView() {
        Log.i(TAG,"智慧建党的视图被初始化了");
        View view = View.inflate(getContext(), R.layout.fragment_jiandang, null);
        tab_layout = view.findViewById(R.id.tab_layout);
        banner = view.findViewById(R.id.banner);
        view_pager = view.findViewById(R.id.view_pager);
        jd_listView = view.findViewById(R.id.jd_listView);
        return view;
    }

    @Override
    public void initData() {
        String titles[] = {"党建展示", "党建动态","组织活动","建言献策","随手拍"};
        List<Fragment> fragmentList;
        fragmentList  = new ArrayList<>();
        fragmentList.add(new JDisplayFragment());
        fragmentList.add(new JDongtaiFragment());
        fragmentList.add(new JActivityFragment());
        fragmentList.add(new JAdviseFragment());
        fragmentList.add(new JPhotoFragment());
        newTabAdapter = new ViewPageAdapter(getChildFragmentManager(),getLifecycle(),fragmentList);
//        view_pager.setAdapter(newTabAdapter);
//        tab_layout.setupWithViewPager(view_pager);
    }


}
