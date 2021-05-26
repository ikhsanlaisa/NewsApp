package com.example.newsapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.model.response.ListArticlesResponse;
import com.example.newsapp.network.APIService;
import com.example.newsapp.network.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListArticlesViewModel extends ViewModel {

    private MutableLiveData<ListArticlesResponse> articleList;

    public ListArticlesViewModel() {
        articleList = new MutableLiveData<>();
    }

    public MutableLiveData<ListArticlesResponse> getArticleListObserver() {
        return articleList;
    }

    public void listArticlesApiCall(String sourceId) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<ListArticlesResponse> call = apiService.getNewsList(sourceId, "49404a6ee0cb4b708cebd98d8de1898d");
        call.enqueue(new Callback<ListArticlesResponse>() {
            @Override
            public void onResponse(Call<ListArticlesResponse> call, Response<ListArticlesResponse> response) {
                articleList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ListArticlesResponse> call, Throwable t) {
                articleList.postValue(null);
            }
        });
    }
}
