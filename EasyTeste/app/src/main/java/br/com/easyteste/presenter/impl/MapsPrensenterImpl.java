package br.com.easyteste.presenter.impl;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import br.com.easyteste.model.PlaceItem;
import br.com.easyteste.model.Places;
import br.com.easyteste.presenter.MapsPrensenter;
import br.com.easyteste.view.MapsView;
import br.com.easyteste.view.adapter.PlacesAdapter;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MapsPrensenterImpl implements MapsPrensenter {

    private MapsView        mapsView;
    private PlacesAdapter   adapter;
    private GoogleApiClient apiClient;

    public MapsPrensenterImpl(MapsView mapsView, GoogleApiClient apiClient) {
        this.mapsView   = mapsView;
        this.apiClient  = apiClient;
        init();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void init() {
    }

    @Override
    public void getPlacesFromBundle(Bundle bundle) {
        generateAdapter((Places) bundle.getSerializable(Places.KEY));
        mapsView.loadSearchResultsRecycler();
    }

    @Override
    public void generateAdapter(Places places) {

        if(adapter == null)
            adapter = new PlacesAdapter(places, mapsView.getContext(), this);

        mapsView.showSearchResult(adapter);
        mapsView.showFavoritePlaces();
    }

    @Override
    public void requestUserPosition() {
        if (ActivityCompat.checkSelfPermission(mapsView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mapsView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Request Permission
            return;
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mapsView.updateCam(latLng);
    }

    @Override
    public void placeSelected(PlaceItem place) {
        LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());
        mapsView.updateCam(latLng);
    }
}
