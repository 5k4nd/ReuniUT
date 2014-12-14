package fr.baptabl.reuniut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
    public void onClick(View v) { this.finish(); }


}
