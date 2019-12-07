package com.siemanejro.siemanejroproject.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtil {

    public static boolean isNetworkConnectionAvailable(ConnectivityManager manager) {
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork == null ||
                !activeNetwork.isConnectedOrConnecting()) {
            return false;
        }

        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.toString().isEmpty();
        } catch (UnknownHostException e) {
            Log.e("Error while checking internet connection", e.getMessage());
            return false;
        }
    }
}
