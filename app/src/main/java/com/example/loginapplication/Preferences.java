package com.example.loginapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    // Keys
    static final String Key_Username = "user";
    static final String Key_Password = "pass";
    static final String Key_Address = "address";
    static final String Key_Phone = "phone";

    static final String Key_User_Login = "name";
    static final String Key_Status_Login = "status";


    // Access SharedPreferences
    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    // =========================
    // USERNAME
    // =========================

    public static void setUserName(Context context, String userName) {
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();

        editor.putString(Key_Username, userName);
        editor.apply();
    }

    public static String getUserName(Context context) {
        return getSharedPreference(context)
                .getString(Key_Username, "");
    }


    // =========================
    // PASSWORD
    // =========================

    public static void setPassword(Context context, String password) {
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();

        editor.putString(Key_Password, password);
        editor.apply();
    }

    public static String getPassword(Context context) {
        return getSharedPreference(context)
                .getString(Key_Password, "");
    }


    // =========================
    // ADDRESS - PRACTICE 2
    // =========================

    public static void setAddress(Context context, String address) {
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();

        editor.putString(Key_Address, address);
        editor.apply();
    }

    public static String getAddress(Context context) {
        return getSharedPreference(context)
                .getString(Key_Address, "");
    }


    // =========================
    // PHONE - PRACTICE 2
    // =========================

    public static void setPhone(Context context, String phone) {
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();

        editor.putString(Key_Phone, phone);
        editor.apply();
    }

    public static String getPhone(Context context) {
        return getSharedPreference(context)
                .getString(Key_Phone, "");
    }


    // =========================
    // LOGGED IN USER
    // =========================

    public static void setUserLogin(Context context, String userLogin) {
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();

        editor.putString(Key_User_Login, userLogin);
        editor.apply();
    }

    public static String getUserLogin(Context context) {
        return getSharedPreference(context)
                .getString(Key_User_Login, "");
    }


    // =========================
    // LOGIN STATUS
    // =========================

    public static void setStatusLogin(Context context, boolean status) {
        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();

        editor.putBoolean(Key_Status_Login, status);
        editor.apply();
    }

    public static boolean getStatusLogin(Context context) {
        return getSharedPreference(context)
                .getBoolean(Key_Status_Login, false);
    }


    // =========================
    // CLEAR USER / LOGOUT
    // =========================

    public static void clearLoggedUser(Context context) {

        SharedPreferences.Editor editor =
                getSharedPreference(context).edit();

        editor.remove(Key_User_Login);
        editor.remove(Key_Status_Login);

        editor.remove(Key_Username);
        editor.remove(Key_Password);

        editor.remove(Key_Address);
        editor.remove(Key_Phone);

        editor.apply();
    }
}