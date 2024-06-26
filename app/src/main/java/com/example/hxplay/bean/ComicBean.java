package com.example.hxplay.bean;

import java.util.List;

public class ComicBean {
    private String msg;
    private int code;
    private int count;
    private List<Comic> data;

    public static class Comic {
        private String comicId;
        private String title;
        private String author;
        private String comicType;
        private String descs;
        private String cover;
        private String updateTime;

        // Getters and Setters for all fields

        public String getComicId() {
            return comicId;
        }

        public void setComicId(String comicId) {
            this.comicId = comicId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getComicType() {
            return comicType;
        }

        public void setComicType(String comicType) {
            this.comicType = comicType;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    // Getters and Setters for all fields

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comic> getData() {
        return data;
    }

    public void setData(List<Comic> data) {
        this.data = data;
    }
}