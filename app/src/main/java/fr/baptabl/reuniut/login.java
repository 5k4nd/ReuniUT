package fr.baptabl.reuniut;

import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.lang.String;
/**
 * Created by bat on 13/12/14.
 */

public class login extends UTCeen{
    private static login Instance = null;
    //Context curContext;
    private String mdp;
    private LinkedList<Groupe> groupes;


    //en chantier
//Constructeur
    private login(String login, String mdp) {
        super(login);//On fait appel au constructeur de UTCeen
        this.mdp=mdp;
        groupes= new LinkedList<Groupe>();

    }
    public void addGroupe(String nom, String membres)
    {
        Groupe g= new Groupe(nom);
        String m[]=membres.split(",");
        for (int i = 0; i < m.length; i++)
        {
            m[i]=m[i].replace(" ", "");
            g.add(new UTCeen(m[i]));
        }
        groupes.add(g);

    }
    public LinkedList<Groupe> getGroupe()
    {
        return groupes;
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
    static public boolean connexion() {// test de connexion
        return true; //En attendant le test
    }


    public String getLogin(){return super.getLogin();}
    public String getMdp(){return mdp;}

}