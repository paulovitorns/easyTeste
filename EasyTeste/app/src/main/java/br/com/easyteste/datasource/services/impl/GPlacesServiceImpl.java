package br.com.easyteste.datasource.services.impl;

import android.util.Log;

import br.com.easyteste.Easy;
import br.com.easyteste.R;
import br.com.easyteste.datasource.api.Api;
import br.com.easyteste.datasource.api.vo.response.GPlacesResponseVO;
import br.com.easyteste.datasource.listeners.OnGPlacesRequestFinished;
import br.com.easyteste.datasource.services.GPlacesService;
import br.com.easyteste.model.EmptyStateTypes;
import br.com.easyteste.model.GPlaces;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class GPlacesServiceImpl implements GPlacesService {

    ApiErrorResponseImpl apiErrorService = new ApiErrorResponseImpl();

    @Override
    public void onRequestPlaces(final OnGPlacesRequestFinished listener, String param) {

        String token = Easy.getContext().getString(R.string.google_app_id);
        String types = "geocode";
        param = param.replaceAll(" ", "+");

        Call<GPlacesResponseVO> call = Api.getGmapsAdapter().requestPlaces(
                param,
                types,
                token
        );

        call.enqueue(new Callback<GPlacesResponseVO>() {

            @Override
            public void onResponse(Response<GPlacesResponseVO> response, Retrofit retrofit) {

                if(response.isSuccess()){
                    if(response.body().predictions.size() > 0)
                        listener.onSuccess(new GPlaces(response.body()));
                    else
                        listener.onPlacesErros(EmptyStateTypes.EMPTY_STATE_NO_RESULT);
                }else{
                    Log.e("Places Error","Response Error 4xx/5xx");
                    apiErrorService.processFailure(listener);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                apiErrorService.processFailure(listener);
            }

        });

    }

}
