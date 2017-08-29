package br.com.easyteste.datasource.database.entity;

import com.orm.SugarRecord;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PlacesEntity extends SugarRecord {

    String name;
    double lat;
    double lng;

    public PlacesEntity() {
    }

    public PlacesEntity(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
