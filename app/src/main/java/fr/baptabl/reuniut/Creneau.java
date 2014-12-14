package fr.baptabl.reuniut;

//import java.util;


import java.util.Date;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;

/**
 * Created by Quentin on 10/12/14.
 */

public class Creneau implements java.lang.Comparable<Creneau>{
	private Date debut;
	private Date fin;
	private String activite;
	private Lieu lieu;

	
	//Comparateur
	public int compareTo(Creneau other) { 
	      if (this.debut.getTime() < other.getDebut().getTime())  return -1; 
	      else if(this.debut.getTime() == other.getDebut().getTime()) return 0; 
	      else return 1; 
	   } 
	//Constructor
	public Creneau(Date d, Date f, Lieu l){
		this.debut=d;
		this.fin=f;
		this.lieu=l;
	}

    public Creneau(Date d, Date f){
        this.debut=d;
        this.fin=f;
    }
    public String montreCreneau()
    {
        String d;
        String f;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//On définit un format de date
        d = df.format(debut);
        f = df.format(fin);
        return "Début à "+d+", et fin à "+f+"\n";
    }
    public Creneau(String emploi)
    {
    	try
		{
        int n=0;
        String[] heures=new String[2];
    	Pattern pattern = Pattern.compile("[0-9]{1,2}:[0-9]{2}");
    	Matcher match = pattern.matcher(emploi);//On parse le string avec la Regex
    	while(match.find() && n<2){
    		 heures[n]=match.group();//On stocke les string obtenus
    		 n++;
    		 }
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm");//On définit un format de date
    	this.debut = df.parse(heures[0]);//On parse les dates
    	this.fin = df.parse(heures[1]);

    	
		}
    	catch (ParseException ex)
		{
    		ex.printStackTrace();
		}
    }

	//Getters
	public Date getDebut(){
		return debut;
	}

	public Date getFin(){
		return fin;
	}

	public Lieu getLieu(){
		return lieu;
	}
	public void setDebut(Date d){
		debut=d;
	}
	public void setFin(Date f){
		fin=f;
	}
	public void  printCreneau()
	{
		System.out.println("Début : "+debut.toString()+" ------ Fin : "+fin.toString());
	}



}
