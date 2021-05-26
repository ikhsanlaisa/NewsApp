package com.example.newsapp.network;

import com.example.newsapp.model.response.SourceResponse;
import com.example.newsapp.model.response.ListArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("everything")
    Call<ListArticlesResponse> getNewsList(@Query("sources") String sources,
                                           @Query("apiKey") String apiKey);

    @GET("sources")
    Call<SourceResponse> getSourcesList(@Query("category") String category,
                                    @Query("apiKey") String apiKey);
}
