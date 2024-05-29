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

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hxplay.R;

import java.util.List;

public class SubViewAdapter extends RecyclerView.Adapter<SubViewAdapter.MyViewHolder> {

    OnItemClickListener mClickListener;
    private int mNumber;
    private List<Integer> mSelectedPositions;

    public SubViewAdapter(int number) {
        mNumber = number;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (position < mNumber) {
            holder.textView.setText(String.valueOf(position + 1));
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onSubItemClick(v, position);
                }
            });
            if (mSelectedPositions != null && mSelectedPositions.contains(position)) {
                holder.textView.setSelected(true);
            }
        }
    }


    @Override
    public int getItemCount() {
        return mNumber;
    }


    public List<Integer> getSelectedPositions() {
        return mSelectedPositions;
    }

    public void setSelectedPositions(List<Integer> mPositions) {
        this.mSelectedPositions = mPositions;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.item);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public interface OnItemClickListener {
        void onSubItemClick(View view, int position);
    }


}
