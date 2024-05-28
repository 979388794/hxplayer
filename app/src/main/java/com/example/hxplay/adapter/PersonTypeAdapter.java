package com.example.hxplay.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hxplay.R;

import java.util.List;

public class PersonTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PersonTypeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title, item)
                .setText(R.id.tv_content, item + "内容");
    }
}