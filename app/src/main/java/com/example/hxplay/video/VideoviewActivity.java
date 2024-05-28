package com.example.hxplay.video;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hxplay.R;

/**
 * @author: henry.xue
 * @date: 2024-05-23
 */
public class VideoviewActivity extends AppCompatActivity {

    private VideoView mVideo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);

        mVideo = findViewById(R.id.video);
        //网络加载 - 视屏地址
        String videoPath = "https://vfx.mtime.cn/Video/2019/07/12/mp4/190712140656051701.mp4";
        //视屏控制器
        MediaController mediaController = new MediaController(VideoviewActivity.this);
        //VideoView绑定控制器
        mVideo.setMediaController(mediaController);
        //设置视频地址
        mVideo.setVideoPath(videoPath);
        //获取焦点
        mVideo.requestFocus();
        //播放视频
        mVideo.start();
    }

}
