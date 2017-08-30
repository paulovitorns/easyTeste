package br.com.easyteste.model;

import br.com.easyteste.datasource.api.vo.response.GPlacesAutoCompleteResponseVO;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class GPlacesAutoComplete {

    private String description;
    private String id;
    private String place_id;

    public GPlacesAutoComplete(GPlacesAutoCompleteResponseVO responseVO) {
        this.description    = responseVO.description;
        this.id             = responseVO.id;
        this.place_id       = responseVO.place_id;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getPlace_id() {
        return place_id;
    }
}
