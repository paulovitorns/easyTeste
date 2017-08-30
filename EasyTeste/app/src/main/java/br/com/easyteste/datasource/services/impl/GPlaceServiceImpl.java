package br.com.easyteste.datasource.services.impl;

import android.util.Log;

import br.com.easyteste.Easy;
import br.com.easyteste.R;
import br.com.easyteste.datasource.api.Api;
import br.com.easyteste.datasource.api.vo.response.GPlaceResponseVO;
import br.com.easyteste.datasource.listeners.OnPlaceResponseFinishedListener;
import br.com.easyteste.datasource.services.GPlaceService;
import br.com.easyteste.model.EmptyStateTypes;
import br.com.easyteste.model.GPlace;
import br.com.easyteste.model.GPlacesAutoComplete;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class GPlaceServiceImpl implements GPlaceService {

    ApiErrorResponseImpl apiErrorService = new ApiErrorResponseImpl();

    @Override
    public void resquestPlace(final OnPlaceResponseFinishedListener listener, GPlacesAutoComplete autoComplete) {

        String token = Easy.getContext().getString(R.string.google_app_id);

        Call<GPlaceResponseVO> call = Api.getGmapsAdapter().requestPlace(
                autoComplete.getPlace_id(),
                token
        );

        call.enqueue(new Callback<GPlaceResponseVO>() {
            @Override
            public void onResponse(Response<GPlaceResponseVO> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    if(response.body().result != null)
                        listener.onSuccess(new GPlace(response.body()));
                }else{
                    Log.e("Place Error","Response Error 4xx/5xx");
                    apiErrorService.processErrorResponse(response, listener);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                apiErrorService.processFailure(listener);
            }
        });

    }

}
