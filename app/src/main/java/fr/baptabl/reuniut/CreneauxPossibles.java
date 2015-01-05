package fr.baptabl.reuniut;

/**
 * Created by Quentin on 10/12/14.
 */
import java.util.Calendar;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;

public class CreneauxPossibles extends Creneau{
    private int nbOptionnel;

    //Constructor 
    public CreneauxPossibles(int nbOptionnel, Date d, Date f){
        super(d, f);
    	this.nbOptionnel=nbOptionnel;
    }


    public int getNbOptionnel(){
    	return nbOptionnel;
    }

    public void setNbOptionnel(int nbOptionnnel){
    	this.nbOptionnel=nbOptionnel;
    }
   

}
