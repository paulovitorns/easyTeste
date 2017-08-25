package br.com.easyteste.datasource.listeners;

import br.com.easyteste.model.ApiResponse;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface BaseListener {

    void onError(ApiResponse error);

    void onConnectionFail(ApiResponse error);

    void onServerNotRespond(ApiResponse error);
}
