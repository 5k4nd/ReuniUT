package fr.baptabl.reuniut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



/**
 * Created by quentinkeunebroek on 12/12/14.
 */
public class ActivityReunion extends Activity implements View.OnClickListener {
    private Button buttReunion = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_reunion);
        buttReunion = (Button) findViewById(R.id.buttReunion);
        buttReunion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        showSimplePopUp();
    }

    private void showSimplePopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Création de la réunion");
        helpBuilder.setMessage("Nous sommes en train de chercher des créneaux. Veuillez patienter quelques instants...");
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent newActivity = new Intent(ActivityReunion.this, ActivitySelectReunion.class);
                        startActivity(newActivity);
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }

}
