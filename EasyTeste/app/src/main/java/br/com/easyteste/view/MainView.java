package br.com.easyteste.view;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface MainView extends BaseView {
    void showFragment(Fragment fragment);
    Context getActivityContext();
    void requestLocationPermission();
}
