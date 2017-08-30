package br.com.easyteste.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import br.com.easyteste.model.GPlace;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface MainView extends BaseView {
    void showFragment(Fragment fragment, String tag);
    Context getActivityContext();
    void requestLocationPermission();
    void loadAdapter();
    void setPlacesAdapter(RecyclerView.Adapter adapter);
    void showEmpty();
    void hideEmpty();
    void updateMaps(GPlace gPlace);
}
