package com.techmave.agfarm.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.techmave.agfarm.R;
import com.techmave.agfarm.databinding.ActivityDashboardBinding;
import com.techmave.agfarm.view.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements AHBottomNavigation.OnNavigationPositionListener {

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
        fragments.add(HomeFragment.getInstance());
        fragments.add(HomeFragment.getInstance());
        fragments.add(HomeFragment.getInstance());

        binding.bottomNavigation.setOnNavigationPositionListener(this);
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
    public void onPositionChange(int y) {

        switchFragment(y);
    }
}