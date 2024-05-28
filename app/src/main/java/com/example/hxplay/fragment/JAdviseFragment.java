package com.example.hxplay.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hxplay.R;


public class JAdviseFragment extends BaseFragment implements View.OnClickListener{
    private ImageButton btn_back;
    private EditText content;
    private Button btn_send;
    private static final String TAG = JAdviseFragment.class.getSimpleName();

    @Override
    public View initView() {
        Log.i(TAG,"建言献策");
        View view = View.inflate(getActivity(), R.layout.jd_advice, null);
        content = view.findViewById(R.id.content);
        btn_back = view.findViewById(R.id.btn_back);
        btn_send = view.findViewById(R.id.btn_send);

        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:

                break;
            case R.id.btn_back:
                getActivity().finish();
                break;
        }
    }
}
