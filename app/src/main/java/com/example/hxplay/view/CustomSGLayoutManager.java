package com.example.hxplay.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @author: henry.xue
 * @date: 2024-05-08
 */
public class CustomSGLayoutManager extends StaggeredGridLayoutManager {
    private double speedRatio;
    public CustomSGLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomSGLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int a = super.scrollHorizontallyBy((int)(speedRatio*dx), recycler, state);//屏蔽之后无滑动效果，证明滑动的效果就是由这个函数实现
        if(a == (int)(speedRatio*dx)){
            return dx;
        }
        return a;
    }

    public void setSpeedRatio(double speedRatio){
        this.speedRatio = speedRatio;
    }
}