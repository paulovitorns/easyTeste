package br.com.easyteste.model;

import java.util.ArrayList;
import java.util.List;

import br.com.easyteste.datasource.api.vo.response.FavoriteItemResponseVO;
import br.com.easyteste.datasource.api.vo.response.FavoritesResponseVO;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Favorites {

    private List<FavoriteItem> items;

    public Favorites(List<FavoriteItem> items) {
        this.items = items;
    }

    public Favorites(FavoritesResponseVO responseVO) {
        this.items = new ArrayList<>();
        generateFavorites(responseVO.list);
    }

    private void generateFavorites(List<FavoriteItemResponseVO> list){
        for (FavoriteItemResponseVO item : list){
            this.items.add(new FavoriteItem(item));
        }
    }

    public List<FavoriteItem> getItems() {
        return items;
    }
}
