package fr.baptabl.reuniut;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


public class ReuniUTActivity extends ActionBarActivity implements View.OnClickListener/*, ImageGetter*/ {
    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        }
    */
    //RelativeLayout layout = null;
    TextView logoUTC = null;
    EditText fieldLogin = null;
    EditText fieldPasswd = null;

    CheckBox keepLogin = null;
    Button buttLogin = null; //= new Button (this, R.style.rect_field);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reuni_ut);

        fieldLogin = (EditText)findViewById(R.id.fieldLogin);
        fieldPasswd = (EditText)findViewById(R.id.fieldPasswd);

        keepLogin = (CheckBox) findViewById(R.id.keepLogin);
        //keepLogin.setOnClickListener(checkedListener);

        buttLogin = (Button) findViewById(R.id.buttLogin);
        buttLogin.setOnClickListener(this);


        //setContentView(layout);
    }

    @Override // CONNECTION
    public void onClick(View v) {
        Button b = (Button) v;
        b.setText("Connexion en cours...");
        CharSequence strKeeplog = null;
        String login = fieldLogin.getText().toString();

        if (keepLogin.isChecked()) {
            strKeeplog = "login enregistré";
            // setString(R.string.login=login); // att., impossible de mod. le strings.xml dynamiquement !
        }
        else {
            strKeeplog = "login non enregistré";
            // setString(R.string.login="login");
        }
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this,strKeeplog, duration);
        toast.show();






    }




	/*
	@Override
	public Drawable getDrawable(String source) {
		Drawable retour = null;

		//Resources resources = context.getResources();
		retour = getResources().getDrawable(R.drawable.logo_utc);
		// On délimite l'image (depuis coin en haut à gauche jusqu'à son coin en bas à droite)
		retour.setBounds(0, 0, retour.getIntrinsicWidth(),
				retour.getIntrinsicHeight());
		return retour;
	}
	*/





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reuni_ut, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
