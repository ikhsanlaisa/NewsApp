package com.example.newsapp.model.response;

import com.example.newsapp.model.Articles;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListArticlesResponse extends BaseResponse{

    @SerializedName("articles")
    List<Articles> articles;

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
