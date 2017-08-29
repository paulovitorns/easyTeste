package br.com.easyteste.datasource.services.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.easyteste.datasource.api.Api;
import br.com.easyteste.datasource.api.vo.request.FavoritesRequestVO;
import br.com.easyteste.datasource.api.vo.response.FavoritesResponseVO;
import br.com.easyteste.datasource.database.dao.PlaceDao;
import br.com.easyteste.datasource.database.entity.PlacesEntity;
import br.com.easyteste.datasource.listeners.FavoritesResponseListener;
import br.com.easyteste.datasource.services.FavoritesService;
import br.com.easyteste.model.PlaceItem;
import br.com.easyteste.model.Places;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class FavoritesServiceImpl implements FavoritesService {

    ApiErrorResponseImpl apiErrorService = new ApiErrorResponseImpl();

    @Override
    public void requestFavorites(FavoritesRequestVO requestVO, final FavoritesResponseListener listener) {

        Call<FavoritesResponseVO> call = Api.getAdapter().getDefaultFavorites(requestVO.token);

        call.enqueue(new Callback<FavoritesResponseVO>() {
            @Override
            public void onResponse(Response<FavoritesResponseVO> response, Retrofit retrofit) {

                if(response.isSuccess()){

                    List<PlacesEntity> entities = new ArrayList<PlacesEntity>();

                    Places places   = new Places(response.body());

                    for (PlaceItem item : places.getItems()){
                        PlaceDao dao = new PlaceDao(new PlacesEntity(item.getName(), item.getLatitude(), item.getLongitude()));
                        dao.savePlace();
                    }

                    listener.onSuccess(new PlaceDao().getPlaces());
                }else{
                    Log.e("FAVORITE_REQUEST","Response Error 4xx/5xx");
                    apiErrorService.processFailure(listener);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("FAVORITE_REQUEST","Fail on Request favorites "+t.getMessage());
                Log.e("FAVORITE_REQUEST","Fail on Request favorites "+t.getStackTrace());
                apiErrorService.processFailure(listener);
            }
        });

    }

}
