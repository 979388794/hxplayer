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

import java.util.List;

public class DramaViewAdapter {

    private int mNumber;
    private SubViewAdapter mSubAdapter;
    private GroupViewAdapter mGroupAdapter;

    public DramaViewAdapter(int number) {
        mNumber = number;
        mSubAdapter = new SubViewAdapter(number);
        mGroupAdapter = new GroupViewAdapter(number);
    }

    public SubViewAdapter getSubViewAdapter() {
        return mSubAdapter;
    }

    public GroupViewAdapter getGroupViewAdapter() {
        return mGroupAdapter;
    }

    public void setSelectedPositions(List<Integer> positions) {
        mSubAdapter.setSelectedPositions(positions);
    }

    public int getSubNumber() {
        return mNumber;
    }

    public int getGroupNumber() {
        return mNumber;
    }


    public int getSubPosition(int groupPosition) {
        return groupPosition * 10;
    }


    public int getGroupPosition(int episodesPosition) {
        return episodesPosition / 10;
    }
}
