package com.techmave.agfarm.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private SharedPreferences prefs;
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
}
