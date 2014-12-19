package fr.baptabl.reuniut;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends ActionBarActivity implements View.OnClickListener /*, ImageGetter*/ {
    //public boolean isConnected; inutilisée ?
    //public String tempTicket; inutilisée ?
    private String login;
    private String password; //moyen de sécuriser ? autre que du simple String ?
    static Context contextLayout; //quelle utilisation ? en l'état, c'est sale...

    //RelativeLayout layout = null;
    private TextView logoUTC = null;
    private EditText fieldLogin = null;
    private EditText fieldPasswd = null;
    private CheckBox keepLogin = null;
    private Button buttLogin = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextLayout = this.getBaseContext();
        setContentView(R.layout.activity_login);

    //récupération des identifiants
        SharedPreferences keptLog = getSharedPreferences("myLog", 0);
        String login = keptLog.getString("login", "");
        String password = keptLog.getString("pwd", "");

        fieldLogin = (EditText) findViewById(R.id.fieldLogin);
        fieldLogin.setText(login);
        fieldPasswd = (EditText) findViewById(R.id.fieldPasswd);
        fieldPasswd.setText(password);
        keepLogin = (CheckBox) findViewById(R.id.keepLogin);

//keepLogin.setOnClickListener(checkedListener);
        buttLogin = (Button) findViewById(R.id.buttLogin);
        buttLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View curView) {
        login = fieldLogin.getText().toString();
        password = fieldPasswd.getText().toString();

        if ( (login.length() > 0) && (password.length() > 0) ) {

            TextView ThrowConnect = (TextView) findViewById(R.id.ThrowConnect);
            Button b = (Button) curView;
            b.setText("Connexion en cours...");

        //keepLogin

            SharedPreferences keptLog = getSharedPreferences("myLog", 0);//à supprimer, présent dès le onCreate de la main activity
            if (keepLogin.isChecked()) {
                SharedPreferences.Editor editor = keptLog.edit();
                editor.putString("login", login);
                editor.putString("psswd", password);
                editor.commit();
                Toast toast = Toast.makeText(this, "login enregistré", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                SharedPreferences.Editor editor = keptLog.edit();
                editor.clear();
                editor.commit();
            }


//CAS
//Boolean connectionReussie = true;



//String ticket = Cas.getTicket(login, password);
/*
String ticket = null;
try {
ticket = Cas.getData();
} catch (Exception e) {
Log.e("Cas.postData()", "I got an error", e);
}
*/

            login curLogin = fr.baptabl.reuniut.login.getInstance(login, password);//on crée l'utilisateur

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


/* à faire : intégration image (logo)
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

}