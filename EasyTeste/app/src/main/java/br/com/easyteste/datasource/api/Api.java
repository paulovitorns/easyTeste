package br.com.easyteste.datasource.api;

import com.squareup.okhttp.HttpUrl;

import retrofit.Retrofit;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Api {

    private static ServiceApi adapter;

    public static final String url = "http://pastebin.com";
    public static final HttpUrl API_URL = HttpUrl.parse(url);

    private Api(){}

    private static ServiceApi createRestAdapter(){

        System.setProperty("http.keepAlive", "false");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(ApiClient.getInstance())
                .build();

        return retrofit.create(ServiceApi.class);
    }

    public static ServiceApi getAdapter(){
        if (adapter == null)
            adapter = createRestAdapter();
        return adapter;
    }
}
