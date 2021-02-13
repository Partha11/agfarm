package com.techmave.agfarm.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.ContactAdapter;
import com.techmave.agfarm.databinding.FragmentInfoBinding;
import com.techmave.agfarm.model.Contact;
import com.techmave.agfarm.model.Crop;
import com.techmave.agfarm.model.OpenWeather;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment getInstance() {

        return new InfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInfoBinding.inflate(inflater);
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
        ((AppCompatActivity) getActivity()).setTitle("Info");
    }

    private void initialize() {

        ContactAdapter adapter = new ContactAdapter(requireContext());

        binding.contactRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.contactRecycler.setAdapter(adapter);

        try {

            InputStream is = requireContext().getAssets().open("contacts.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            Type type = new TypeToken<ArrayList<Contact>>() { }.getType();
            List<Contact> contacts = new Gson().fromJson(json, type);

            adapter.setItems(contacts);
            adapter.notifyDataSetChanged();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}