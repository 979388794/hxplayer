package com.example.hxplay.bean;


import java.util.List;

/**
 * @author: henry.xue
 * @date: 2024-05-08
 */
public class FictionBean {
    private String msg;
    private int code;
    private int count;
    private List<Fiction> data;

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

    public List<Fiction> getData() {
        return data;
    }

    public void setData(List<Fiction> data) {
        this.data = data;
    }


    public class Fiction {
        private String fictionId;
        private String title;
        private String author;
        private String fictionType;
        private String descs;
        private String cover;
        private String updateTime;

        public String getFictionId() {
            return fictionId;
        }

        public void setFictionId(String fictionId) {
            this.fictionId = fictionId;
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

        public String getFictionType() {
            return fictionType;
        }

        public void setFictionType(String fictionType) {
            this.fictionType = fictionType;
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
}