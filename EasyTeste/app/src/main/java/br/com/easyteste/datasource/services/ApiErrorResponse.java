package br.com.easyteste.datasource.services;

import br.com.easyteste.datasource.listeners.BaseListener;
import retrofit.Response;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface ApiErrorResponse {

    void processErrorResponse(Response response, BaseListener listener);

    void processFailure(BaseListener listener);
}
