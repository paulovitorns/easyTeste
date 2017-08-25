package br.com.easyteste.presenter.impl;

import br.com.easyteste.datasource.api.vo.request.FavoritesRequestVO;
import br.com.easyteste.datasource.listeners.FavoritesResponseListener;
import br.com.easyteste.datasource.services.FavoritesService;
import br.com.easyteste.datasource.services.ManagerDefaultPlaces;
import br.com.easyteste.datasource.services.impl.FavoritesServiceImpl;
import br.com.easyteste.datasource.services.impl.ManagerDefaultPlacesImpl;
import br.com.easyteste.model.ApiResponse;
import br.com.easyteste.model.Favorites;
import br.com.easyteste.presenter.MainPrensenter;
import br.com.easyteste.view.MainView;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MainPrensenterImpl implements MainPrensenter, FavoritesResponseListener {

    private ManagerDefaultPlaces    managerPlaces;
    private FavoritesService        favoritesService;
    private MainView                mainView;

    public MainPrensenterImpl(MainView mainView) {
        this.mainView = mainView;
        init();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void init() {
        managerPlaces       = new ManagerDefaultPlacesImpl();
        favoritesService    = new FavoritesServiceImpl();
    }

    @Override
    public void requestDefaultFavoritesPlaces() {
        if(!managerPlaces.isAlreadyLoaded()) {
            FavoritesRequestVO requestVO = new FavoritesRequestVO();
            requestVO.token = "M9e1vpTd";
            favoritesService.requestFavorites(requestVO, this);
        }else{
            mainView.showFragment(null);
        }
    }

    @Override
    public void onError(ApiResponse error) {
        mainView.showFragment(null);
    }

    @Override
    public void onConnectionFail(ApiResponse error) {
        mainView.showFragment(null);
    }

    @Override
    public void onServerNotRespond(ApiResponse error) {
        mainView.showFragment(null);
    }

    @Override
    public void onSuccess(Favorites favorites) {
//        managerPlaces.setLoaded();
        mainView.showFragment(null);
    }
}
