package com.example.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsapp.R;
import com.example.newsapp.activity.DetailNewsActivity;
import com.example.newsapp.model.Articles;
import com.example.newsapp.model.Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Articles> listArticle;
    private List<Articles> listArticleAll;

    public ArticlesListAdapter(Context context, List<Articles> listArticle) {
        this.context = context;
        this.listArticle = listArticle;
    }

    public void setArticleList(List<Articles> listArticle) {
        this.listArticle = listArticle;
        this.listArticleAll = new ArrayList<>(listArticle);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticlesListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_head_lines_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesListAdapter.MyViewHolder holder, int position) {
        Articles item = listArticle.get(position);

        if (item.getTitle() != null) {
            holder.tvTitle.setText(item.getTitle().toString());
        }
        if (item.getDescription() != null) {
            holder.tvDesc.setText(item.getDescription().toString());
        }
        if (item.getSource().getName() != null) {
            holder.tvSource.setText(item.getSource().getName().toString());
        }
        Glide.with(context)
                .load(item.getUrlToImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

        holder.article.setOnClickListener(v -> {
            Intent i = new Intent(context, DetailNewsActivity.class);
            i.putExtra("url", item.getUrl());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        if (this.listArticle != null) {
            return this.listArticle.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Articles> filteredList = new ArrayList<>();

            if (constraint.toString().isEmpty()) {
                filteredList.addAll(listArticleAll);
            } else {
                for (Articles article : listArticleAll) {
                    if (article.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(article);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listArticle.clear();
            listArticle.addAll((Collection<? extends Articles>) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvDesc;
        TextView tvSource;
        ConstraintLayout article;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvSource = itemView.findViewById(R.id.tvSource);
            article = itemView.findViewById(R.id.article);
        }
    }
}
