package fr.baptabl.reuniut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by bat on 06/01/15.
 */

public class ActivityVueReunion extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private /*CreneauxPossibles*/ String[] creneaux = {"creneau1","creneau2", "creneau3"}; /*null;*/
    private String reunionName = null;
    private Spinner fieldSpinner = null ;
    private Button buttRetour = null;

    private String creneauFinal = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        reunionName = i.getStringExtra("reunionName");

        //creneaux = login.getCreneauReu(reunionName));
        String[] creneaux = {"un","dos","tres"};
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, creneaux);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        setContentView(R.layout.activity_vue_reunion);

        fieldSpinner = (Spinner) findViewById(R.id.fieldSpinner);
        fieldSpinner.setAdapter(adapter_state);
        fieldSpinner.setOnItemSelectedListener(this);

        buttRetour = (Button) findViewById(R.id.buttRetour);
        buttRetour.setOnClickListener(this);

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        fieldSpinner.setSelection(position);
        String selState = (String) fieldSpinner.getSelectedItem();
        creneauFinal = selState;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onClick(View v) {

        Toast toast = Toast.makeText(this, creneauFinal, Toast.LENGTH_SHORT);
        toast.show();
        this.finish();
    }

}