package br.com.easyteste.presenter.impl;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import br.com.easyteste.datasource.api.vo.request.FavoritesRequestVO;
import br.com.easyteste.datasource.listeners.FavoritesResponseListener;
import br.com.easyteste.datasource.services.FavoritesService;
import br.com.easyteste.datasource.services.ManagerDefaultPlaces;
import br.com.easyteste.datasource.services.impl.FavoritesServiceImpl;
import br.com.easyteste.datasource.services.impl.ManagerDefaultPlacesImpl;
import br.com.easyteste.model.ApiResponse;
import br.com.easyteste.model.EmptyStateTypes;
import br.com.easyteste.model.Favorites;
import br.com.easyteste.presenter.MainPrensenter;
import br.com.easyteste.presenter.RequestPermissionPrensenter;
import br.com.easyteste.util.NetworkUtils;
import br.com.easyteste.view.MainView;
import br.com.easyteste.view.fragment.MapsFragment;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MainPrensenterImpl implements MainPrensenter,
        FavoritesResponseListener,
        RequestPermissionPrensenter{

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

            if(NetworkUtils.hasActiveInternetConnection()){
                FavoritesRequestVO requestVO = new FavoritesRequestVO();
                requestVO.token = "M9e1vpTd";
                favoritesService.requestFavorites(requestVO, this);
            }else{
                mainView.showError(EmptyStateTypes.ERROR_TYPE_NO_CONNECTION);
            }

        }else{
            requestLocationPermission();
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
        if(!NetworkUtils.isLocationAvailable()){
            mainView.showError(EmptyStateTypes.ERROR_TYPE_NO_LOCATION);
        }else{
            mainView.showFragment(MapsFragment.newInstance());
            requestLocationPermission();
        }
    }

    @Override
    public boolean hasLocationPermission() {
        return ActivityCompat.checkSelfPermission(mainView.getActivityContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mainView.getActivityContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestLocationPermission() {
        if(!hasLocationPermission()){
            mainView.requestLocationPermission();
        }
    }
}
