package fr.baptabl.reuniut;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by quentinkeunebroek on 22/12/14.
 */
public class ActivityGroupe extends Activity implements View.OnClickListener {
    private EditText fieldGroupName = null;
    private EditText fieldLogins = null;
    private Button buttCreerGroupe = null;

    private String groupName;
    private String logins;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_groupe);

        fieldGroupName = (EditText) findViewById(R.id.fieldGroupName);
        fieldLogins = (EditText) findViewById(R.id.fieldLogins);

        buttCreerGroupe = (Button) findViewById(R.id.buttCreerGroupe);
        buttCreerGroupe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        groupName = fieldGroupName.getText().toString();
        logins = fieldLogins.getText().toString();

        if ( (groupName.length() > 0) && (logins.length() > 0) ) {
            //on envoie groupName et logins à la classe login

            this.finish();
        }
        else {
            AlertDialog ad = new AlertDialog.Builder(this)
                    .setPositiveButton("Ok", null).setTitle("Erreur").setMessage("Merci d'entrer un nom de groupe et les logins désirés !")
                    .create();
            ad.show();
        }

    }

    /*
    private void showSimplePopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Création du groupe");
        helpBuilder.setMessage("Le groupe a bien été créé, vous pouvez à présent chercher un créneau pour organiser une réunion.");
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent newActivity = new Intent(ActivityGroupe.this, ActivityMenu.class);
                        startActivity(newActivity);
                    }
                });


        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }
    */
}
