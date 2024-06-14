package com.example.hxplay.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hxplay.R;
import com.example.hxplay.adapter.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private ViewPager2 viewPager;
    TabLayout tab_layout;
    String[] title = {"国产剧", "综艺", "日韩动漫", "喜剧片", "爱情片", "纪录片", "欧美剧", "韩剧"};
    private ViewPageAdapter newTabAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        viewPager = view.findViewById(R.id.view_pager);
        tab_layout = view.findViewById(R.id.tab_layout);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragments();
    }


    public void initFragments() {
        List<Fragment> fragmentlist = new ArrayList<>();
        fragmentlist.add(new GuoChanFragment());
        fragmentlist.add(new ZongYiFragment());
        fragmentlist.add(new RiHanDongManFragment());
        fragmentlist.add(new XiJuPianFragment());
        fragmentlist.add(new AiQingPianFragment());
        fragmentlist.add(new JiLuPianFragment());
        fragmentlist.add(new OuMeiJuFragment());
        fragmentlist.add(new HanJuFragment());
        newTabAdapter = new ViewPageAdapter(getChildFragmentManager(), getLifecycle(), fragmentlist);
        viewPager.setAdapter(newTabAdapter);
        viewPager.setOffscreenPageLimit(fragmentlist.size());

        //TabLayout和Viewpager2进行关联
        new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setCustomView(getViewAtI(position));
            }
        }).attach();
        // 手动选中第一个Tab
        tab_layout.getTabAt(0).select();
        // 为第一个Tab设置选中时的样式
        TabLayout.Tab firstTab = tab_layout.getTabAt(0);
        if (firstTab != null) {
            // 获取第一个Tab对应的CustomView
            View firstTabView = firstTab.getCustomView();
            if (firstTabView != null) {
                TextView firstTabTitle = firstTabView.findViewById(R.id.tv_title);
                firstTabTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_text_selected));
                firstTabTitle.setTextSize(20);
            }
        }
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Tab 被选中
                int position = tab.getPosition();
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView title = customView.findViewById(R.id.tv_title);
                    title.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_text_selected));
                    title.setTextSize(20);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Tab 取消选中
                int position = tab.getPosition();
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView title = customView.findViewById(R.id.tv_title);
                    title.setTextColor(ContextCompat.getColor(getContext(), R.color.tab_text_unselected));
                    title.setTextSize(16);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Tab 被重新选中（点击已选中的 Tab）
            }
        });

    }

    private View getViewAtI(int position) {
        View view = getLayoutInflater().inflate(R.layout.tab_text, null, false);
        TextView textView = view.findViewById(R.id.tv_title);
        // 这个icons就是一个简单的图片保存的数组，保存如R.drawable.touxiang
        textView.setText(title[position]);
        return view;
    }


    /**
     * 更新标签文字
     */
    private void updateTabText(TabLayout.Tab tab, boolean isSelect) {
        if (isSelect) {
            //选中文字加大加粗
            TextView tabSelect = tab.getCustomView().findViewById(R.id.tv_title);
            tabSelect.setTextSize(22);
            tabSelect.setTextColor(Color.parseColor("#FFC107"));
            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setText(tab.getText());
        } else {
            TextView tabUnSelect = tab.getCustomView().findViewById(R.id.tv_title);
            tabUnSelect.setTextSize(14);
            tabUnSelect.setTextColor(Color.BLACK);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setText(tab.getText());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
