package br.com.customsnackbar.componet;

import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.LayoutDirection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.customsnackbar.R;

import static android.view.FrameMetrics.ANIMATION_DURATION;

/**
 * Created by paulovitornogueirasales on 29/08/17.
 */

public class CustomSnackbar {

    private CustomSnackbar() {
    }

    public static void generateCustomSnack(View view, String iconSnack, String msgSnack) {

        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setBackgroundColor(view.getContext().getResources().getColor(android.R.color.transparent));
        int heightOfSnack = (int) view.getContext().getResources().getDimension(R.dimen.snack_height);

        Snackbar.SnackbarLayout.LayoutParams params = (Snackbar.SnackbarLayout.LayoutParams) layout.getLayoutParams();
        params.height = heightOfSnack;

        layout.setLayoutParams(params);

        TextView txView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        txView.setVisibility(View.INVISIBLE);

        View snackView = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_snack_bar, null);

        TextView iconText = (TextView) snackView.findViewById(R.id.iconSnack);
        iconText.setText(iconSnack);

        TextView msgText = (TextView) snackView.findViewById(R.id.msgSnack);
        msgText.setText(msgSnack);

        layout.addView(snackView, 0);

        snackbar.show();
    }

}

