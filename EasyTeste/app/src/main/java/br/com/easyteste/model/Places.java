package br.com.easyteste.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.easyteste.datasource.api.vo.response.FavoriteItemResponseVO;
import br.com.easyteste.datasource.api.vo.response.FavoritesResponseVO;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Places implements Serializable{

    public static final String KEY = Places.class.getSimpleName();

    private List<PlaceItem> items;

    public Places(List<PlaceItem> items) {
        this.items = items;
    }

    public Places(FavoritesResponseVO responseVO) {
        this.items = new ArrayList<>();
        generateFavorites(responseVO.list);
    }

    private void generateFavorites(List<FavoriteItemResponseVO> list){
        for (FavoriteItemResponseVO item : list){
            this.items.add(new PlaceItem(item));
        }
    }

    public List<PlaceItem> getItems() {
        return items;
    }
}
