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


public class login extends UTCeen{
    private static login Instance = null;
    Context curContext;
    private String mdp;


    //en chantier
//Constructeur
    private login(String login, String mdp) {
        super(login, mdp);//On fait appel au constructeur de UTCeen
        this.mdp=mdp;

    }

    //getInstance()
    static public login getInstance(String login, String mdp) {
        if (Instance == null) {
            Instance = new login(login, mdp);
        }
        return Instance;
    }
    static public login getInstance() {//Accesseur "classique" de l'objet
        return Instance;
    }
    static public boolean connexion(String login, String mdp) {// test de connexion
        return true; //En attendant le test
    }


    public String getLogin(){return super.getLogin();}
    public String getMdp(){return mdp;}

}