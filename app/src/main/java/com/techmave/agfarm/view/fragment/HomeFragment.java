package com.techmave.agfarm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.GridAdapter;
import com.techmave.agfarm.databinding.FragmentHomeBinding;
import com.techmave.agfarm.model.CardItem;

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

        items.add(new CardItem("ফসলের বিবরণ", R.drawable.ic_plant ));
        items.add(new CardItem("কি ফসল চাষ করবেন", R.drawable.ic_what));
        items.add(new CardItem("সার ও বীজের হিসাব", R.drawable.ic_calculator));
        items.add(new CardItem("লাভ ক্ষতির হিসাব", R.drawable.ic_profit));

        adapter.setItems(items);

        binding.homeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.homeRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.homeRecycler.setAdapter(adapter);
    }
}