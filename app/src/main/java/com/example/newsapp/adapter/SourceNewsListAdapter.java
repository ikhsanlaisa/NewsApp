package com.example.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.activity.ListArticlesActivity;
import com.example.newsapp.model.Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SourceNewsListAdapter extends RecyclerView.Adapter<SourceNewsListAdapter.SourceNewsViewHolder> implements Filterable {

    private Context context;
    private List<Source> sourceList;
    private List<Source> sourceListAll;

    public SourceNewsListAdapter(Context context, List<Source> sourceList) {
        this.context = context;
        this.sourceList = sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
        this.sourceListAll = new ArrayList<>(sourceList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SourceNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sources_item, parent, false);
        return new SourceNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceNewsViewHolder holder, int position) {
        Source item = sourceList.get(position);

        if (item.getName() != null) {
            holder.tvSource.setText(item.getName().toString());
        }
        if (item.getDescription() != null) {
            holder.tvDesc.setText(item.getDescription().toString());
        }
        if (item.getUrl() != null) {
            holder.tvUrl.setText(item.getUrl());
        }

        holder.clData.setOnClickListener(v -> {
            Intent i = new Intent(context, ListArticlesActivity.class);
            i.putExtra("id", item.getId());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        if (this.sourceList != null) {
            return this.sourceList.size();
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
            List<Source> filteredList = new ArrayList<>();

            if (constraint.toString().isEmpty()) {
                filteredList.addAll(sourceListAll);
            } else {
                for (Source source : sourceListAll) {
                    if (source.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(source);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            sourceList.clear();
            sourceList.addAll((Collection<? extends Source>) results.values);
            notifyDataSetChanged();
        }
    };

    public class SourceNewsViewHolder extends RecyclerView.ViewHolder{
        TextView tvSource;
        TextView tvDesc;
        TextView tvUrl;
        ConstraintLayout clData;

        public SourceNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            clData = itemView.findViewById(R.id.clData);
        }
    }
}
