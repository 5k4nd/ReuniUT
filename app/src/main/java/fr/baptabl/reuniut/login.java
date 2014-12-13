package fr.baptabl.reuniut;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bat on 13/12/14.
 */
//Note : Singleton pourrit ("complexifie") le contexte...
 //apparemment singleton (ne) permet ici (que) de limiter une instance par CLASSE mais alors qu'il nous faut limiter une instance EN TOUT (évidemment... !)

public class login {
    private static login Instance = null;
    public boolean isConnected;
    private String edt;
    Context curContext;


//en chantier
//Constructeur
    public login(Context c) { //ATTENTION, on récupère le contexte d'ActivityLogin
        curContext = c;
        isConnected = true;//TEMPORAIRE POUR LES TESTS tant que le CAS n'est pas opérationnel
        if (isConnected == true) {
            //toast "Connexion réussie";
        }
        else {
            //toast "Échec de connexion");
        }

    //getEdt
        new HttpAsyncTask().execute("http://bat.demic.eu/cas/EDT/abelbapt.edt");
        //http://bat.demic.eu/cas/EDT/abelbapt.edt //ou courbeje, qkeunebr
        //https://cas.utc.fr/cas/v1/tickets
    }

//getInstance()
    static public login getInstance(Context c) {// car besoin du contexte FIXME (conf note de haut de page)
        if (Instance == null) {
            Instance = new login(c);
        }
        return Instance;
    }

//getEdt()
    public String getEdt() { return this.edt; }

//getHTTP()
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
                result = "Contenu de requête NULL";

        } catch (Exception e) {
            Log.d("INPUTSTREAM", e.getLocalizedMessage());
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

    //IMPLÉMENTER LE TEST DE LOGIN CAS
    //du style getTicketDeTest
    //public boolean isConnected(login, password){} //isConnected est donc à prendre ici de "est connecté, potentiellement"
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) curContext.getSystemService(curContext.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return getHTTP(urls[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(curContext, "Emploi du temps récupéré", Toast.LENGTH_LONG).show();
            edt = result;
        }
    }
}
