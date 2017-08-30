package br.com.easyteste.datasource.api.vo.response;

import com.google.gson.annotations.SerializedName;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class GPlacedetailResponseVO {
    @SerializedName("formatted_address")    public String                   formatted_address;
    @SerializedName("geometry")             public GPlaceLocationResponseVO geometry;
}
