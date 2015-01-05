package fr.baptabl.reuniut;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Thread.sleep;

/*NOTE : erreurs connues :
    - il faut se connecter deux fois via le bouton "Se connecter", puisque la réponse de la requête arrive trop tard pour la premièr fois.
    - il faut redémarrer l'appli pour accéder à l'edt si c'est la première connexion. le temps que le script serveur récupère est trop long pour l'appli.
*/

public class ActivityLogin extends Activity implements View.OnClickListener /*, ImageGetter*/ {
    public boolean isConnected; //devra être dans le CAS
    public boolean badLogin; //devra être dans le CAS
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
        isConnected = false;
        badLogin = false;
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
        //CAS monCAS = new CAS();
        login = fieldLogin.getText().toString();
        password = fieldPasswd.getText().toString();

        if ( (login.length() > 0) && (password.length() > 0) ) {
            TextView ThrowConnect = (TextView) findViewById(R.id.ThrowConnect);
            Button b = (Button) curView;
            b.setText("Connexion en cours...");

            //BUG : cette requête ne fonctionnera jamais la première fois car elle ne se lance qu'à la fin du onClick. il faut donc cliquer deux fois sur "Se connecter" pour le moment...
            new HttpAsyncTask().execute("http://bat.demic.eu/cas/login.php?login="+login+"&password="+password);

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
            Log.v("TOAST", "fin keepLogin");

        //test CAS de logins corrects
            int i = 0;
            while ( (i < 1) && (this.isConnected == false) ) {//augmenter le i pour les tests. initialement, c'était pour les tests d'attente de la requête. fail. il faut cliquer 2 fois sur se connecter pour le moment.
                try {//on attend avec de la marge (oui c'est sale...) le retour de la requête. l'idéal serait d'attendre le retour de la requête avec une méthode d'HTTPrequest, ça serait *nettement* plus propre.
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                Log.v("connexionReussie ? :'", String.valueOf(i));
                Log.v("connexionReussie ? :'", String.valueOf(this.isConnected));
            }

            login curLogin = fr.baptabl.reuniut.login.getInstance(login, password);//on crée l'utilisateur

            Log.v("CONNECTÉ ?", "on teste le isConnected qui vaut :");
            Log.v("CONNECTÉ ?", String.valueOf(this.isConnected));
            if ( ( this.isConnected == false) || (curLogin == null) /* a décomenter lorsque on aura réussi à faire attendre la requête  || curLogin.getEmploi() == null || curLogin.getEmploi().montreEmploi() == null*/)//Si le constructeur a renvoyé une erreur
            {
                curLogin = null;
                b.setText("Se connecter");
                Log.v("MAUVAIS ?", "on teste le goodLogin qui vaut :");
                Log.v("MAUVAIS ?", String.valueOf(this.badLogin));
                if ( badLogin == true )
                    ThrowConnect.setText("Logins incorrects");
                else
                    ThrowConnect.setText("Connexion impossible");
            } else {
                Log.v("CONNECTÉ ?", "on a atteint le else");
                b.setText("Connecté");

                Intent newActivity = new Intent(ActivityLogin.this, ActivityMenu.class);

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


    /* ***********************************
                      CAS
    /* ***********************************
    (note : le CAS n'est plus une classe à part pour des raisons de thread parallèles. trop complexe à gérer pour la beta.)
    */


    private void setConnexionStatus(boolean etat) {
        Log.v("static connexionReussie (avant) :'", String.valueOf(this.isConnected));
        this.isConnected = etat;
        Log.v("static connexionReussie (apres) :'", String.valueOf(this.isConnected));
    }

    private void setMauvaisLogin(boolean etat) {
        Log.v("static connexionReussie (avant) :'", String.valueOf(this.badLogin));
        this.badLogin = etat;
        Log.v("static connexionReussie (apres) :'", String.valueOf(this.badLogin));
    }


    public static String getHTTP(String url){
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "NULL"; //Contenu de requête NULL

        } catch (Exception e) {
            // Log.d("INPUTSTREAM", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return getHTTP(urls[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            setMauvaisLogin(false);
            Log.v("test de CORRECT:'", result);
            int pos = result.indexOf("correct");
            boolean correct = Boolean.parseBoolean(null);
            if (pos >= 0) {
                correct = true;
            }
            if ( correct == true ) {//si connexion OK
                setConnexionStatus(true);
                Log.v("connexionReussie :'", String.valueOf(isConnected));
            }
            else {//sinon, est-ce que c'est une erreur de login ?
                pos = result.indexOf("mauvais");
                boolean mauvais = Boolean.parseBoolean(null);
                if (pos >= 0) {
                    mauvais = true;
                }
                if ( mauvais == true ) {//si connexion OK
                    setMauvaisLogin(true);
                    Log.v("connexionReussie :'", String.valueOf(isConnected));
                }
            }
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