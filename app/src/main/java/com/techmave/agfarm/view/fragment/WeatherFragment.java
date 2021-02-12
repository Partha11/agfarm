package com.techmave.agfarm.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.WeatherAdapter;
import com.techmave.agfarm.databinding.FragmentWeatherBinding;
import com.techmave.agfarm.model.OpenWeather;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherAdapter adapter;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment getInstance() {

        return new WeatherFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWeatherBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {

        adapter = new WeatherAdapter();

        binding.weatherRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.weatherRecycler.setAdapter(adapter);

        try {

            InputStream is = getContext().getAssets().open("weather.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            OpenWeather data = new Gson().fromJson(json, OpenWeather.class);

            adapter.setItems(data.getDaily());
            adapter.notifyDataSetChanged();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}