package br.com.easyteste.presenter.impl;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import br.com.easyteste.datasource.api.vo.request.FavoritesRequestVO;
import br.com.easyteste.datasource.database.dao.PlaceDao;
import br.com.easyteste.datasource.listeners.FavoritesResponseListener;
import br.com.easyteste.datasource.listeners.OnGPlacesRequestFinished;
import br.com.easyteste.datasource.listeners.OnPlaceResponseFinishedListener;
import br.com.easyteste.datasource.services.FavoritesService;
import br.com.easyteste.datasource.services.GPlaceService;
import br.com.easyteste.datasource.services.GPlacesService;
import br.com.easyteste.datasource.services.ManagerDefaultPlaces;
import br.com.easyteste.datasource.services.impl.FavoritesServiceImpl;
import br.com.easyteste.datasource.services.impl.GPlaceServiceImpl;
import br.com.easyteste.datasource.services.impl.GPlacesServiceImpl;
import br.com.easyteste.datasource.services.impl.ManagerDefaultPlacesImpl;
import br.com.easyteste.model.ApiResponse;
import br.com.easyteste.model.EmptyStateTypes;
import br.com.easyteste.model.GPlace;
import br.com.easyteste.model.GPlaces;
import br.com.easyteste.model.GPlacesAutoComplete;
import br.com.easyteste.model.PlaceItem;
import br.com.easyteste.model.Places;
import br.com.easyteste.presenter.MainPrensenter;
import br.com.easyteste.presenter.RequestPermissionPrensenter;
import br.com.easyteste.util.NetworkUtils;
import br.com.easyteste.view.MainView;
import br.com.easyteste.view.adapter.PlacesAdapter;
import br.com.easyteste.view.adapter.PlacesSearchAdapter;
import br.com.easyteste.view.fragment.MapsFragment;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MainPrensenterImpl implements MainPrensenter,
        FavoritesResponseListener,
        RequestPermissionPrensenter,
        OnGPlacesRequestFinished,
        OnPlaceResponseFinishedListener{

    private ManagerDefaultPlaces    managerPlaces;
    private FavoritesService        favoritesService;
    private MainView                mainView;
    private GPlacesService          gPlacesService;
    private GPlaceService           gPlaceService;
    private PlacesSearchAdapter     adapter;
    private GPlaces                 places;

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
        gPlacesService      = new GPlacesServiceImpl();
        gPlaceService       = new GPlaceServiceImpl();

        mainView.loadAdapter();
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
            onSuccess(new PlaceDao().getPlaces());
        }
    }

    @Override
    public void requestPlaces(String query) {
        gPlacesService.onRequestPlaces(this, query);
    }

    @Override
    public void placeSelected(GPlacesAutoComplete place) {
        gPlaceService.resquestPlace(this, place);
    }

    @Override
    public void onError(ApiResponse error) {
        mainView.showFragment(null, "");
    }

    @Override
    public void onConnectionFail(ApiResponse error) {
        mainView.showFragment(null, "");
    }

    @Override
    public void onServerNotRespond(ApiResponse error) {
        mainView.showFragment(null, "");
    }

    @Override
    public void onSuccess(Places places) {
        managerPlaces.setLoaded();
        if(!NetworkUtils.isLocationAvailable()){
            mainView.showError(EmptyStateTypes.ERROR_TYPE_NO_LOCATION);
        }else{
            mainView.showFragment(MapsFragment.newInstance(places), MapsFragment.TAG);
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

    @Override
    public void onSuccess(GPlaces places) {

        if(this.places == null){
            this.places = places;
            this.adapter = new PlacesSearchAdapter(places, mainView.getActivityContext(), this);
            this.mainView.setPlacesAdapter(adapter);
        }else{
            if(adapter.getItemCount() > 0)
                adapter.removeAllItens();

            adapter.addAll(places.getPredictions());
        }
    }

    @Override
    public void onPlacesErros(EmptyStateTypes error) {
        if(adapter != null && adapter.getItemCount() > 0)
            adapter.removeAllItens();

    }

    @Override
    public void onSuccess(GPlace place) {

        mainView.updateMaps(place);
    }
}
