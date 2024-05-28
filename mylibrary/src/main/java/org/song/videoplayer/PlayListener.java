package org.song.videoplayer;



public interface PlayListener {
    /**
     * 播放器的ui状态
     */
    void onStatus(int status);

    /**
     * 播放器的ui模式[全屏/普通/...
     */
    void onMode(int mode);

    /**
     * 播放事件
     */
    void onEvent(int what, Integer... extra);
}
