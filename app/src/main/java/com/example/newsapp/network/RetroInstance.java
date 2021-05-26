package com.example.newsapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    //My Api Key = "49404a6ee0cb4b708cebd98d8de1898d"
    public static String BASE_URL = "https://newsapi.org/v2/";

    private static Retrofit retrofit;

    public static Retrofit getRetroClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
