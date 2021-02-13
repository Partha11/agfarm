package com.techmave.agfarm.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.techmave.agfarm.databinding.ActivityLoginBinding;
import com.techmave.agfarm.utility.SharedPrefs;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

        SharedPrefs prefs = new SharedPrefs(this);

        if (prefs.isLoggedIn()) {

            switchActivity();
        }

        binding.buttonSignIn.setOnClickListener(v -> {

            prefs.setLoggedIn(true);
            switchActivity();
        });
    }

    private void switchActivity() {

        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);
        finishAffinity();
    }
}