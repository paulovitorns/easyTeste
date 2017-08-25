package br.com.easyteste.datasource.services;

import br.com.easyteste.datasource.api.vo.request.FavoritesRequestVO;
import br.com.easyteste.datasource.listeners.FavoritesResponseListener;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface FavoritesService {
    void requestFavorites(FavoritesRequestVO requestVO, FavoritesResponseListener listener);
}
