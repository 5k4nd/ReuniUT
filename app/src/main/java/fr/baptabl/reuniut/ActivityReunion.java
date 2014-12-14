package fr.baptabl.reuniut.ActivityReunion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.baptabl.reuniut.ActivityMenu;
import fr.baptabl.reuniut.R;

/**
 * Created by quentinkeunebroek on 12/12/14.
 */
public class ActivityReunion extends ActionBarActivity implements View.OnClickListener {
    private TextView erreurs = null;
    private TextView title=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent(); //on récupère le parent

        setContentView(R.layout.activity_menu);

        erreurs = (TextView) findViewById(R.id.erreurs);
        title = (TextView) findViewById(R.id.title);
    }

        @Override
        public void onClick (View v){
            erreurs = (TextView) findViewById(R.id.erreurs);

        }

    Intent newActivity = new Intent(ActivityMenu.this, ActivityReunion.class);



}
