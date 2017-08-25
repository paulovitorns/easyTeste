package br.com.easyteste.datasource.api;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import br.com.easyteste.BuildConfig;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class ApiClient extends OkHttpClient {

    private static ApiClient instance;
    private final static long TIMEOUT = 45;

    private ApiClient(){};

    public static ApiClient getInstance(){

        if (instance == null){
            instance = new ApiClient();

            if (BuildConfig.DEBUG) {
                instance.interceptors().add(getLogInterceptor());
            }
            instance.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
            instance.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
            instance.setWriteTimeout(TIMEOUT, TimeUnit.SECONDS);
        }
        return instance;
    }

    private static HttpLoggingInterceptor getLogInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override public void log(String message) {
                        Log.d("OkHttp", message);
                    }
                });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }


}
