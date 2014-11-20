package fr.baptabl.reuniut;

/**
 * Created by jean on 20/11/14.
 */
public class UTCeen{
    //Attributes of Utceen
    private String nom;
    private String prenom;
    private String login;
    private String emploi;

    //Constructors
    private UTCeen(String nom,String prenom, String login, String emploi){
        super();
        this.nom=nom;
        this.prenom=prenom;
        this.login=login;
        this.emploi=emploi;
    }

    //Getters & Setters
    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(){
        this.prenom=prenom;
    }

    public String getLogin(){
        return login;
    }

    private void setLogin(String login){
        this.login=login;
    }

    public String getEmploi(){
        return emploi;
    }

    public void setEmploi(String emploi){
        this.emploi=emploi;
    }

    private void creer_emploi(){
    }
}
