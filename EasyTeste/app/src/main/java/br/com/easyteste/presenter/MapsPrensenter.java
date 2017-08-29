package br.com.easyteste.presenter;

import android.os.Bundle;

import br.com.easyteste.model.Places;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface MapsPrensenter extends BasePresenter {
    void getPlacesFromBundle(Bundle bundle);
    void generateAdapter(Places places);
}
