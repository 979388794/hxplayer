package com.example.hxplay.utils;

import java.util.ArrayList;

/**
 * @author: henry.xue
 * @date: 2024-05-21
 */
public class MovieType {


    /**
     * 视频搜索
     * <p>
     * option   * 必填
     * string
     * 选择搜索项 标题：title ，导演 ：director，主演：actor，地区：region，上映：releaseTime，分类：videoType
     * <p>
     * key     * 必填
     * string
     * 搜索关键字
     * <p>
     * from     * 可选
     * int
     * 当前页数，留空默认1
     * <p>
     * size     * 可选
     * int
     * 一页显示的数量，留空默认10，最多30
     */
    String url = "https://api.pingcc.cn/video/search/{option}/{key}/{from}/{size}";


    /**
     * 视频章节
     * <p>
     * 参数名   videoId
     * 类型     string
     * 描述     通过视频搜索API获取到videoId
     * 必填     必填
     */
    String url2 = "https://api.pingcc.cn/videoChapter/search/{videoId}";


    private static MovieType instance;

    private ArrayList<String> movieType;

    public MovieType() {
        // 初始化 movieType 中的分类
        movieType.add("动漫电影");
        movieType.add("泰国");
        movieType.add("港澳剧");
        movieType.add("日本");
        movieType.add("韩国剧");
        movieType.add("海外动漫");
        movieType.add("中国大陆");
        movieType.add("香港剧");
        movieType.add("泰国剧");
        movieType.add("台湾剧");
        movieType.add("港澳");
        movieType.add("犯罪");
        movieType.add("悬疑");
        movieType.add("战争");
        movieType.add("选秀");
        movieType.add("日本剧");
        movieType.add("日韩综艺");
        movieType.add("韩剧");
        movieType.add("港台综艺");
        movieType.add("欧美综艺");
        movieType.add("海外剧");
        movieType.add("泰剧");
        movieType.add("科幻");
        movieType.add("日剧");
        movieType.add("喜剧片");
        movieType.add("战争片");
        movieType.add("爱情片");
        movieType.add("欧美剧");
        movieType.add("大陆综艺");
        movieType.add("欧美");
        movieType.add("伦理片");
        movieType.add("日韩动漫");
        movieType.add("动作片");
        movieType.add("动作");
        movieType.add("科幻片");
        movieType.add("爱情");
        movieType.add("喜剧");
        movieType.add("欧美动漫");
        movieType.add("剧情动画");
        movieType.add("真人秀");
        movieType.add("国产剧");
        movieType.add("动漫");
        movieType.add("国产");
        movieType.add("伦理");
        movieType.add("剧情片");
        movieType.add("国产动漫");
        movieType.add("奇幻");
        movieType.add("综艺");
        movieType.add("动画");
        movieType.add("纪录片");
        movieType.add("其它");
    }

    public static MovieType getInstance() {
        if (instance == null) {
            instance = new MovieType();
        }
        return instance;
    }

    public ArrayList<String> getmovieType() {
        return movieType;
    }

}
