package com.techmave.agfarm.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.techmave.agfarm.R;
import com.techmave.agfarm.databinding.ActivityDashboardBinding;
import com.techmave.agfarm.listener.FragmentInteractionListener;
import com.techmave.agfarm.utility.Constants;
import com.techmave.agfarm.view.fragment.HomeFragment;
import com.techmave.agfarm.view.fragment.ProfileFragment;
import com.techmave.agfarm.view.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener, FragmentInteractionListener {

    private ActivityDashboardBinding binding;

    private List<Fragment> fragments;

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

        fragments.add(HomeFragment.getInstance());
        fragments.add(WeatherFragment.getInstance());
        fragments.add(ProfileFragment.getInstance());
        fragments.add(HomeFragment.getInstance());

        binding.bottomNavigation.setOnTabSelectedListener(this);
        binding.bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        switchFragment(0);
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
        }
    }
}