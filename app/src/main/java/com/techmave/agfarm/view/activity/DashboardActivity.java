package com.techmave.agfarm.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.techmave.agfarm.R;
import com.techmave.agfarm.databinding.ActivityDashboardBinding;
import com.techmave.agfarm.dialog.DatePickerDialog;
import com.techmave.agfarm.listener.ContactClickListener;
import com.techmave.agfarm.listener.FragmentInteractionListener;
import com.techmave.agfarm.model.Crop;
import com.techmave.agfarm.model.Irrigation;
import com.techmave.agfarm.utility.Constants;
import com.techmave.agfarm.utility.SharedPrefs;
import com.techmave.agfarm.view.fragment.HomeFragment;
import com.techmave.agfarm.view.fragment.InfoFragment;
import com.techmave.agfarm.view.fragment.ProfileFragment;
import com.techmave.agfarm.view.fragment.WeatherFragment;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener, FragmentInteractionListener, ContactClickListener {

    private ActivityDashboardBinding binding;

    private List<Fragment> fragments;
    private List<Crop> crops;

    private SharedPrefs prefs;
    private Crop crop = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

        setSupportActionBar(binding.toolbar);

        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation);
        navigationAdapter.setupWithBottomNavigation(binding.bottomNavigation);

        fragments = new ArrayList<>();
        prefs = new SharedPrefs(this);

        fragments.add(HomeFragment.getInstance());
        fragments.add(WeatherFragment.getInstance());
        fragments.add(ProfileFragment.getInstance());
        fragments.add(InfoFragment.getInstance());

        binding.bottomNavigation.setOnTabSelectedListener(this);
        binding.bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        switchFragment(0);

        fetchDataFromFile();

        if (!prefs.getSelectedCrop().isEmpty()) {

            crop = new Gson().fromJson(prefs.getSelectedCrop(), Crop.class);
        }
    }

    private Fragment getFragment(int position) {

        return fragments.get(position);
    }

    private void switchFragment(int position) {

        Fragment f = getFragment(position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, f);
        transaction.commit();
    }

    private void fetchDataFromFile() {

        try {

            InputStream is = getAssets().open("crops.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            Type type = new TypeToken<ArrayList<Crop>>() { }.getType();
            crops = new Gson().fromJson(json, type);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void showAlertDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Information")
                .setMessage(message)
                .setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {

        switchFragment(position);
        return true;
    }

    @Override
    public void onCardClicked(int fragmentType, int position) {

        if (fragmentType == Constants.FRAGMENT_HOME) {

            if (position == 0) {

                startActivity(new Intent(this, CropActivity.class));

            } else if (position == 2) {

                startActivity(new Intent(this, CalculateActivity.class));
            }

        } else {

            if (position == 0) {

                DatePickerDialog dialog = new DatePickerDialog();

                dialog.setCrops(crops);
                dialog.setListener((crop, millis) -> {

                    if (crop != null) {

                        prefs.setSeedingTime(millis);
                        prefs.setSelectedCrop(new Gson().toJson(crop));
                    }
                });

                dialog.show(getSupportFragmentManager(), "d");

            } else if (position == 1) {

                if (crop == null) {

                    Toast.makeText(this, "কোন ফসল সিলেক্ট করা হয় নি!", Toast.LENGTH_SHORT).show();

                } else {

                    long now = System.currentTimeMillis();
                    long created = prefs.getSeedingTime();
                    String message = "";

                    for (Irrigation i: crop.getIrrigation()) {

                        int time = i.getAfter();
                        int millis = time * 24 * 60 * 60 * 1000;

                        if (time == 0) {

                            message = "এই ফসলে সেচের প্রয়োজন নেই!";
                            break;

                        } else if (created + millis > now) {

                            long diff = (created + millis) - now;
                            int date = (int) (diff / (1000 * 60 * 60 * 24));

                            message = "আপনার জমিতে " + date + " দিন পর সেচ দিতে হবে!";
                            break;
                        }
                    }

                    showAlertDialog(message);
                }

            } else if (position == 2) {

                if (crop == null) {

                    Toast.makeText(this, "কোন ফসল সিলেক্ট করা হয় নি!", Toast.LENGTH_SHORT).show();

                } else {

                    long now = System.currentTimeMillis();
                    long created = prefs.getSeedingTime();
                    String message = "";

                    for (Integer i: crop.getFertilizer()) {

                        int time = i;
                        int millis = time * 24 * 60 * 60 * 1000;

                        if (time == 0) {

                            message = "এই ফসলে সেচের প্রয়োজন নেই!";
                            break;

                        } else if (created + millis > now) {

                            long diff = (created + millis) - now;
                            int date = (int) (diff / (1000 * 60 * 60 * 24));

                            message = "আপনার জমিতে " + date + " দিন পর সার দিতে হবে!";
                            break;
                        }
                    }

                    showAlertDialog(message);
                }
            }
        }
    }

    @Override
    public void onContactClicked(String number) {

        if (number != null) {

            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("number", number);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Number copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }
}