package fr.baptabl.reuniut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bat on 10/12/14.
 */
// ceci est un TEST
public class ReuniUTMenu extends ActionBarActivity implements View.OnClickListener {
    private TextView erreurs = null ;
    private Button buttCreerGroupe = null;
    private Button buttCreerReu = null;
    private Button buttVoirEDT = null;
    private Button buttLogout = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent(); //on récupère le parent

        setContentView(R.layout.activity_menu);

        erreurs = (TextView) findViewById(R.id.erreurs);
        buttCreerGroupe = (Button) findViewById(R.id.buttCreerGroupe);
        buttCreerGroupe.setOnClickListener(this);
        buttCreerReu = (Button) findViewById(R.id.buttCreerReu);
        buttCreerReu.setOnClickListener(this);
        buttVoirEDT = (Button) findViewById(R.id.buttVoirEDT);
        buttVoirEDT.setOnClickListener(this);
        buttLogout = (Button) findViewById(R.id.buttLogout);
        buttLogout.setOnClickListener(this);

        //on récupère l'extra passé par ReuniUTActivity
        //boolean connectionReussie = i.getIntExtra(ReuniUTActivity.connectionReussie, 0);


    }

    @Override
    public void onClick(View v) {
        erreurs = (TextView) findViewById(R.id.erreurs);
        //Button buttLogout = (Button) v;
        switch(v.getId()){
            case R.id.buttLogout:
                erreurs.setText("je me déco!");
            break;

            case R.id.buttCreerGroupe:
                //blop
            break;

            case R.id.buttCreerReu:
                //blop
            break;

            case R.id.buttVoirEDT:
                //blop
            break;

        }

    }
}
