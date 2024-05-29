package com.example.hxplay.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.hxplay.R;
import com.example.hxplay.adapter.DramaViewAdapter;
import com.example.hxplay.adapter.SubViewAdapter;
import com.example.hxplay.bean.VideoDetailsBean;
import com.example.hxplay.utils.API;
import com.example.hxplay.utils.SSLHelper;
import com.example.hxplay.video.danmaku.DanmakuConfig;
import com.example.hxplay.video.danmaku.DanmakuControl;
import com.example.hxplay.video.danmaku.QSDanmakuParser;
import com.example.hxplay.video.io.FileUtil;
import com.example.hxplay.view.DramaView;
import com.google.gson.Gson;

import org.song.videoplayer.DemoQSVideoView;
import org.song.videoplayer.IVideoPlayer;
import org.song.videoplayer.PlayListener;
import org.song.videoplayer.QSVideo;
import org.song.videoplayer.cache.Proxy;
import org.song.videoplayer.floatwindow.FloatParams;
import org.song.videoplayer.media.AndroidMedia;
import org.song.videoplayer.media.BaseMedia;

import java.io.File;
import java.io.IOException;
import java.util.List;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlayVideoActivity extends AppCompatActivity {
    String TAG = this.getClass().getSimpleName();
    DemoQSVideoView demoVideoView;
    DanmakuControl danmakuControl;
    DramaView mMyview;
    String[] arr = {"适应", "填充", "原尺寸", "拉伸", "16:9", "4:3"};
    String m3u8;
    String url;
    Class<? extends BaseMedia> decodeMedia;
    String videoId;
    public OkHttpClient client;
    List<VideoDetailsBean.Data.Chapter> chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        SSLHelper.configureSSL(okBuilder);
        client = okBuilder.build();
        videoId = getIntent().getStringExtra("videoId");

        Log.d(TAG, "videoId-----=" + videoId.toString());
        getdata(videoId);
        mMyview = (DramaView) findViewById(R.id.Drama_series);

        demoVideoView = findViewById(R.id.qs);
        demoVideoView.getCoverImageView().setImageResource(R.mipmap.cover);
        demoVideoView.setLayoutParams(new LinearLayout.LayoutParams(-1, getResources().getDisplayMetrics().widthPixels * 9 / 16));
        //进入全屏的模式 0横屏 1竖屏 2传感器自动横竖屏 3根据视频比例自动确定横竖屏      -1什么都不做
        demoVideoView.enterFullMode = 3;
        //是否非全屏下也可以手势调节进度
        demoVideoView.isWindowGesture = true;
        demoVideoView.setPlayListener(new PlayListener() {
            int mode;

            @Override
            public void onStatus(int status) {//播放状态
                if (status == IVideoPlayer.STATE_AUTO_COMPLETE)
                    demoVideoView.quitWindowFullscreen();//播放完成退出全屏
            }

            @Override//全屏/普通/浮窗
            public void onMode(int mode) {
                this.mode = mode;
            }

            @Override
            public void onEvent(int what, Integer... extra) {
                //系统浮窗点击退出退出activity
                if (what == DemoQSVideoView.EVENT_CLICK_VIEW && extra[0] == org.song.videoplayer.R.id.help_float_close) {
                    if (demoVideoView.isSystemFloatMode()) finish();
                }
            }

        });
        //集成弹幕 播放前调用
        danmakuControl = DanmakuControl.bind(
                demoVideoView,
                new QSDanmakuParser(FileUtil.readAssets("danmu.json", this)),
                DanmakuConfig.getDefaultContext()
        );
        danmakuControl.hide();
    }


    private void play(String url, Class<? extends BaseMedia> decodeMedia) {
        demoVideoView.release();
        demoVideoView.setDecodeMedia(decodeMedia);

        demoVideoView.setUp(
                QSVideo.Build(url).title("这是标清标题").definition("").resolution("标清 720P").build()
//                QSVideo.Build(url).title("这是标清标题").definition("标清").resolution("标清 720P").build(),
//                QSVideo.Build(url).title("这是高清标题").definition("高清").resolution("高清 1080P").build(),
//                QSVideo.Build(url).title("这是2K标题").definition("2K").resolution("超高清 2K").build(),
//                QSVideo.Build(url).title("这是4K标题").definition("4K").resolution("极致 4K").build()
        );
//        demoVideoView.setUp(url, "这是一个标题");
        //demoVideoView.seekTo(12000);
//        demoVideoView.openCache();//缓存配置见最后,缓存框架太久没更新,不是很稳定
        demoVideoView.play();
        this.url = url;
        this.decodeMedia = decodeMedia;

    }


    public void m3u8直播(View v) {
        play(m3u8, decodeMedia);
    }


    int mode;

    public void 缩放模式(View v) {
        demoVideoView.setAspectRatio(++mode > 5 ? mode = 0 : mode);
        ((Button) v).setText(arr[mode]);
    }


    //android:launchMode="singleTask" 根据自己需求设置
    public void 系统浮窗(View v) {
        if (demoVideoView.getCurrentMode() == IVideoPlayer.MODE_WINDOW_FLOAT_ACT)
            return;
        enterFloat(true);
        ((Button) v).setText(demoVideoView.isWindowFloatMode() ? "退出浮窗" : "系统浮窗");
    }

    public void 界面内浮窗(View v) {
        if (demoVideoView.getCurrentMode() == IVideoPlayer.MODE_WINDOW_FLOAT_SYS)
            return;
        enterFloat(false);
        ((Button) v).setText(demoVideoView.isWindowFloatMode() ? "退出浮窗" : "界面内浮窗");
    }


    public void 弹幕(View v) {
        if (danmakuControl.isShow())
            danmakuControl.hide();
        else
            danmakuControl.show();
    }

    public void 发弹幕(View v) {
        addDanmaku(false);
        ((ImageView) findViewById(R.id.image)).setImageBitmap(demoVideoView.getCurrentFrame());
    }


    public void 销毁(View v) {
        demoVideoView.release();
    }

    //返回键
    @Override
    public void onBackPressed() {
        //全屏和系统浮窗不finish
        if (demoVideoView.onBackPressed()) {
            if (demoVideoView.isSystemFloatMode())
                //系统浮窗返回上一界面 android:launchMode="singleTask"
                moveTaskToBack(true);
            return;
        }
        super.onBackPressed();
    }

    //进入浮窗
    private void enterFloat(boolean isSystemFloat) {
        FloatParams floatParams = demoVideoView.getFloatParams();
        if (floatParams == null) {
            floatParams = new FloatParams();
            floatParams.x = 0;
            floatParams.y = 0;
            floatParams.w = getResources().getDisplayMetrics().widthPixels * 3 / 4;
            floatParams.h = floatParams.w * 9 / 16;
            floatParams.round = 30;
            floatParams.fade = 0.8f;
            floatParams.canMove = true;
            floatParams.canCross = false;
        }
        floatParams.systemFloat = isSystemFloat;

        if (demoVideoView.isWindowFloatMode())
            demoVideoView.quitWindowFloat();
        else {
            if (!demoVideoView.enterWindowFloat(floatParams)) {
                Toast.makeText(this, "没有浮窗权限", Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 0);
                }
            }
        }
        if (demoVideoView.isSystemFloatMode())
            onBackPressed();
    }

    private void addDanmaku(boolean islive) {
        BaseDanmaku danmaku = DanmakuConfig.getDefaultContext().mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || danmakuControl == null) {
            return;
        }
        // for(int i=0;i<100;i++){
        // }
        danmaku.text = "QSVideoPlayer-" + System.nanoTime();
        danmaku.padding = 5;
        danmaku.priority = 10;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = islive;
        danmaku.setTime(demoVideoView.getPosition() + 1200);
        danmaku.textSize = 40;
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = Color.WHITE;
        // danmaku.underlineColor = Color.GREEN;
        danmaku.borderColor = Color.GREEN;
        danmakuControl.add(danmaku);

    }


    //=======================以下生命周期控制=======================

    private boolean playFlag;//记录退出时播放状态 回来的时候继续播放
    private int position;//记录销毁时的进度 回来继续该进度播放
    private Handler handler = new Handler();

    @Override
    public void onResume() {
        super.onResume();
        if (playFlag)
            demoVideoView.play();
        handler.removeCallbacks(runnable);
        if (position > 0) {
            demoVideoView.seekTo(position);
            position = 0;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (demoVideoView.isSystemFloatMode())
            return;
        //暂停
        playFlag = demoVideoView.isPlaying();
        demoVideoView.pause();
    }


    @Override
    public void onStop() {
        super.onStop();
        if (demoVideoView.isSystemFloatMode())
            return;
        //进入后台不马上销毁,延时15秒
        handler.postDelayed(runnable, 1000 * 15);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();//销毁
        if (demoVideoView.isSystemFloatMode())
            demoVideoView.quitWindowFloat();
        demoVideoView.release();
        handler.removeCallbacks(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (demoVideoView.getCurrentState() != IVideoPlayer.STATE_AUTO_COMPLETE)
                position = demoVideoView.getPosition();
            demoVideoView.release();
        }
    };

    //缓存配置
    private void cacheConfig() {
        Proxy.setConfig(new HttpProxyCacheServer
                        .Builder(this)
                        .cacheDirectory(new File(Environment.getExternalStorageDirectory(), "qsvideo"))
                        //.fileNameGenerator() 存储文件名规则
                        .maxCacheSize(512 * 1024 * 1024)//缓存文件大小
                //.maxCacheFilesCount(100)//缓存文件数目 二选一
        );
    }

    public void getdata(String videoId) {
        Log.d(TAG, "getdata-----");
        Request request = new Request.Builder()
                .url(API.VideoDetails + videoId)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    VideoDetailsBean movieBean = gson.fromJson(result, VideoDetailsBean.class);
//                    Log.d(TAG, "movieBean =" +movieBean.getChapterList().size());
                    // 更新数据源
                    chapters = movieBean.getData().getChapterList();
                    // 延迟100ms以确保数据已经赋值
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (chapters != null && chapters.size() >= 1) {
                                Log.d(TAG, "chapters =" + chapters);
                                m3u8 = chapters.get(0).getChapterPath();
                                DramaViewAdapter adapter = new DramaViewAdapter(chapters.size());
                                mMyview.setAdapter(adapter);
                                adapter.getSubViewAdapter().setOnItemClickListener(new SubViewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onSubItemClick(View view, int position) {
                                        Log.d(TAG, "点击位置为" + position);
                                        if (chapters.size() >= 1) {
                                            play(chapters.get(position).getChapterPath(), AndroidMedia.class);
                                        } else {
                                            Log.d(TAG, "chapters只有一个" + position);
                                        }
                                    }
                                });
                            }
                        }
                    }, 300);

                }
            }
        });

    }


}
