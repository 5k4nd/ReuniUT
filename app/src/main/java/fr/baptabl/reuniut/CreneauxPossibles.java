package fr.baptabl.reuniut;

/**
 * Created by Quentin on 10/12/14.
 */

public class CreneauxPossibles{
    private int nbOptionnel;

    //Constructor 
    private CreneauxPossibles(int nbOptionnel){
    	this.nbOptionnel=nbOptionnel;
    }

    public int getNbOptionnel(){
    	return nbOptionnel;
    }

    public void setNbOptionnel(int nbOptionnnel){
    	this.nbOptionnel=nbOptionnel;
    }
   

}
