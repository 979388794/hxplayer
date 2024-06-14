package com.example.hxplay.utils;

import java.io.File;
import java.util.Map;
import java.util.Set;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: henry.xue
 * @date: 2024-06-14
 */
public class okHttpUtil {

    private static okHttpUtil okHttpUtil = null;
    private static OkHttpClient okHttpClient = null;

    public static okHttpUtil getInstance() {
        if (okHttpUtil == null) {
            synchronized (okHttpUtil.class) {
                if (okHttpUtil == null) {
                    okHttpUtil = new okHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    /**
     * 单例模式
     * 封装okhttp
     * synchronized同步方法
     *
     * @return
     */
    public static synchronized OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            SSLHelper.configureSSL(okBuilder);
            okHttpClient = okBuilder.build();
//            okHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .build();
        }
        return okHttpClient;
    }

    /**
     * GET请求
     */
    public void doGet(String url, Callback callback) {
        Request reQuest = new Request.Builder()
                .get()
                .url(url)
                .build();

        getOkHttpClient().newCall(reQuest).enqueue(callback);
    }

    /**
     * GET下载
     */
    public void doGetDownLoad(String url, Callback callback) {
        Request reQuest = new Request.Builder()
                .get()
                .url(url)
                .build();

        getOkHttpClient().newCall(reQuest).enqueue(callback);
    }

    /**
     * POST请求
     */
    public void doPost(String url, Map<String, String> params, Callback callback) {
        // 请求体
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            String value = params.get(key);
            builder.add(key, value);
        }
        FormBody formBody = builder.build();

        Request reQuest = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();

        getOkHttpClient().newCall(reQuest).enqueue(callback);
    }

    /**
     * POST请求json
     */
    public void doPostJson(String url, String json, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);

        Request reQuest = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        getOkHttpClient().newCall(reQuest).enqueue(callback);
    }

    /**
     * POST上传文件
     */
    public void doPostUpload(String url, String path, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(path));

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "file.jpg", requestBody)
                .build();

        Request reQuest = new Request.Builder()
                .post(multipartBody)
                .url(url)
                .build();

        getOkHttpClient().newCall(reQuest).enqueue(callback);
    }

}