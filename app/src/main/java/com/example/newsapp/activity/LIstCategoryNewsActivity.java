package com.example.newsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.newsapp.R;
import com.example.newsapp.adapter.CategoryNewsListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIstCategoryNewsActivity extends AppCompatActivity {

    private String[] category ={"business", "sports", "general", "technology", "entertainment", "health", "science"};
    private List<String> categoryList = new ArrayList<>();
    private CategoryNewsListAdapter adapter;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_ist_category_news);
        final Activity activity = this;
        activity.setTitle("News Category");

        categoryList.addAll(Arrays.asList(category));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CategoryNewsListAdapter(this, categoryList);
        recyclerView.setAdapter(adapter);
        adapter.setCategoryList(categoryList);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            }
        });
    }
}