package fr.baptabl.reuniut;

/**
 * Created by jean on 20/11/14.
 */

public class UTCeenManager {
	private UTCeen utceen;
	private UTCeen first;
	private UTCeenManager instanceUnique;

	//Constructor
	private UTCeenManager(UTCeen utceen, UTCeen first, UTCeenManager instanceUnique){
		this.utceen=utceen;
		this.first=first;
		this.instanceUnique=instanceUnique;
	}

	//Getters and Setters
	private UTCeen getFirst(){
		return first;
	}

	//methods
	private UTCeen donneInstance(){
		return null;
	}

	private void libererInstance(){}

	private UTCeen Next(){
		return null;
	}

	public boolean isDone(){
		return true;
	}

	private UTCeen UTCeenActuel(){
		return null;
	}

	private void ajoutUTCeen(String nom, String prenom, String login){
	} 

	//private UTCceen getUTCeen(String nom, String prenom, String login){return null;}


}
