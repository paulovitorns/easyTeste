package br.com.easyteste.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import br.com.easyteste.R;
import br.com.easyteste.model.Places;
import br.com.easyteste.presenter.MapsPrensenter;
import br.com.easyteste.presenter.impl.MapsPrensenterImpl;
import br.com.easyteste.view.MapsView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MapsFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        MapsView{

    @Bind(R.id.fabMyLocation)   FloatingActionButton    fabMyLocation;
    @Bind(R.id.recyclerView)    RecyclerView            recycler;

    private GoogleApiClient     mGoogleApiClient;
    private GoogleMap           gMap;
    private BottomSheetBehavior mBottomSheetBehavior;
    private MapsPrensenter      prensenter;

    public static MapsFragment newInstance(Places places){
        MapsFragment mapsFragment = new MapsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Places.KEY, places);
        mapsFragment.setArguments(bundle);
        return mapsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        setHasOptionsMenu(true);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.mapFrag, mapFragment).commit();
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        ButterKnife.bind(this, view);

        prensenter = new MapsPrensenterImpl(this);

        if(getArguments() != null){
            prensenter.getPlacesFromBundle(getArguments());
        }

        mBottomSheetBehavior = BottomSheetBehavior.from(recycler);

        int sizeOfPeeker = (int) getResources().getDimension(R.dimen.bottom_sheet_size);

        mBottomSheetBehavior.setPeekHeight(sizeOfPeeker);
        mBottomSheetBehavior.setHideable(false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("CONNECTION_SUSPENDED", "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("CONNECTION_FAILED", "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
    }

    @Override
    public void tapOnSearchButton() {

    }

    @Override
    public void tapOnSeachBar() {

    }

    @Override
    public void requestFocusIntoSearch() {

    }

    @Override
    public void loadSearchResultsRecycler() {
        recycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(mLayoutManager);
    }

    @Override
    public void showSearchResult(RecyclerView.Adapter adapter) {
        recycler.setAdapter(adapter);
    }

    @Override
    public void tapOnFavorite() {

    }

    @Override
    public void tapOnRequestLocation() {

    }

    @Override
    public void tapOnFavoritePlaces() {

    }

    @Override
    public void showFavoritePlaces() {

    }

    @Override
    public void hideFavoritePlaces() {

    }

    @Override
    public void animateCam(LatLng latLng) {

    }
}
