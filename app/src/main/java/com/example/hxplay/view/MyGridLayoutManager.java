package com.example.hxplay.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: henry.xue
 * @date: 2024-05-08
 */
public class MyGridLayoutManager extends GridLayoutManager {
    private double speedRatio;

    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    public void setSpeedRatio(double speedRatio) {
        this.speedRatio = speedRatio;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int a = super.scrollHorizontallyBy((int) (speedRatio * dx), recycler, state);//屏蔽之后无滑动效果，证明滑动的效果就是由这个函数实现
        if (a == (int) (speedRatio * dx)) {
            return dx;
        }
        return a;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int a = super.scrollVerticallyBy(dy, recycler, state);
        if (a == (int) (speedRatio * dy)) {
            return dy;
        }
        return a;
    }

}