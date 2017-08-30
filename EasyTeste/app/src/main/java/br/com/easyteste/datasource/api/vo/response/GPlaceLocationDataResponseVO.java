package br.com.easyteste.datasource.api.vo.response;

import com.google.gson.annotations.SerializedName;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class GPlaceLocationDataResponseVO {
    @SerializedName("lat")  public double lat;
    @SerializedName("lng")  public double lng;
}
