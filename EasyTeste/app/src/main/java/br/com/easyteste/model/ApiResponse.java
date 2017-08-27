package br.com.easyteste.model;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class ApiResponse {

    private int             code;
    private String          message;
    private EmptyStateTypes dialogType;

    public ApiResponse(){

    }

    public static ApiResponse getDefaultConnectionError(){
        ApiResponse api = new ApiResponse();
        api.dialogType = EmptyStateTypes.ERROR_TYPE_NO_CONNECTION;

        return api;
    }

    public static ApiResponse getDefaultLocationError(){
        ApiResponse api = new ApiResponse();
        api.dialogType = EmptyStateTypes.ERROR_TYPE_NO_LOCATION;

        return api;
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmptyStateTypes getDialogType() {
        return dialogType;
    }

    public void setDialogType(EmptyStateTypes dialogType) {
        this.dialogType = dialogType;
    }

}
