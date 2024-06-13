package com.example.hxplay.fragment;

import android.util.Log;

import com.example.hxplay.R;
import com.example.hxplay.utils.API;

import java.util.ArrayList;
import java.util.List;


public class GuoChanFragment extends BaseFragment {

    String TAG = this.getClass().getSimpleName();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView------");
    }
    @Override
    public List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.guochan1);
        imageList.add(R.drawable.guochan2);
        imageList.add(R.drawable.guochan3);
        return imageList;
    }



    @Override
    public String getMovieUrl() {
        return API.GUOCHAN;
    }

}