package com.jiudian.manage.model;

public class News {
    public String news_id;
    public String news_title;
    public String news_anthor;
    public String news_date;
    public String news_class;
    public String news_photo;

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_anthor() {
        return news_anthor;
    }

    public void setNews_anthor(String news_anthor) {
        this.news_anthor = news_anthor;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_class() {
        return news_class;
    }

    public void setNews_class(String news_class) {
        this.news_class = news_class;
    }

    public String getNews_photo() {
        return news_photo;
    }

    public void setNews_photo(String news_photo) {
        this.news_photo = news_photo;
    }

    @Override
    public String toString() {
        return "News{" +
                "news_id='" + news_id + '\'' +
                ", news_title='" + news_title + '\'' +
                ", news_anthor='" + news_anthor + '\'' +
                ", news_date='" + news_date + '\'' +
                ", news_class='" + news_class + '\'' +
                ", news_photo='" + news_photo + '\'' +
                '}';
    }
}
