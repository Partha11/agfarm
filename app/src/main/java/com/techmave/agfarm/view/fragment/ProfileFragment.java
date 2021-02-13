package com.techmave.agfarm.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.GridAdapter;
import com.techmave.agfarm.databinding.FragmentProfileBinding;
import com.techmave.agfarm.listener.FragmentInteractionListener;
import com.techmave.agfarm.listener.CardClickListener;
import com.techmave.agfarm.model.CardItem;
import com.techmave.agfarm.utility.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements CardClickListener {

    private FragmentProfileBinding binding;
    private GridAdapter adapter;

    private FragmentInteractionListener listener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment getInstance() {

        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater);
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

        ((AppCompatActivity) getActivity()).setTitle("Profile");
    }

    private void initialize() {

        adapter = new GridAdapter(requireContext(), this);
        List<CardItem> items = new ArrayList<>();

        items.add(new CardItem("বীজ বপনের তারিখ", R.drawable.ic_calendar));
        items.add(new CardItem("সেচ দেওয়ার সময়", R.drawable.ic_watering_plants));
        items.add(new CardItem("সার দেওয়ার সময়", R.drawable.ic_fertilizer));
        items.add(new CardItem("ফসল কাটার সময়", R.drawable.ic_harvest));

        adapter.setItems(items);

        binding.homeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.homeRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.homeRecycler.setAdapter(adapter);
    }

    @Override
    public void onCardClicked(int position) {

        if (listener != null) {

            listener.onCardClicked(Constants.FRAGMENT_PROFILE, position);
        }
    }
}