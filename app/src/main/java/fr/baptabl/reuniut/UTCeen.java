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
    private UTCeen(String nom,String prenom, String login, String edt){
        //Verification de l'existence, récupération de l'emploi du temps
        this.nom=nom;
        this.prenom=prenom;
        this.login=login;
        this.emploi = new Emploi(edt);
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

    public Emploi getEmploi(){
        return emploi;
    }

}
