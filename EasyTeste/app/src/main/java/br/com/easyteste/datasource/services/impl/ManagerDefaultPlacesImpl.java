package br.com.easyteste.datasource.services.impl;

import br.com.easyteste.datasource.services.ManagerDefaultPlaces;
import br.com.easyteste.util.SharedPreferencesUtils;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class ManagerDefaultPlacesImpl implements ManagerDefaultPlaces {

    @Override
    public boolean isAlreadyLoaded() {
        return SharedPreferencesUtils.isAlreadyLoaded();
    }

    @Override
    public void setLoaded() {
        SharedPreferencesUtils.saveAlreadyLoaded();
    }
}
