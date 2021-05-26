package com.example.newsapp.model.response;

import com.example.newsapp.model.Source;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourceResponse extends BaseResponse {

    @SerializedName("sources")
    List<Source> sources;

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
