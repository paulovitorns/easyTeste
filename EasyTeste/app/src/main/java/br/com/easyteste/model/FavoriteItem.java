package br.com.easyteste.model;

import br.com.easyteste.datasource.api.vo.response.FavoriteItemResponseVO;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class FavoriteItem {

    private String name;
    private double latitude;
    private double longitude;

    public FavoriteItem(String name, double latitude, double longitude) {
        this.name       = name;
        this.latitude   = latitude;
        this.longitude  = longitude;
    }

    public FavoriteItem(FavoriteItemResponseVO responseVO) {
        this.name       = responseVO.name;
        this.latitude   = responseVO.latitude;
        this.longitude  = responseVO.longitude;
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
