package br.com.easyteste.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orm.SugarApp;

import br.com.easyteste.Easy;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NetworkUtils {

    public static boolean hasActiveInternetConnection() {
        return isNetworkAvailable();
    }

    private static boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) Easy.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static boolean isLocationAvailable(){
        LocationManager lm = (LocationManager) Easy.getContext().getSystemService(Context.LOCATION_SERVICE);

        try {
            return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {
            return false;
        }
    }

}
