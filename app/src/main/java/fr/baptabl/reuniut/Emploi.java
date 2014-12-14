package fr.baptabl.reuniut;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Iterator;
/**
 * Created by Quentin on 10/12/14.
 */


public class Emploi {
	private EnsCreneau[] journee;

	private Emploi(EnsCreneau journee[]){
		this.journee=journee;
	}
	


	public Emploi(String edt)
	{
			journee=new EnsCreneau[7];
			journee[0]=new EnsCreneau();//On initialise le dimanche vide
			int n=1;
			String[] jour = new String[] {"LUNDI...", "MARDI...", "MERCREDI", "JEUDI...", "VENDREDI", "SAMEDI.." };
			for(String j : jour)
			{
				journee[n]=new EnsCreneau();
				Pattern pattern = Pattern.compile("("+j+"){1}[A-Z0-9-:,= ]{24}");
				Matcher match = pattern.matcher(edt);//On parse le string avec la Regex
				while(match.find()){
	    			journee[n].add(new Creneau(match.group()));//On ajoute un creneau à la liste du jour
	    			//System.out.println(match.group());//On teste
	    		 }
				Collections.sort(journee[n]);//On trie la liste
				n++;
			}
	}
    public EnsCreneau getJournee(int n){
        return journee[n];
    }
    public void printJournee(int n)
    {
    	journee[n].printEnsCreneau();
    }


}
