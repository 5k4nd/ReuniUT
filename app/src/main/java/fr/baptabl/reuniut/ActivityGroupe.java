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
 * Created by quentinkeunebroek on 22/12/14.
 */
public class ActivityGroupe extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_groupe);
    }

    @Override
    public void onClick(View v){
        showSimplePopUp();
    }

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

}
