package br.com.easyteste.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.orm.SugarApp;

import br.com.easyteste.Easy;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class SharedPreferencesUtils {

    private static final String KEY             = "Easy";
    private static final String SESSION_DATA    = "EasyPLaces";

    private static SharedPreferences getPreferences(){
        SharedPreferences sharedPreferences = Easy.getContext().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void saveAlreadyLoaded(){
        SharedPreferences preferences   = getPreferences();
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(SESSION_DATA, true).commit();
    }

    public static void unsetCheckFavoriteLoaded(){
        SharedPreferences sharedPreferences = getPreferences();
        SharedPreferences.Editor editor     = sharedPreferences.edit();

        editor.putBoolean(SESSION_DATA, false).commit();
    }

    public static boolean isAlreadyLoaded(){
        SharedPreferences sharedPreferences = getPreferences();
        return sharedPreferences.getBoolean(SESSION_DATA, false);
    }

}
