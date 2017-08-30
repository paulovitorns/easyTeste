package br.com.easyteste.datasource.listeners;

import br.com.easyteste.model.EmptyStateTypes;
import br.com.easyteste.model.GPlaces;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface OnGPlacesRequestFinished extends BaseListener {
    void onSuccess(GPlaces places);

    void onPlacesErros(EmptyStateTypes error);
}
