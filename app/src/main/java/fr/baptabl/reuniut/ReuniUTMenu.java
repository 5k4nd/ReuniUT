package fr.baptabl.reuniut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by bat on 10/12/14.
 */

public class ReuniUTMenu extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent i = getIntent(); //on récupère le parent

        //on récupère l'extra passé
        //int age = i.getIntExtra(ReuniUTActivity.AGE, 0);


    }
}
