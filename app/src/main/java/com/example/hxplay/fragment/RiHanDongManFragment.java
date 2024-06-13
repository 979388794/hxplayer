package com.example.hxplay.fragment;

import android.util.Log;

import com.example.hxplay.R;
import com.example.hxplay.utils.API;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: henry.xue
 * @date: 2024-05-16
 */
public class RiHanDongManFragment extends BaseFragment {

    String TAG = this.getClass().getSimpleName();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView------");
    }

    @Override
    public List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.dongman1);
        imageList.add(R.drawable.dongman2);
        imageList.add(R.drawable.dongman3);
        return imageList;
    }

    @Override
    public String getMovieUrl() {
        return API.RIHANDONGMAN;
    }




}
