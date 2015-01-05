package fr.baptabl.reuniut;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bat on 11/12/14.
 */

//cette classe n'est actuellement plus utilisée . elle est intégrée à ActivityLogin pour des raisons de gestion de thread, trop complexe à gérer pour la beta.


public class CAS {
    private static CAS Instance = null;

    //private String login;
    //private String password;
    public boolean isConnected;

    public CAS() {
        this.isConnected = false;
        new HttpAsyncTask().execute("http://bat.demic.eu/cas/test.php");
    }


    private void setConnexionStatus(boolean etat) {
        Log.v("static connexionReussie (avant) :'", String.valueOf(this.isConnected));
        this.isConnected = etat;
        Log.v("static connexionReussie (apres) :'", String.valueOf(this.isConnected));
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
            Log.v("test de CORRECT:'", result);
            int pos = result.indexOf("correct");
            boolean correct = Boolean.parseBoolean(null);
            if (pos >= 0) {
                correct = true;
            }
            if ( correct == true ) {
                setConnexionStatus(true);
                Log.v("connexionReussie :'", String.valueOf(isConnected));
            }
        }
    }

    // à réintégrer dans la V2
    /*
    public boolean isConnected(Context c, String login, String password) {
        ConnectivityManager connMgr = (ConnectivityManager) c.getSystemService(c.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    */


}
