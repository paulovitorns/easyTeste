package br.com.easyteste.presenter;

import br.com.easyteste.model.GPlacesAutoComplete;
import br.com.easyteste.model.PlaceItem;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface MainPrensenter extends BasePresenter{
    void requestDefaultFavoritesPlaces();
    void requestPlaces(String query);
    void placeSelected(GPlacesAutoComplete place);
}
