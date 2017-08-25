package br.com.easyteste.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.easyteste.R;
import br.com.easyteste.presenter.MainPrensenter;
import br.com.easyteste.presenter.impl.MainPrensenterImpl;
import br.com.easyteste.view.MainView;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPrensenter prensenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prensenter = new MainPrensenterImpl(this);
        prensenter.requestDefaultFavoritesPlaces();
    }

    @Override
    public void showFragment(Fragment fragment) {
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragmentView, fragment);
//        fragmentTransaction.commit();
    }
}
