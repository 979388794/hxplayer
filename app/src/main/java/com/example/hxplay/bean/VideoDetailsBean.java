package com.example.hxplay.bean;


import java.util.List;

public class VideoDetailsBean {


    private String msg;
    private int code;
    private int count;

    private Data data;

    public int getCode() {
        return code;
    }

    public int getCount() {
        return count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String videoId;
        private String title;
        private String descs;
        private String cover;
        private String director;
        private String actor;
        private String region;
        private String videoType;
        private String releaseTime;
        private String updateTime;
        private List<Chapter> chapterList;


        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<Chapter> getChapterList() {
            return chapterList;
        }

        public void setChapterList(List<Chapter> chapterList) {
            this.chapterList = chapterList;
        }


        public static class Chapter {
            private String title;
            private String chapterPath;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getChapterPath() {
                return chapterPath;
            }

            public void setChapterPath(String chapterPath) {
                this.chapterPath = chapterPath;
            }
        }
    }


}