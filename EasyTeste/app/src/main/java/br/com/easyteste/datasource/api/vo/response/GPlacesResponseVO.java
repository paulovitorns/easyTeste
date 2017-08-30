package br.com.easyteste.datasource.api.vo.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class GPlacesResponseVO {
    @SerializedName("predictions")  public List<GPlacesAutoCompleteResponseVO> predictions;
}
