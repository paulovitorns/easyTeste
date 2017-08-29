package br.com.easyteste.model;

import android.content.Intent;
import android.provider.Settings;

import com.orm.SugarApp;

import br.com.easyteste.Easy;
import br.com.easyteste.R;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public enum EmptyStateTypes {

    ERROR_TYPE_NO_CONNECTION(
            Easy.getContext().getString(R.string.error_no_connection_msg),
            Easy.getContext().getString(R.string.error_no_connection_action),
            new Intent(Settings.ACTION_WIFI_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    ),
    ERROR_TYPE_NO_LOCATION(
            Easy.getContext().getString(R.string.error_location_disabled_msg),
            Easy.getContext().getString(R.string.error_location_disabled_action),
            new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    ),
    EMPTY_STATE_NO_RESULT(
            Easy.getContext().getString(R.string.empty_state),
            "",
            null
    );

    private String msg;
    private String btnAction;
    private Intent action;

    EmptyStateTypes(String msg, String btnAction, Intent action){
        this.msg        = msg;
        this.btnAction  = btnAction;
        this.action     = action;
    }

    public String getMsg() {
        return msg;
    }

    public String getBtnAction() {
        return btnAction;
    }

    public Intent getAction() {
        return action;
    }
}
