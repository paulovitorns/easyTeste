package br.com.easyteste.view.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import br.com.easyteste.R;
import br.com.easyteste.model.EmptyStateTypes;
import br.com.easyteste.model.GPlace;
import br.com.easyteste.presenter.MainPrensenter;
import br.com.easyteste.presenter.impl.MainPrensenterImpl;
import br.com.easyteste.util.SnackBarUtils;
import br.com.easyteste.view.MainView;
import br.com.easyteste.view.fragment.MapsFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.fragmentView)    FrameLayout     frameLayout;
    @Bind(R.id.searchToolbar)   Toolbar         toolbar;
    @Bind(R.id.containerSearch) RelativeLayout  containerSearch;
    @Bind(R.id.emptyState)      RelativeLayout  emptyState;
    @Bind(R.id.recyclerView)    RecyclerView    recycler;

    private MainPrensenter      prensenter;
    private boolean             isSearchOpen;
    private SearchView          mSearchView;
    private MenuItem            favMenu;
    private Fragment            fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        prensenter = new MainPrensenterImpl(this);

        if(savedInstanceState == null) {
            prensenter.requestDefaultFavoritesPlaces();
        }else{

            fragment = getSupportFragmentManager().findFragmentByTag(MapsFragment.TAG);
            if (fragment == null) {
                prensenter.requestDefaultFavoritesPlaces();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        fragment = getSupportFragmentManager().findFragmentByTag(MapsFragment.TAG);
        if (fragment == null) {
            prensenter.requestDefaultFavoritesPlaces();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        favMenu             = menu.findItem(R.id.menu_fav);
        MenuItem searchMenu = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) searchMenu.getActionView();

        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
        ImageView v = (ImageView) mSearchView.findViewById(searchImgId);
        v.setImageResource(R.drawable.ic_search_black_24dp);

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favMenu.setVisible(false);
                if(containerSearch.getVisibility() == View.GONE)
                    containerSearch.setVisibility(View.VISIBLE);
                if(!isSearchOpen)
                    isSearchOpen = true;
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                prensenter.requestPlaces(newText);
                return false;
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                favMenu.setVisible(true);
                containerSearch.setVisibility(View.GONE);
                isSearchOpen = false;
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(isSearchOpen){
            mSearchView.onActionViewCollapsed();
            isSearchOpen = false;
            containerSearch.setVisibility(View.GONE);
            favMenu.setVisible(true);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void showFragment(Fragment fragment, String tag) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, fragment);
        fragmentTransaction.replace(R.id.fragmentView, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public Context getActivityContext() {
        return getApplicationContext();
    }

    @Override
    public void showError(EmptyStateTypes types) {
        SnackBarUtils.showSnackDialog(frameLayout, this, types);
    }

    @Override
    public void loadAdapter() {
        recycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(mLayoutManager);
    }

    @Override
    public void setPlacesAdapter(RecyclerView.Adapter adapter) {
        recycler.setAdapter(adapter);
    }

    @Override
    public void showEmpty() {
        emptyState.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
    }

    @Override
    public void hideEmpty() {
        emptyState.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateMaps(GPlace gPlace) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(MapsFragment.TAG);
        if(fragment instanceof MapsFragment){

            isSearchOpen = false;
            favMenu.setVisible(true);
            mSearchView.onActionViewCollapsed();
            containerSearch.setVisibility(View.GONE);

            MapsFragment maps = (MapsFragment) fragment;
            maps.receiveNewPlace(gPlace);
        }
    }
}
