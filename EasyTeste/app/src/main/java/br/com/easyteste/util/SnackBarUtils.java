package br.com.easyteste.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import br.com.easyteste.R;
import br.com.easyteste.model.EmptyStateTypes;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class SnackBarUtils {

    public static void showSnackDialog(View view, final Context context, final EmptyStateTypes dialogTypes){

        final Snackbar snackbar;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            snackbar = Snackbar
                    .make(view, dialogTypes.getMsg(), Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(context.getColor(R.color.colorAccent))
                    .setAction(dialogTypes.getBtnAction(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(dialogTypes.getAction());
                        }
                    });
        }else{
            snackbar = Snackbar
                    .make(view, dialogTypes.getMsg(), Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(context.getResources().getColor(R.color.colorAccent))
                    .setAction(dialogTypes.getBtnAction(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(dialogTypes.getAction());
                        }
                    });
        }

        snackbar.show();

    }

}
