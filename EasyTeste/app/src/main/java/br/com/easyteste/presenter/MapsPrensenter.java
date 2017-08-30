package br.com.easyteste.presenter;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;

import br.com.easyteste.model.PlaceItem;
import br.com.easyteste.model.Places;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface MapsPrensenter extends BasePresenter {
    void getPlacesFromBundle(Bundle bundle);
    void reloadPlaces();
    void generateAdapter(Places places);
    void requestUserPosition();
    void placeSelected(PlaceItem place);
}
