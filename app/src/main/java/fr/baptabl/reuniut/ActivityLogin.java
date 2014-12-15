package fr.baptabl.reuniut;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
public class ActivityLogin extends ActionBarActivity implements View.OnClickListener /*, ImageGetter*/ {
    public boolean isConnected;
    public String tempTicket;
    private String login;
    private String password;
    static Context contextLayout;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    }
    */
//Interface
//RelativeLayout layout = null;
    private TextView logoUTC = null;
    private EditText fieldLogin = null;
    private EditText fieldPasswd = null;
    private CheckBox keepLogin = null;
    private Button buttLogin = null; //= new Button (this, R.style.rect_field);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextLayout = this.getBaseContext();
        setContentView(R.layout.activity_login);
        fieldLogin = (EditText) findViewById(R.id.fieldLogin);
        fieldPasswd = (EditText) findViewById(R.id.fieldPasswd);
        keepLogin = (CheckBox) findViewById(R.id.keepLogin);
//keepLogin.setOnClickListener(checkedListener);
        buttLogin = (Button) findViewById(R.id.buttLogin);
        buttLogin.setOnClickListener(this);
    }
    //redéfinition de onClick => CONNECTION
    @Override
    public void onClick(View v) {
        int usersize = fieldLogin.getText().length();

        int passsize = fieldPasswd.getText().length();
        // si les deux champs sont remplis
        if (usersize > 0 && passsize > 0) {

            TextView ThrowConnect = (TextView) findViewById(R.id.ThrowConnect);
            Button b = (Button) v;
            b.setText("Connexion en cours...");
//keepLogin
            CharSequence strKeeplog = null;
            if (keepLogin.isChecked()) {
                strKeeplog = "login enregistré";
// setString(R.string.login=login); // att., impossible de mod. le strings.xml dynamiquement !
            } else {
                strKeeplog = "login non enregistré";
// setString(R.string.login="login");
            }
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, strKeeplog, duration);
            toast.show();
//CAS
//Boolean connectionReussie = true;

            login = fieldLogin.getText().toString();
            password = fieldPasswd.getText().toString();


//String ticket = Cas.getTicket(login, password);
/*
String ticket = null;
try {
ticket = Cas.getData();
} catch (Exception e) {
Log.e("Cas.postData()", "I got an error", e);
}
*/

            login curLogin = fr.baptabl.reuniut.login.getInstance(login, password);// On cree l'utilisateur


            if (curLogin == null /* a décomenter lorsque on aura réussi à faire attendre la requête  || curLogin.getEmploi() == null || curLogin.getEmploi().montreEmploi() == null*/)//Si le constructeur a renvoyé une erreur
            {
                curLogin = null;
                b.setText("Se connecter");
                ThrowConnect.setText("Connexion impossible");
            } else {
                Log.v("ELSE", "on a atteint le else");
                b.setText("Connecté");
//ThrowConnect.setText(ticket);
                Intent newActivity = new Intent(ActivityLogin.this, ActivityMenu.class);
//newActivity.putExtra("EDT", EDT);
                startActivity(newActivity);
            }
        }
        else
        {
            AlertDialog ad = new AlertDialog.Builder(this)
                    .setPositiveButton("Ok", null).setTitle("Erreur").setMessage("Merci d'entrer vos login et mot de passe !")
                    .create();
            ad.show();
        }
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
/*
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
*/
}