package fr.baptabl.reuniut;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by bat on 11/12/14.
 */
/* À IMPLÉMENTER :
- CAS doit être singleton
- ...
 */

public class CAS {
    private static CAS Instance = null;
    public boolean EstIdentifie;
    private String login;
    private String password;

    private CAS(String login, String password) {
        this.login = login;
        this.password = password;
        this.EstIdentifie = true;//le temps des tests
    }

    static public CAS getInstance(String login, String password) {
        if (Instance==null) {
            Instance = new CAS(login, password);
        }
        return Instance;
    }


    public String getTicket() {
        return getTicket(this.login, this.password);
    }

    /*
    public String getTicket(String login, String password) {
        String getData() throws Exception {
            String reponse = null;

        //AUTH
            String ticket = this.getTicket(this.login, this.password);

            try {
            HttpClient myClient = new DefaultHttpClient();
            HttpPost myPost = new HttpPost("https://cas.utc.fr/cas/v1/tickets");

            //HttpResponse response = null;
            //String responseString = null;

        //POST
            //List<NameValuePair> parametresPost = new ArrayList<NameValuePair>(2);
            //parametresPost.add(new BasicNameValuePair("username", ""));
            //parametresPost.add(new BasicNameValuePair("password", ""));
            //try {
            //    myPost.setEntity(new UrlEncodedFormEntity(parametresPost));
            //} catch (UnsupportedEncodingException e) {
                // writing error to Log
            //Log.e("Encodage", "I got an error", e);
            //}

        // Execute HTTP Post Request
            try {
                HttpResponse rep = myClient.execute(myPost);
                //BufferedReader stringRep = new BufferedReader(new InputStreamReader(rep.getEntity().getContent()));
                //reponse = stringRep.readLine();
            } catch (ClientProtocolException e) { Log.v("Requete HTTP", "Exception Protocole Client");
            } catch (IOException e) { Log.v("Requete HTTP", "IOException...");
            }
            } catch (Exception e) {
            Log.v("HTTP-request", "UNE ERREUR EST SURVENUE SUR L'ENSEMBLE");
        }

            //reponse = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
            Log.v("REQUETE ", reponse);
            return reponse;
        }
    }
    */
//À suppprimer, je garde pour les tests
    public String getTicket(String login, String password) {
        String ticket = "null";

        //String barbareno = getData();
        String barbareno = "123456";
        barbareno = barbareno.substring(1, 5);
        Log.v("HTTP", barbareno);

        ticket = "login : " + login + "\net password : " + password;

        if (ticket != "null")
            this.EstIdentifie = true;//tant que le CAS n'est pas opérationnel // À IMPLÉMENTER
        return ticket;

    }

    private String getEdt(String edt) {
        if (this.EstIdentifie == false)
            return "null";
        else {
            //return edt = getData().parserDeStringAimplementer;
            return "null";

        }
    }

//****************
//    HTTP
//****************
    String getData() throws Exception {

        HttpClient myClient = new DefaultHttpClient();
        HttpPost myPost = new HttpPost("http://bat.demic.eu/cas/EDT/abelbapt.edt");

        HttpResponse rep = myClient.execute(myPost);
        BufferedReader stringRep = new BufferedReader(new InputStreamReader(rep.getEntity().getContent()));
        String reponse = stringRep.readLine();
        Log.v("REQUETE ", reponse);
        return reponse;
    }
}



//***************
//  BROUILLON
//***************
/*
            response = httpclient.execute(URL);
            //HttpResponse To String
        private String inputStreamToString(InputStream is) {
            String s = "";
            String line = "";

            // Wrap a BufferedReader around the InputStream
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            // Read response until the end
            while ((line = rd.readLine()) != null) { s += line; }

            // Return full string
            return s;
*/



/*
public class CAS extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}
*/
