package fr.baptabl.reuniut;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bat on 06/01/15.
 */

public class ActivityVueReunion extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private /*CreneauxPossibles*/ String[] creneaux = {"creneau1","creneau2", "creneau3"}; /*null;*/
    private String reunionName = null;
    private String fromLogin = null;
    private String creneauFinal = null;
    private String participants = null;

    private Spinner fieldSpinner = null ;
    private Button buttMail = null;
    private TextView infoMail = null;
    private Button buttFermer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        fromLogin = login.getInstance().getLogin();
        reunionName = i.getStringExtra("reunionName");
        Reunion curReu = login.getInstance().getReunion(reunionName);

        participants = curReu.getMembres();

        String[] creneaux = curReu.getCreneaux();
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, creneaux);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        setContentView(R.layout.activity_vue_reunion);

        fieldSpinner = (Spinner) findViewById(R.id.fieldSpinner);
        fieldSpinner.setAdapter(adapter_state);
        fieldSpinner.setOnItemSelectedListener(this);

        //pour la V2. les boutons ci-dessous ne s'affichent pas tant que le spinner n'a pas été utilisé.
        buttMail = (Button) findViewById(R.id.buttMail);
        buttMail.setOnClickListener(this);
        infoMail = (TextView) findViewById(R.id.infoMail);

        buttFermer = (Button) findViewById(R.id.buttFermer);
        buttFermer.setOnClickListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        fieldSpinner.setSelection(position);
        String selState = (String) fieldSpinner.getSelectedItem();
        creneauFinal = selState;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttMail:
            //send mail
                String GETrequest = "http://bat.demic.eu/cas/mail_push.php?fromlogin="+fromLogin+"&creneau="+creneauFinal+"&participants="+participants;
                GETrequest = GETrequest.replace(" ","%20");
                GETrequest = GETrequest.replace("à","%C3%A0");
                new HttpAsyncTask().execute(GETrequest);
                Log.v("on envoie : '", GETrequest+"'");
                infoMail.setText("La notification push-mail a été envoyée à tous vos collègues.");
            break;

            case R.id.buttFermer:
                System.exit(0);
            break;
        }
    }

    //requêtes HTTP
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
            //pas besoin d'un quelconque retour cette fois-ci.
        }
    }



}