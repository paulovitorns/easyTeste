package br.com.customsnackbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.customsnackbar.componet.CustomSnackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.txtView);

        CustomSnackbar.generateCustomSnack(
                textView,
                "icon", "exemplo de warning com no máximo duas linhas");

    }
}
