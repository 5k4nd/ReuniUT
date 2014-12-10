package fr.baptabl.reuniut;

/**
 * Created by bat on 09/12/14.
 */
public class Membre {
    private UTCeen id;
    private Membre suivant;

    //Constructor
    private Membre(UTCeen id, Membre suivant){
    	this.id=id;
    	this.suivant=suivant;
    }

    public UTCeen getId(){return null;}

}
