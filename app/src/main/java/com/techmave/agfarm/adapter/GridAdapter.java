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
import com.techmave.agfarm.listener.CardClickListener;
import com.techmave.agfarm.model.CardItem;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<CardItem> items;
    private final Context context;
    private final CardClickListener listener;

    public GridAdapter(Context context, CardClickListener listener) {

        this.context = context;
        this.listener = listener;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ModelCardBinding binding;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            binding = ModelCardBinding.bind(itemView);
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (listener != null) {

                listener.onCardClicked(getAdapterPosition());
            }
        }
    }
}
