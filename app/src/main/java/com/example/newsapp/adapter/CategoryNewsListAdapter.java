package com.example.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.activity.SourceNewsActivity;

import java.util.List;

public class CategoryNewsListAdapter extends RecyclerView.Adapter<CategoryNewsListAdapter.CategoryListViewHolder> {

    private Context context;
    private List<String> catList;

    public CategoryNewsListAdapter(Context context, List<String> catList) {
        this.context = context;
        this.catList = catList;
    }

    public void setCategoryList(List<String> catList) {
        this.catList = catList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListViewHolder holder, int position) {
        String item = catList.get(position);

        String output = item.substring(0, 1).toUpperCase() + item.substring(1);
        holder.tvCategory.setText(output.toString());

        holder.clData.setOnClickListener(v -> {
            Intent i = new Intent(context, SourceNewsActivity.class);
            i.putExtra("category", item.toString());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        if (this.catList != null) {
            return this.catList.size();
        }
        return 0;
    }

    public class CategoryListViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        ConstraintLayout clData;

        public CategoryListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvCategory);
            clData = itemView.findViewById(R.id.clData);
        }
    }
}
