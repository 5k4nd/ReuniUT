package fr.baptabl.reuniut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by quentinkeunebroek on 21/12/14.
 */
public class ActivityWaitReunion extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent i = getIntent();
            setContentView(R.layout.activity_select_reunion);
        }
}
