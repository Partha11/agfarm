package com.techmave.agfarm.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private final SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SharedPrefs(Context context) {

        prefs = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {

        return prefs.getBoolean(Constants.PREF_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean value) {

        editor = prefs.edit();

        editor.putBoolean(Constants.PREF_IS_LOGGED_IN, value);
        editor.apply();
    }

    public long getSeedingTime() {

        return prefs.getLong(Constants.PREF_SEEDING_TIME, 0L);
    }

    public void setSeedingTime(Long millis) {

        editor = prefs.edit();

        editor.putLong(Constants.PREF_SEEDING_TIME, millis);
        editor.apply();
    }

    public void setSelectedCrop(String data) {

        editor = prefs.edit();

        editor.putString(Constants.PREF_CROP_DATA, data);
        editor.apply();
    }

    public String getSelectedCrop() {

        return prefs.getString(Constants.PREF_CROP_DATA, "");
    }
}
