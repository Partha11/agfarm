package com.techmave.agfarm.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.SpinnerAdapter;
import com.techmave.agfarm.databinding.ActivityCalculateBinding;
import com.techmave.agfarm.model.Breed;
import com.techmave.agfarm.model.Crop;
import com.techmave.agfarm.model.FertilizerAmount;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CalculateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityCalculateBinding binding;

    private List<Crop> crops;
    private Crop crop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityCalculateBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

        setSupportActionBar(binding.toolbar);
        setTitle("Calculation");

        fetchInformationFromJson();

        binding.fieldAmountInput.setText("0");
        binding.cropSpinner.setOnItemSelectedListener(this);

        binding.fieldAmountInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do Nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updateUi();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Do Nothing
            }
        });
    }

    private void fetchInformationFromJson() {

        try {

            InputStream is = getAssets().open("crops.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            Type type = new TypeToken<ArrayList<Crop>>() { }.getType();
            crops = new Gson().fromJson(json, type);

            SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_dropdown, crops);
            binding.cropSpinner.setAdapter(adapter);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void updateUi() {

        if (crop != null) {

            String seedAmount = crop.getSeedAmount();
            String text = binding.fieldAmountInput.getText().toString().trim();

            if (!text.isEmpty()) {

                double fieldAmount = Double.parseDouble(binding.fieldAmountInput.getText().toString().trim());
                StringBuilder sBuilder = new StringBuilder();
                StringBuilder fBuilder = new StringBuilder();
                String[] arr = seedAmount.split("-");
                String fertilizer = "";

                for (int i = 0; i < arr.length; i++) {

                    int value = Integer.parseInt(arr[i].trim());
                    sBuilder.append((value * fieldAmount) / 1000);

                    if (i != arr.length - 1) {

                        sBuilder.append("-");
                    }
                }

                for (FertilizerAmount f: crop.getFertilizerAmount()) {

                    Log.d("Count", fertilizer);

                    fertilizer += f.getName();
                    fertilizer += " ------ ";
                    fertilizer += ((f.getAmount() * fieldAmount) / 1000);
                    fertilizer += "\n";
                }

                binding.seedAmountInput.setText(sBuilder.toString());
                binding.fertilizerAmountInput.setText(fertilizer);

            } else {

                binding.seedAmountInput.setText("0");
                binding.fertilizerAmountInput.setText("");
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        this.crop = crops.get(position);
        updateUi();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //Do Nothing
    }
}