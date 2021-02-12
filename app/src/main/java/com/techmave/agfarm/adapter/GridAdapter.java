package com.techmave.agfarm.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.techmave.agfarm.R;
import com.techmave.agfarm.databinding.ModelCardBinding;
import com.techmave.agfarm.model.CardItem;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<CardItem> items;
    private Context context;

    public GridAdapter(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.ViewHolder holder, int position) {

        CardItem item = items.get(position);

        if (item != null) {

            holder.binding.cardTitle.setText(item.getName());

            Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), item.getIcon(), null);
            holder.binding.cardThumb.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void setItems(List<CardItem> items) {

        this.items = items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ModelCardBinding binding;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            binding = ModelCardBinding.bind(itemView);
        }
    }
}
