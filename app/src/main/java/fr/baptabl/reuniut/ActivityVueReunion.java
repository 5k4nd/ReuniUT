package fr.baptabl.reuniut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bat on 06/01/15.
 */

public class ActivityVueReunion extends Activity implements View.OnClickListener {
    private /*CreneauxPossibles*/ String[] creneaux = {"creneau1","creneau2", "creneau3"}; /*null;*/
    private String reunionName = null;
    private TextView vueEdt = null ;
    private Button buttRetour = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String reunionName = "coucou";//get bonus
        //creneaux = login.getCreneauReu(reunionName));

        setContentView(R.layout.activity_vue_reunion);

        //vueEdt = (TextView) findViewById(R.id.vueEdt);
        buttRetour = (Button) findViewById(R.id.buttRetour);
        buttRetour.setOnClickListener(this);

        Log.d("login.getInstance", "in ActivityMenu for ActivityMenuVueEdt");
        login curLogin = login.getInstance();
        String EDT = curLogin.getEmploi().montreEmploi();
        //vueEdt.setText(EDT);
    }

    @Override
    public void onClick(View v) {

        Toast toast = Toast.makeText(this, "COUCOU !", Toast.LENGTH_SHORT);
        toast.show();
        this.finish();
    }

}