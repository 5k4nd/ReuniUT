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

    public String montreCreneau()
    {
        String d;
        String f;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM HH:mm");//On définit un format de date
        d = df.format(debut);
        f = df.format(fin);
        return "Début à "+d+", et fin à "+f+", nombre 'o : "+nbOptionnel+"\n";
    }
    public int getNbOptionnel(){
    	return nbOptionnel;
    }
    public void incOpt()
    {
        nbOptionnel++;
    }

    public void setNbOptionnel(int nb){
    	this.nbOptionnel=nb;
    }
   

}
