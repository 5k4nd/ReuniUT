package fr.baptabl.reuniut;

/**
 * Created by bat on 13/12/14.
 */

public class login extends UTCeen{
    private static login Instance = null;
    //Context curContext;
    private String mdp;


    //en chantier
//Constructeur
    private login(String login, String mdp) {
        super(login);//On fait appel au constructeur de UTCeen
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
    static public boolean connexion() {// test de connexion
        return true; //En attendant le test
    }


    public String getLogin(){return super.getLogin();}
    public String getMdp(){return mdp;}

}