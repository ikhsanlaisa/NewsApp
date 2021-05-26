package com.example.newsapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.model.response.SourceResponse;
import com.example.newsapp.network.APIService;
import com.example.newsapp.network.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesNewsViewModel extends ViewModel {

    private MutableLiveData<SourceResponse> sourcesList;

    public SourcesNewsViewModel() {
        sourcesList = new MutableLiveData<>();
    }

    public MutableLiveData<SourceResponse> getSourcesListObserver() {
        return sourcesList;
    }

    public void sourcesApiCall(String category) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<SourceResponse> call = apiService.getSourcesList(category, "49404a6ee0cb4b708cebd98d8de1898d");
        call.enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                sourcesList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {
                sourcesList.postValue(null);
            }
        });
    }
}
