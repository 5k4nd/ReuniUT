package fr.baptabl.reuniut;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * Created by quentinkeunebroek on 12/12/14.
 */

public class ActivityReunion extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText fieldReunionName = null;
    private EditText fieldDescriptif = null;
    private Spinner fieldSpinnerGroup1 = null;
    private Spinner fieldSpinnerGroup2 = null;
    private EditText fieldDateDebut = null;
    private EditText fieldDateFin = null;
    private Button buttReunion = null;

    private String reunionName;
    private String descriptif;
    private String group1 = null;
    private String group2 = null;
    private String dateDebut;
    private String dateFin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String[] getGroups = {"etUnGroupe","etDeuxGroupe"};
        System.out.println(getGroups.length);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getGroups);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        setContentView(R.layout.activity_reunion);

        fieldReunionName = (EditText) findViewById(R.id.fieldReunionName);
        fieldDescriptif = (EditText) findViewById(R.id.fieldDescriptif);

        fieldSpinnerGroup1 = (Spinner) findViewById(R.id.fieldSpinner1);
        fieldSpinnerGroup1 = (Spinner) findViewById(R.id.fieldSpinner1);
        fieldSpinnerGroup1.setAdapter(adapter_state);
        fieldSpinnerGroup1.setOnItemSelectedListener(this);

        fieldSpinnerGroup2 = (Spinner) findViewById(R.id.fieldSpinner2);
        fieldSpinnerGroup2 = (Spinner) findViewById(R.id.fieldSpinner2);
        fieldSpinnerGroup2.setAdapter(adapter_state);
        fieldSpinnerGroup2.setOnItemSelectedListener(this);

        fieldDateDebut = (EditText) findViewById(R.id.fieldDateDebut);
        fieldDateFin = (EditText) findViewById(R.id.fieldDateFin);

        buttReunion = (Button) findViewById(R.id.buttReunion);
        buttReunion.setOnClickListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selState;
        Log.v("onItemSelected", "on est dedans et la vue est :");
        Log.v("onItemSelected", String.valueOf(parent));
        switch(parent.getId()){
            case R.id.fieldSpinner1:
                Log.v("onItemSelected", "fieldSpinner1 ATTEINT ! o/");
                fieldSpinnerGroup1.setSelection(position);
                selState = (String) fieldSpinnerGroup1.getSelectedItem();
                group1 = selState;
                Log.v("onItemSelected", "group1 vaut:"+selState);
                break;

            case R.id.fieldSpinner2:
                fieldSpinnerGroup2.setSelection(position);
                selState = (String) fieldSpinnerGroup2.getSelectedItem();
                group2= selState;
                Log.v("onItemSelected", "group2 vaut:"+selState);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onClick(View v){
        reunionName = fieldReunionName.getText().toString();
        descriptif = fieldDescriptif.getText().toString();
        dateDebut = fieldDateDebut.getText().toString();
        dateFin = fieldDateFin.getText().toString();

        if ( (reunionName.length() > 0) && (descriptif.length() > 0) && (group1.length() > 0) && (group2.length() > 0) && (dateDebut.length() > 0) && (dateFin.length() > 0) ) {
            //on envoie les données à la classe réunion

            this.finish();
        }
        else {
            AlertDialog ad = new AlertDialog.Builder(this)
                    .setPositiveButton("Ok", null).setTitle("Erreur").setMessage("Merci de remplir tous les champs !")
                    .create();
            ad.show();
        }
    }

    /*
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
    */

}
