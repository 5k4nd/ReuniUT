package fr.baptabl.reuniut;

/**
 * Created by jean on 20/11/14.
 */
import java.util.LinkedList;
// ligne de test à supprimer !
public class Groupe extends LinkedList{
    private String nom;
    private int id;
    private Groupe suivant;
    private Membre membres;
    private Membre premier;

    private Groupe(String nom){
        this.nom=nom;
    }

    private void addMembre(UTCeen utceen){}
    private void delMembre(UTCeen utceen){}
    private void next(){}
    private Membre MembreActuel(){return this.membres;}
    private boolean isDone(){return true;}

    



}
