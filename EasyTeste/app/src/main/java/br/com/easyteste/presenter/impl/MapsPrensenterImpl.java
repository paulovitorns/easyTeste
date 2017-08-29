package br.com.easyteste.presenter.impl;

import android.os.Bundle;

import br.com.easyteste.model.Places;
import br.com.easyteste.presenter.MapsPrensenter;
import br.com.easyteste.view.MapsView;
import br.com.easyteste.view.adapter.PlacesAdapter;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MapsPrensenterImpl implements MapsPrensenter {

    private MapsView mapsView;
    private PlacesAdapter adapter;

    public MapsPrensenterImpl(MapsView mapsView) {
        this.mapsView = mapsView;
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
            adapter = new PlacesAdapter(places, mapsView.getContext());

        mapsView.showSearchResult(adapter);
    }
}
