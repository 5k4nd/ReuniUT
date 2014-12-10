package fr.baptabl.reuniut;

/**
 * Created by Quentin on 10/12/14.
 */


public class Lieu{
	private String nom;
	private String adresse;


	//Constructor
	private Lieu(String nom, String adresse){
		this.nom=nom;
		this.adresse=adresse;
	}

	//Getters and Setters
	private void setNom(String nom){
		this.nom=nom;
	}

	private void setAdresse(String adresse){
		this.adresse=adresse;
	}

	private String getNom(){
		return nom;
	}

	private String getAdresse(){
		return adresse;
	}

	private void CalculTemps(Lieu lieu){}
}