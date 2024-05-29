/*
 * Copyright (C) 2016-2017 hejunlin <hejunlin2013@gmail.com>
 * Github:https://github.com/hejunlin2013/EpisodeListView
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hxplay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hxplay.R;


public class GroupViewAdapter extends RecyclerView.Adapter<GroupViewAdapter.MyViewHolder> {

    private OnItemClickListener mItemClickListener;

    private int mNumber;

    private int mCurrentPosition;

    public GroupViewAdapter(int number) {
        mNumber = number;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parent_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int startNumber = position * 10 + 1;
        int endNumber = Math.min(position * 10 + 10, mNumber); //处理最后一页不足10个的情况
        holder.textView.setText(startNumber + "-" + endNumber);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onGroupItemClick(v, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mNumber % 10 == 0) {
            return mNumber / 10;
        }
        return mNumber / 10 + 1;
    }


    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int position) {
        mCurrentPosition = position;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.item);
        }
    }

    public interface OnItemClickListener {
        void onGroupItemClick(View view, int position);
    }

}
