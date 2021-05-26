package com.example.newsapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapter.SourceNewsListAdapter;
import com.example.newsapp.model.Source;
import com.example.newsapp.model.response.SourceResponse;
import com.example.newsapp.viewmodel.SourcesNewsViewModel;

import java.util.List;

public class SourceNewsActivity extends AppCompatActivity {

    private List<Source> sourceList;
    private SourceNewsListAdapter adapter;
    private SourcesNewsViewModel viewModel;
    private TextView tvCategory;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_news);
        final Activity activity = this;

        recyclerView = findViewById(R.id.rvSources);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvCategory = findViewById(R.id.tvCategory);
        String category = getIntent().getStringExtra("category");

        String output = category.substring(0, 1).toUpperCase() + category.substring(1);
        tvCategory.setText(output.toString());

        activity.setTitle(output.toString());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SourceNewsListAdapter(this, sourceList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(SourcesNewsViewModel.class);
        viewModel.getSourcesListObserver().observe(this, new Observer<SourceResponse>() {
            @Override
            public void onChanged(SourceResponse sourceResponse) {
                if (sourceResponse.getStatus().equalsIgnoreCase("ok")) {
                    sourceList = sourceResponse.getSources();
                    adapter.setSourceList(sourceList);
                }
            }
        });

        viewModel.sourcesApiCall(category);
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