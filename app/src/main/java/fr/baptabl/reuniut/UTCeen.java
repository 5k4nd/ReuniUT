package fr.baptabl.reuniut;

/**
 * Created by jean on 20/11/14.
 */
public class UTCeen{
    private String nom;
    private String prenom;
    private String login;
    private Emploi emploi;

    //Constructor
    private UTCeen(String nom,String prenom, String login, Emploi emploi){
        this.nom=nom;
        this.prenom=prenom;
        this.login=login;
        this.emploi=emploi;
    }

    //Getters & Setters
    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public String getLogin(){
        return login;
    }

    public void setUTCeenName(String nom, String prenom){
        this.nom=nom;
        this.prenom=prenom;
    }

    public void setUTCeenId(String login){
        this.login=login;
    }

    //methods
    private void creer_emploi(){
    }
}
