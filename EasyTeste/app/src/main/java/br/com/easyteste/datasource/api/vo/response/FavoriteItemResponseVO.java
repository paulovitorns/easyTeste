package br.com.easyteste.datasource.api.vo.response;

import com.google.gson.annotations.SerializedName;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class FavoriteItemResponseVO {
    @SerializedName("name")         public String name;
    @SerializedName("latitude")     public double latitude;
    @SerializedName("longitude")    public double longitude;
}
