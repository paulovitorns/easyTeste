package br.com.easyteste.datasource.services;

import br.com.easyteste.datasource.listeners.OnPlaceResponseFinishedListener;
import br.com.easyteste.model.GPlacesAutoComplete;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface GPlaceService {
    void resquestPlace(OnPlaceResponseFinishedListener listener, GPlacesAutoComplete autoComplete);
}
