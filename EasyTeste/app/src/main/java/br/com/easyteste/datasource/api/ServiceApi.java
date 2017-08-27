package br.com.easyteste.datasource.api;

import br.com.easyteste.datasource.api.vo.response.FavoritesResponseVO;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface ServiceApi {

    @GET("raw.php?")
    Call<FavoritesResponseVO> getDefaultFavorites(@Query("i") String token);
}
