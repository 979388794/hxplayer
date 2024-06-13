package com.example.hxplay.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hxplay.R;
import com.example.hxplay.adapter.ViewPageAdapter;
import com.example.hxplay.utils.ImageFilter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class UserFragment extends Fragment {

    private ImageView ivBg;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_user, null);
        ivBg = view.findViewById(R.id.iv_bg);
        collapsingToolbarLayout = view.findViewById(R.id.toolbar_layout);
        appBarLayout = view.findViewById(R.id.appbar_layout);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData() {
        //拿到初始图
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.author);
        //处理得到模糊效果的图
        Bitmap blurBitmap = ImageFilter.blurBitmap(getContext(), bmp, 25f);
        ivBg.setImageBitmap(blurBitmap);

//        伸缩偏移量监听
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {//收缩时
                    collapsingToolbarLayout.setTitle("HxPlayer");
                    isShow = true;
                } else if (isShow) {//展开时
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });

        final ArrayList<Fragment> fragments = new ArrayList<>();
        final String[] tabName = {"动态", "博客", "分类专栏"};

        for (int i = 0; i < tabName.length; i++) {
            fragments.add(new PersonTabFragment(tabName[i]));
        }
        ViewPageAdapter fragTabAdapter = new ViewPageAdapter(getChildFragmentManager(), getLifecycle(),
                fragments);
        viewPager.setAdapter(fragTabAdapter);
        //TabLayout和Viewpager2进行关联
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                tab.setText(tabName[position]);
            }
        }).attach();
    }

}
