package br.com.easyteste.datasource.services;

import br.com.easyteste.datasource.listeners.OnGPlacesRequestFinished;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface GPlacesService {
    void onRequestPlaces(OnGPlacesRequestFinished listener, String param);
}
