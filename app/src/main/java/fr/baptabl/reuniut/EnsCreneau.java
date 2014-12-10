package fr.baptabl.reuniut;

/**
 * Created by Quentin on 10/12/14.
 */

public class EnsCreneau{
	private Creneau creneau_actuel;
	private Creneau first;


	//Constructor
	private EnsCreneau(Creneau creneau_actuel, Creneau first){
		this.creneau_actuel=creneau_actuel;
		this.first=first;
	}

	//Methods
	private Creneau First(){
		return null;
	}
	private Creneau Next(){
		return null;
	}

	private Creneau CreneauActuel(){
		return null;
	}

	private boolean isDone(){
		return false;
	}

	private void creerCreneau(){}

}
