package fr.baptabl.reuniut;

/**
 * Created by jean on 20/11/14.
 */
import java.util.LinkedList;

public class Groupe extends LinkedList{
    private String nom;
    private int id;
    private Groupe suivant;
    private Membre membres;
    private Membre premier;

    //Constructor
    private Groupe(String nom){
        this.nom=nom;
    }

    //Getters and Setters
    public String getNom(){
        return nom;
    }
    public int getId(){
        return id;
    }
    public void setNom(String nom){
        this.nom=nom;
    }
    public void setId(int id){
        this.id=id;
    }

    //methods
    private void addMembre(UTCeen utceen){}
    private void delMembre(UTCeen utceen){}
    private void next(){}
    private Membre MembreActuel(){return this.membres;}
    private boolean isDone(){return true;}

}
