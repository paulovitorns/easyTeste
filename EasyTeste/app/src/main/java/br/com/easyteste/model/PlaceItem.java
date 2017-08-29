package br.com.easyteste.model;

import java.io.Serializable;

import br.com.easyteste.datasource.api.vo.response.FavoriteItemResponseVO;
import br.com.easyteste.datasource.database.entity.PlacesEntity;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PlaceItem implements Serializable {

    public static final String KEY = PlaceItem.class.getSimpleName();

    private String name;
    private double latitude;
    private double longitude;

    public PlaceItem(String name, double latitude, double longitude) {
        this.name       = name;
        this.latitude   = latitude;
        this.longitude  = longitude;
    }

    public PlaceItem(FavoriteItemResponseVO responseVO) {
        this.name       = responseVO.name;
        this.latitude   = responseVO.latitude;
        this.longitude  = responseVO.longitude;
    }

    public PlaceItem(PlacesEntity place) {
        this.name       = place.getName();
        this.latitude   = place.getLat();
        this.longitude  = place.getLng();
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
