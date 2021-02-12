package com.techmave.agfarm.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techmave.agfarm.R;
import com.techmave.agfarm.databinding.FragmentHomeBinding;
import com.techmave.agfarm.view.adapter.GridAdapter;
import com.techmave.agfarm.view.model.CardItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GridAdapter adapter;

    public static HomeFragment getInstance() {

        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {

        adapter = new GridAdapter();
        List<CardItem> items = new ArrayList<>();

        items.add(new CardItem("ফসলের বিবরণ", 1));
        items.add(new CardItem("কি ফসল চাষ করবেন", 1));
        items.add(new CardItem("সার ও বীজের হিসাব", 1));
        items.add(new CardItem("লাভ ক্ষতির হিসাব", 1));

        adapter.setItems(items);

        binding.homeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.homeRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.homeRecycler.setAdapter(adapter);
    }
}