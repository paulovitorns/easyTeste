package br.com.easyteste.datasource.listeners;

import br.com.easyteste.model.Favorites;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface FavoritesResponseListener extends BaseListener{
    void onSuccess(Favorites favorites);
}
