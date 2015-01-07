package fr.baptabl.reuniut;

import java.util.Iterator;
/**
 * Created by jean on 20/11/14.
 */
import java.util.LinkedList;

public class Groupe extends LinkedList<UTCeen>{
	private String nom;


	//Constructor
	public Groupe(String nom){
		this.nom=nom;
	}

	//Getters and Setters
	public String getNom(){
		return nom;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	public String montreGroupe()
	{
		Iterator<UTCeen> i= this.iterator();
		StringBuilder sb = new StringBuilder("");
		while(i.hasNext())
		{
			sb.append(i.next().montreUTCeen()+",");
		}
		return sb.toString();
	}

	//methods
	private void addMembre(UTCeen utceen){}
	private void delMembre(UTCeen utceen){}
	private void next(){}
	private boolean isDone(){return true;}

}
