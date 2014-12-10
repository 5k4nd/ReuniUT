package fr.baptabl.reuniut;

import java.util;

/**
 * Created by Quentin on 10/12/14.
 */


public class Creneau{
	private Date debut;
	private Date fin;
	private Lieu lieu;

	//Constructor
	private Creneau(Date debut, Date fin, Lieu lieu){
		this.debut=debut;
		this.fin=fin;
		this.lieu=lieu;
	}

	//Getters
	private Date getDebut(){
		return debut;
	}

	private Date getFin(){
		return fin;
	}

	private Lieu getLieu(){
		return lieu;
	}

}