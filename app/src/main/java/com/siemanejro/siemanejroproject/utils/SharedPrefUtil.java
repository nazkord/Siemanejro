package com.siemanejro.siemanejroproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.siemanejro.siemanejroproject.R;

import com.siemanejro.siemanejroproject.model.User;

public class SharedPrefUtil {

    private SharedPreferences sharedPreferences;

    private String KEY_ID;
    private String KEY_LOGIN;
    private String KEY_PASSWORD;
    private String KEY_TOKEN;

    public SharedPrefUtil(Context context) {

        KEY_ID = context.getString(R.string.shPref_id_key);
        KEY_LOGIN = context.getString(R.string.shPref_login_key);
        KEY_PASSWORD = context.getString(R.string.shPref_password_key);
        KEY_TOKEN = context.getString(R.string.shPref_token_key);


        String KEY_LOGIN = context.getString(R.string.login_preferences_key);
        this.sharedPreferences = context.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE);
    }

    public User getLoggerUser() {
        long id = sharedPreferences.getLong(KEY_ID, 0);
        String userName = sharedPreferences.getString(KEY_LOGIN, null);
        String userPassword = sharedPreferences.getString(KEY_PASSWORD, null);
        if(id == 0 && userName == null && userPassword == null) {
            return null;
        } else {
            return new User(id, userName, userPassword);
        }
    }

    public void setLoggerUser(User user) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(KEY_LOGIN, user.getName());
        prefEditor.putString(KEY_PASSWORD, user.getPassword());
        prefEditor.putLong(KEY_ID, user.getId());
        prefEditor.apply();
    }

    public void deleteLoggedUser() {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.remove(KEY_LOGIN);
        prefEditor.remove(KEY_PASSWORD);
        prefEditor.remove(KEY_ID);
        prefEditor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void setToken(String token) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(KEY_TOKEN, token);
        prefEditor.apply();
    }
}













