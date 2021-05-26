package com.example.newsapp.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapter.ArticlesListAdapter;
import com.example.newsapp.model.Articles;
import com.example.newsapp.model.response.ListArticlesResponse;
import com.example.newsapp.viewmodel.ListArticlesViewModel;

import java.util.List;

public class ListArticlesActivity extends AppCompatActivity {

    private List<Articles> articlesList;
    private ArticlesListAdapter adapter;
    private ListArticlesViewModel viewModel;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_head_lines);

        recyclerView = findViewById(R.id.rvSources);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String sourceId = getIntent().getStringExtra("id");

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticlesListAdapter(this, articlesList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ListArticlesViewModel.class);
        viewModel.getArticleListObserver().observe(this, new Observer<ListArticlesResponse>() {
            @Override
            public void onChanged(ListArticlesResponse topHeadlinesResponse) {
                if (topHeadlinesResponse.getStatus().equalsIgnoreCase("ok")) {
                    articlesList = topHeadlinesResponse.getArticles();
                    adapter.setArticleList(articlesList);
                }
            }
        });

        viewModel.listArticlesApiCall(sourceId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}