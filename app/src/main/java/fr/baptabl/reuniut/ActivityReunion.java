package fr.baptabl.reuniut.ActivityReunion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.baptabl.reuniut.ActivityMenu;
import fr.baptabl.reuniut.R;

/**
 * Created by quentinkeunebroek on 12/12/14.
 */
public abstract class ActivityReunion extends ActionBarActivity implements View.OnClickListener {
    private EditText fieldIntitule = null;

    public void onCreate(Bundle savedInstanceState) {
        fieldIntitule = (EditText) findViewById(R.id.fieldIntitule);

    }


}
