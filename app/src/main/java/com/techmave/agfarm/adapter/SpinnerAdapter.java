package com.techmave.agfarm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.techmave.agfarm.R;
import com.techmave.agfarm.model.Crop;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Crop> {

    private final int resource;
    private final List<Crop> objects;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Crop> objects) {

        super(context, resource, objects);

        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        }

        AppCompatTextView tView = convertView.findViewById(R.id.spinner_text);
        tView.setText(objects.get(position).getNameBangla());

        return convertView;
    }
}
