package fr.baptabl.reuniut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bat on 13/12/14.
 */

//améliorer l'affichage

public class ActivityMenuVueEdt extends Activity implements View.OnClickListener {
    private TextView vueEdt = null ;
    private Button buttRetour = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();

        setContentView(R.layout.activity_menu_vue_edt);

        vueEdt = (TextView) findViewById(R.id.vueEdt);
        buttRetour = (Button) findViewById(R.id.buttRetour);
        buttRetour.setOnClickListener(this);

        Log.d("login.getInstance", "in ActivityMenu for ActivityMenuVueEdt");
        login curLogin = login.getInstance();
        String EDT = curLogin.getReunion(0).sujet+curLogin.getReunion(0).montreReunion();
        vueEdt.setText(EDT);
    }

    @Override
    public void onClick(View v) { this.finish(); }


}
