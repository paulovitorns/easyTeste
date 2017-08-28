package br.com.easyteste.datasource.services.impl;

import br.com.easyteste.datasource.listeners.BaseListener;
import br.com.easyteste.datasource.services.ApiErrorResponse;
import br.com.easyteste.model.ApiResponse;
import br.com.easyteste.util.NetworkUtils;
import retrofit.Response;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class ApiErrorResponseImpl implements ApiErrorResponse {

    @Override
    public void processErrorResponse(Response response, BaseListener listener) {

        ApiResponse apiResponse = null;

        listener.onError(apiResponse);
    }

    @Override
    public void processFailure(BaseListener listener) {
        if(!NetworkUtils.hasActiveInternetConnection()){
            listener.onConnectionFail(ApiResponse.getDefaultConnectionError());
        }
    }

}
