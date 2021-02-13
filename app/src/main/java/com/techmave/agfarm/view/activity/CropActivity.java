package com.techmave.agfarm.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.SpinnerAdapter;
import com.techmave.agfarm.databinding.ActivityCropBinding;
import com.techmave.agfarm.model.Breed;
import com.techmave.agfarm.model.Crop;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CropActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityCropBinding binding;
    private List<Crop> crops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityCropBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

        setSupportActionBar(binding.toolbar);
        setTitle("Crops");

        fetchInformationFromJson();

        binding.cropSpinner.setOnItemSelectedListener(this);
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

    private void updateUi(Crop crop) {

        if (crop != null) {

            String text = crop.getSeedAmount() + " গ্রাম প্রতি শতাংশ";
            String cultivationText = "<strong>ফসল তোলার সময়:</strong> " + crop.getCultivation().getStart() + " - " + crop.getCultivation().getEnd();
            String seedlingText = "<strong>চারা রোপণের সময়:</strong> " + crop.getSeedlings().getStart() + " - " + crop.getSeedlings().getEnd() + "<br><br>";
            seedlingText += "<strong>ফসল তোলার নিয়ম:</strong> " + crop.getCultivation().getDetails() + "<br><br>";
            seedlingText += "<strong>প্রয়োজনীয় পরিবেশ:</strong> " + crop.getRequirements() + "<br><br>";
            seedlingText += "<strong>সেচ প্রক্রিয়া:</strong> " + (crop.getIrrigation().isEmpty() ? "" : crop.getIrrigation().get(0).getDetails()) + "<br><br>";
            seedlingText += "<strong>সার দেয়ার নিয়ম:</strong> " + crop.getFertilizationProcess() + "<br><br>";
            seedlingText += "<strong>সার দেয়ার নিয়ম:</strong> " + crop.getFertilizationProcess() + "<br><br>";
            seedlingText += "<strong>বিভিন্ন জাতের বিবরণ:</strong> <br>";

            StringBuilder builder = new StringBuilder();

            for (Breed breed: crop.getBreeds()) {

                builder.append("<strong>");
                builder.append(breed.getName());
                builder.append(":</strong> ");
                builder.append(breed.getDetails());
                builder.append("<br><br>");
            }

            seedlingText += builder.toString();

            binding.cropName.setText(crop.getNameBangla());
            binding.cropUseText.setText(crop.getUseCase());
            binding.seedAmountText.setText(text);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                binding.cultivationTime.setText(Html.fromHtml(cultivationText, Html.FROM_HTML_MODE_COMPACT));
                binding.seedlingTime.setText(Html.fromHtml(seedlingText, Html.FROM_HTML_MODE_COMPACT));

            } else {

                binding.cultivationTime.setText(Html.fromHtml(cultivationText));
                binding.seedlingTime.setText(Html.fromHtml(seedlingText));
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        binding.selectCropMsg.setVisibility(View.GONE);
        updateUi(crops.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        binding.selectCropMsg.setVisibility(View.VISIBLE);
    }
}