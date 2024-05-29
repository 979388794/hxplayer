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
package com.example.hxplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hxplay.R;
import com.example.hxplay.adapter.DramaViewAdapter;
import com.example.hxplay.adapter.GroupViewAdapter;
import com.example.hxplay.adapter.SubViewAdapter;


public class DramaView extends RelativeLayout {

    public static final String TAG = DramaView.class.getSimpleName();

    private Context mContext;
    private RecyclerView subsetview;
    private RecyclerView mParentView;
    private LinearLayoutManager subsetmanager;
    private LinearLayoutManager parentsetmanager;

    private DramaViewAdapter mEpisodeListAdapter;
    private SubViewAdapter msubAdapter;
    private GroupViewAdapter mGroupAdapter;

    public DramaView(Context context) {
        this(context, null);
    }

    public DramaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DramaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(R.layout.dramaview_layout, this, true);
        subsetview = (RecyclerView) findViewById(R.id.subset);
        mParentView = (RecyclerView) findViewById(R.id.parentset);
        subsetmanager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        parentsetmanager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        subsetview.setLayoutManager(subsetmanager);
        mParentView.setLayoutManager(parentsetmanager);
    }


    public void setAdapter(final DramaViewAdapter adapter) {
        mEpisodeListAdapter = adapter;
        msubAdapter = adapter.getSubViewAdapter();
        mGroupAdapter = adapter.getGroupViewAdapter();
        subsetview.setAdapter(msubAdapter);
        mParentView.setAdapter(mGroupAdapter);

        mGroupAdapter.setOnItemClickListener(new GroupViewAdapter.OnItemClickListener() {
            @Override
            public void onGroupItemClick(View view, int position) {
                Log.d(TAG, "  " + position);
                Log.d(TAG, "  " + adapter.getSubPosition(position));
                subsetmanager.scrollToPositionWithOffset(adapter.getSubPosition(position), 0);
            }
        });


        msubAdapter.setOnItemClickListener(new SubViewAdapter.OnItemClickListener() {
            @Override
            public void onSubItemClick(View view, int position) {
//                Log.d(TAG, "  " + position);
            }
        });

    }


}
