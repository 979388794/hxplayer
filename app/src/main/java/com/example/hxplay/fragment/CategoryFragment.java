package com.example.hxplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.hxplay.R;
import com.example.hxplay.adapter.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;


public class CategoryFragment extends Fragment {
    private TabLayout tab_layout;
    private Banner banner;
    private ViewPager view_pager;
    private ListView jd_listView;
    private ViewPageAdapter newTabAdapter;
    String TAG = getClass().getSimpleName();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_jiandang, null);
        tab_layout = view.findViewById(R.id.tab_layout);
        banner = view.findViewById(R.id.banner);
        view_pager = view.findViewById(R.id.view_pager);
        jd_listView = view.findViewById(R.id.jd_listView);
        return view;
    }


}
