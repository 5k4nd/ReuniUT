package fr.baptabl.reuniut;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by quentinkeunebroek on 12/12/14.
 */
public class ActivitySelectReunion extends Activity {
    private Spinner spinner1, spinner2;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_reunion);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSelect);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showSimplePopUp();
            }
        });
    }

    private void showSimplePopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Sélection du créneau");
        helpBuilder.setMessage("Vous avez sélectionné un créneau, une notification a été envoyée à l'ensemble des participants. ");
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent newActivity = new Intent(ActivitySelectReunion.this, ActivityMenu.class);
                        startActivity(newActivity);
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }
}