package com.techmave.agfarm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techmave.agfarm.R;
import com.techmave.agfarm.databinding.ModelContactBinding;
import com.techmave.agfarm.listener.ContactClickListener;
import com.techmave.agfarm.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> items;
    private ContactClickListener listener;

    public ContactAdapter(Context context) {

        if (context instanceof ContactClickListener) {

            this.listener = (ContactClickListener) context;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact item = items.get(position);

        if (item != null) {

            holder.binding.userName.setText(item.getName());
            holder.binding.userNumber.setText("Number: " + item.getNumber());
            holder.binding.userDesignation.setText(item.getDesignation());
            holder.binding.userLocation.setText("Location: " + item.getLocation());
        }
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void setItems(List<Contact> items) {

        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ModelContactBinding binding;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            binding = ModelContactBinding.bind(itemView);
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (listener != null) {

                listener.onContactClicked(items.get(getAdapterPosition()).getNumber());
            }
        }
    }
}
