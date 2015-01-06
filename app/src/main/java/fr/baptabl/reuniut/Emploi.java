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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
/**
 * Created by Quentin on 10/12/14.
 */


public class Emploi {
	private EnsCreneau[] journee;


	public String montreEmploi()
	{

		int i;
		StringBuilder sb = new StringBuilder("");
		String[] jour = new String[] {"DIMANCHE", "LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI", "SAMEDI" };
		for(i=1; i< this.journee.length; i++)
		{
			sb.append(jour[i]+" : \n"+journee[i].montreEnsCreneau());
		}
		return sb.toString();

	}

    public Emploi(String login)
    {
        Log.v("HTTP REQUEST", "on envoie le login: "+login);
        //Tests de CAS implémentés. s'effectuent au login
        new HttpAsyncTask().execute("http://bat.demic.eu/cas/EDT/"+login+".edt");
        Log.v("HTTP REQUEST", "atteint");

    }
    public Emploi(String login, String Ulogin, String Umdp)//Constructeur pour les autres UTCeens
    {
        //Tests de CAS implémentés. s'effectuent au login
        new HttpAsyncTask().execute("http://bat.demic.eu/cas/EDT/"+login+".edt");

    }
	private void Construire_emploi(String edt)
	{
        Log.v("CONSTRUIRE", "atteint");
        journee = new EnsCreneau[7];
		this.journee[0]=new EnsCreneau();//On initialise le dimanche vide
		int n=1;
		String[] jour = new String[] {"LUNDI...", "MARDI...", "MERCREDI", "JEUDI...", "VENDREDI", "SAMEDI.." };
		for(String j : jour)
		{
			this.journee[n]=new EnsCreneau();
			Pattern pattern = Pattern.compile("("+j+"){1}[A-Z0-9-:,= ]{24}");
			Matcher match = pattern.matcher(edt);//On parse le string avec la Regex
			while(match.find()){
				this.journee[n].add(new Creneau(match.group()));//On ajoute un creneau à la liste du jour
				//System.out.println(match.group());//On teste
			}
            int k= this.journee.length;
			Collections.sort(this.journee[n]);//On trie la liste
			n++;
		}
	}
	public EnsCreneau getJournee(int n){
		return journee[n];
	}
    public EnsCreneau[] getJournee(){
        return journee;
    }

    //Fonctions d'accès aux edts
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
            Construire_emploi(result);//On construit l'emploi du temps ici
        }
    }

}
