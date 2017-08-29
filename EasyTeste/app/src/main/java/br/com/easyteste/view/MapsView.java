package br.com.easyteste.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface MapsView {

    void tapOnSearchButton();

    void tapOnSeachBar();

    void requestFocusIntoSearch();

    void loadSearchResultsRecycler();

    void showSearchResult(RecyclerView.Adapter adapter);

    void tapOnFavorite();

    void tapOnRequestLocation();

    void tapOnFavoritePlaces();

    void showFavoritePlaces();

    void hideFavoritePlaces();

    void animateCam(LatLng latLng);

    Context getContext();

}
