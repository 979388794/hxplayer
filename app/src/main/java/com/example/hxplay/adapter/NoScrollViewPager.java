package com.example.hxplay.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;


public class NoScrollViewPager extends ViewPager {
    public boolean canScroll = false;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canScroll && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, false);
    }


}
