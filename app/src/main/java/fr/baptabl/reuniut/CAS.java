package fr.baptabl.reuniut;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bat on 11/12/14.
 */

//DOIT ETRE SINGLETON
public class CAS {
    private static CAS Instance= null;
    public boolean EstIdentifie;
    private String login;
    private String password;

    private CAS() {
        this.EstIdentifie = false;
        //gérer les erreurs !
    }//C'est quoi l'intêret de faire un constructeur sans paramètres?

    private CAS(String login, String password) {
        this.login = login;
        this.password = password;
        this.EstIdentifie = true;//tant que le CAS n'est pas opérationnel
    }
    static public CAS getInstance(String login, String password)
    {
        if (Instance==null) {
            Instance = new CAS(login, password);
        }
        return Instance;
    }

    //HTTP-request
    String postData() throws Exception {
        String reponse = null;
        try {

            HttpClient myClient = new DefaultHttpClient();
            HttpPost myPost = new HttpPost("https://cas.utc.fr/cas/v1/tickets");

            //HttpResponse response = null;
            //String responseString = null;

            //POST
            List<NameValuePair> parametresPost = new ArrayList<NameValuePair>(2);
            parametresPost.add(new BasicNameValuePair("username", "abelbapt"));
            parametresPost.add(new BasicNameValuePair("password", "*******"));
            try {
                myPost.setEntity(new UrlEncodedFormEntity(parametresPost));
            } catch (UnsupportedEncodingException e) {
                // writing error to Log
                e.printStackTrace();
            }

            // Execute HTTP Post Request
            try {
                HttpResponse rep = myClient.execute(myPost);

                // writing response to log
                Log.d("Http Response:", rep.toString());
                //BufferedReader stringRep = new BufferedReader(new InputStreamReader(rep.getEntity().getContent()));
                //reponse = stringRep.readLine();

            } catch (ClientProtocolException e) {
                // writing exception to log
                e.printStackTrace();

            } catch (IOException e) {
                // writing exception to log
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
        //Log.v("REQUETE ", reponse);
        //reponse = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        return reponse;


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

    }

    public String getTicket() {
        return "null";
    }

    public String getTicket(String login, String password) {
        String ticket = "null";

        //String barbareno = postData();
        String barbareno = "123456";
        barbareno = barbareno.substring(1, 5);
        Log.v("HTTP", barbareno);

        ticket = "login : " + login + "et password : " + password;

        return ticket;
    }

    private String getEdt(String edt) {
        if (this.EstIdentifie == false)
            return "null";
        else {
            return "null";



        }
    }
}


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
