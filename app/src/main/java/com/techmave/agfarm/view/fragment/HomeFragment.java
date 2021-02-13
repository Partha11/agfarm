package com.techmave.agfarm.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.GridAdapter;
import com.techmave.agfarm.databinding.FragmentHomeBinding;
import com.techmave.agfarm.listener.FragmentInteractionListener;
import com.techmave.agfarm.listener.CardClickListener;
import com.techmave.agfarm.model.CardItem;
import com.techmave.agfarm.utility.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CardClickListener {

    private FragmentHomeBinding binding;
    private GridAdapter adapter;

    private FragmentInteractionListener listener;

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

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        if (context instanceof FragmentInteractionListener) {

            listener = (FragmentInteractionListener) context;
        }

        ((AppCompatActivity) getActivity()).setTitle("Home");
    }

    private void initialize() {

        adapter = new GridAdapter(requireContext(), this);
        List<CardItem> items = new ArrayList<>();

        items.add(new CardItem("ফসলের বিবরণ", R.drawable.ic_plant));
        items.add(new CardItem("কি ফসল চাষ করবেন", R.drawable.ic_what));
        items.add(new CardItem("সার ও বীজের হিসাব", R.drawable.ic_calculator));
        items.add(new CardItem("লাভ ক্ষতির হিসাব", R.drawable.ic_profit));

        adapter.setItems(items);

        binding.homeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.homeRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.homeRecycler.setAdapter(adapter);
    }

    @Override
    public void onCardClicked(int position) {

        if (listener != null) {

            listener.onCardClicked(Constants.FRAGMENT_HOME, position);
        }
    }
}