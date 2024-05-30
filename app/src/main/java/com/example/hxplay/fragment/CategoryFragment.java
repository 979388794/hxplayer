package com.example.hxplay.fragment;

import android.view.View;
import android.widget.ListView;

import androidx.viewpager.widget.ViewPager;

import com.example.hxplay.R;
import com.example.hxplay.adapter.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;


public class CategoryFragment extends BaseFragment {
    private TabLayout tab_layout;
    private Banner banner;
    private ViewPager view_pager;
    private ListView jd_listView;
    private ViewPageAdapter newTabAdapter;
    private static final String TAG = CategoryFragment.class.getSimpleName();

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_jiandang, null);
        tab_layout = view.findViewById(R.id.tab_layout);
        banner = view.findViewById(R.id.banner);
        view_pager = view.findViewById(R.id.view_pager);
        jd_listView = view.findViewById(R.id.jd_listView);
        return view;
    }


}
