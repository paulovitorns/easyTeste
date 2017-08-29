package br.com.easyteste.view.activity;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;

import br.com.easyteste.R;
import br.com.easyteste.model.EmptyStateTypes;
import br.com.easyteste.presenter.MainPrensenter;
import br.com.easyteste.presenter.impl.MainPrensenterImpl;
import br.com.easyteste.util.SnackBarUtils;
import br.com.easyteste.view.MainView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.fragmentView)    FrameLayout     frameLayout;
    @Bind(R.id.searchToolbar)   Toolbar         toolbar;
    @Bind(R.id.recyclerView)    RecyclerView    recycler;
    private MainPrensenter prensenter;
    private static final int PERMISSION_LOCATION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        prensenter = new MainPrensenterImpl(this);
        prensenter.requestDefaultFavoritesPlaces();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchMenu     = menu.findItem(R.id.menu_search);
        SearchView searchView   = (SearchView) searchMenu.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(recycler.getVisibility() == View.GONE)
                    recycler.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recycler.setVisibility(View.GONE);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public Context getActivityContext() {
        return getApplicationContext();
    }

    @Override
    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_LOCATION_REQUEST_CODE);
    }

    @Override
    public void showError(EmptyStateTypes types) {
        SnackBarUtils.showSnackDialog(frameLayout, this, types);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_LOCATION_REQUEST_CODE: {

                if ( grantResults.length > 0
                        && ((grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        || (grantResults[1] == PackageManager.PERMISSION_GRANTED))) {

                } else {

                }
                return;
            }

        }
    }
}
