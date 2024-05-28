package com.example.hxplay.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: henry.xue
 * @date: 2024-05-08
 */
public class MyRecyclerView extends RecyclerView {
    private double scale;               //抛掷速度的缩放因子

    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityY *= scale;
        return super.fling(velocityX, velocityY);
    }

    public void setflingScale(double scale) {
        this.scale = scale;
    }








}
