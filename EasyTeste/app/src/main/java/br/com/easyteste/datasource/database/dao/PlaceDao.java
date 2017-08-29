package br.com.easyteste.datasource.database.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.easyteste.datasource.database.entity.PlacesEntity;
import br.com.easyteste.model.PlaceItem;
import br.com.easyteste.model.Places;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PlaceDao {

    private PlacesEntity place;

    public PlaceDao() {
    }

    public PlaceDao(PlacesEntity place) {
        this.place = place;
    }

    public void savePlace(){
        this.place.save();
    }

    public void saveAllPlaces(List<PlacesEntity> entities){
        for (PlacesEntity place : entities){
            place.save();
        }
    }

    public Places getPlaces(){
        List<PlacesEntity> places = PlacesEntity.listAll(PlacesEntity.class);

        List<PlaceItem> items = new ArrayList<>();

        for (PlacesEntity entity : places){
            items.add(new PlaceItem(entity));
        }

        return new Places(items);
    }
}
