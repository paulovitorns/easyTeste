package br.com.easyteste.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import br.com.easyteste.R;
import br.com.easyteste.model.GPlace;
import br.com.easyteste.model.Places;
import br.com.easyteste.presenter.MapsPrensenter;
import br.com.easyteste.presenter.impl.MapsPrensenterImpl;
import br.com.easyteste.view.MapsView;
import br.com.easyteste.view.component.CustomDialog;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MapsFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        MapsView{

    @Bind(R.id.fabMyLocation)           FloatingActionButton    fabMyLocation;
    @Bind(R.id.bottom_sheet)            LinearLayout            bottomSheet;
    @Bind(R.id.recyclerView)            RecyclerView            recycler;

    public static final String TAG = MapsFragment.class.getSimpleName();
    private static final int    PERMISSION_LOCATION_REQUEST_CODE = 200;
    private GoogleApiClient     mGoogleApiClient;
    private GoogleMap           gMap;
    private BottomSheetBehavior mBottomSheetBehavior;
    private MapsPrensenter      prensenter;
    private double              lastLat;
    private double              lastLng;

    public static MapsFragment newInstance(Places places){
        MapsFragment mapsFragment = new MapsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Places.KEY, places);
        mapsFragment.setArguments(bundle);
        return mapsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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

        prensenter = new MapsPrensenterImpl(this, mGoogleApiClient);

        if(getArguments() != null){
            prensenter.getPlacesFromBundle(getArguments());
        }

        if(savedInstanceState != null){
            lastLat = savedInstanceState.getDouble("lastLat");
            lastLng = savedInstanceState.getDouble("lastLng");
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        lastLat = (gMap != null) ? gMap.getCameraPosition().target.latitude : 0;
        lastLng = (gMap != null) ? gMap.getCameraPosition().target.longitude : 0;

        outState.putDouble("lastLat", lastLat);
        outState.putDouble("lastLng", lastLng);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_fav){
            CustomDialog customDialog = new CustomDialog(getContext(), gMap.getCameraPosition().target, prensenter);
            customDialog.show();
        }
        return super.onOptionsItemSelected(item);
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
        if(lastLng == 0 && lastLat == 0)
            prensenter.requestUserPosition();
        else
            updateCam(new LatLng(lastLat, lastLng));
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
    public void receiveNewPlace(GPlace gPlace) {
        updateCam(new LatLng(gPlace.getResult().getGeometry().getLat(), gPlace.getResult().getGeometry().getLng()));
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
    public void tapOnAddFavorite() {

    }

    @OnClick(R.id.fabMyLocation)
    @Override
    public void tapOnRequestLocation() {
        prensenter.requestUserPosition();
    }

    @OnClick(R.id.btnPeeker)
    @Override
    public void openBottomSheet() {
        int sizeOfPeeker = (int) getResources().getDimension(R.dimen.bottom_sheet_open_size);
        mBottomSheetBehavior.setPeekHeight(sizeOfPeeker);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if(newState == BottomSheetBehavior.STATE_DRAGGING){
                    int sizeOfPeeker = (int) getResources().getDimension(R.dimen.bottom_sheet_size);
                    mBottomSheetBehavior.setPeekHeight(sizeOfPeeker);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void closeBottomSheet() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        int sizeOfPeeker = (int) getResources().getDimension(R.dimen.bottom_sheet_size);
        mBottomSheetBehavior.setPeekHeight(sizeOfPeeker);
    }

    @Override
    public void showFavoritePlaces() {
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        int sizeOfPeeker = (int) getResources().getDimension(R.dimen.bottom_sheet_size);
        mBottomSheetBehavior.setPeekHeight(sizeOfPeeker);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setHideable(false);
    }

    @Override
    public void updateCam(LatLng latLng) {
        CameraUpdate updateCam = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        gMap.moveCamera(updateCam);
    }


    @Override
    public void requestLocationPermission() {
        requestPermissions(
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_LOCATION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_LOCATION_REQUEST_CODE: {

                if ( grantResults.length > 0
                        && ((grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        || (grantResults[1] == PackageManager.PERMISSION_GRANTED))) {
                        prensenter.requestUserPosition();
                }
                return;
            }

        }
    }

}
