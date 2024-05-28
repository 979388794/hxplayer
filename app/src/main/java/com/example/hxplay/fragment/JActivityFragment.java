package com.example.hxplay.fragment;

import android.util.Log;
import android.view.View;

import com.example.hxplay.R;


public class JActivityFragment extends BaseFragment {
    private static final String TAG = JActivityFragment.class.getSimpleName();
    @Override
    public View initView() {
        Log.i(TAG,"建党活动");
        View view = View.inflate(getActivity(),R.layout.jd_activity,null);
        return view;
    }


}
