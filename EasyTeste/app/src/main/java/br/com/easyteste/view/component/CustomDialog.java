package br.com.easyteste.view.component;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import br.com.easyteste.R;
import br.com.easyteste.datasource.database.dao.PlaceDao;
import br.com.easyteste.datasource.database.entity.PlacesEntity;
import br.com.easyteste.presenter.MapsPrensenter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class CustomDialog{

    @Bind(R.id.edtNamePlace) EditText placeName;

    private Dialog          dialog;
    private Context         context;
    private LatLng          place;
    private MapsPrensenter  prensenter;

    public interface CustomDialogCallBack {
        void dismissCallBack();
    }

    public CustomDialog(Context context, LatLng place, MapsPrensenter prensenter) {
        this.context    = context;
        this.place      = place;
        this.prensenter = prensenter;

        createDialog();
    }

    private void createDialog() {
        dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_favorite);
        dialog.setCancelable(true);

        ButterKnife.bind(this, dialog);
    }

    public void show(){
        dialog.show();
    }

    @OnClick(R.id.btnSave)
    public void onSave(){
        if(!placeName.getText().toString().equalsIgnoreCase("")){
            PlacesEntity placesEntity = new PlacesEntity(placeName.getText().toString(), place.latitude, place.longitude);
            PlaceDao placeDao = new PlaceDao(placesEntity);
            placeDao.savePlace();
            prensenter.reloadPlaces();
            dialog.dismiss();
        }
    }

}