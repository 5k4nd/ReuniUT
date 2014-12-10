package fr.baptabl.reuniut;

/**
 * Created by Quentin on 10/12/14.
 */


public class Emploi{
	private EnsCreneau journee[]=new EnsCreneau[7];

	private Emploi(EnsCreneau journee[]){
		this.journee=journee;
	}

	private Emploi First(){
		return null;
	}

	private Emploi Next(){
		return null;
	}

	private boolean isDone(){
		return false;
	}

	private EnsCreneau getJournee(){
		return null;
	}
}