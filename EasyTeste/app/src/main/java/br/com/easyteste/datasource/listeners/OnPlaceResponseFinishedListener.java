package br.com.easyteste.datasource.listeners;

import br.com.easyteste.model.GPlace;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface OnPlaceResponseFinishedListener extends BaseListener {
    void onSuccess(GPlace place);
}
