package fr.baptabl.reuniut;

/**
 * Created by Quentin on 10/12/14.
 */


public class GroupeManager{
	private GroupeManager instanceUnique;
	private Groupe groupes;
	private Groupe first;

	private GroupeManager(GroupeManager instanceUnique, Groupe groupes, Groupe first){
		this.instanceUnique=instanceUnique;
		this.groupes=groupes;
		this.first=first;
	}

	private GroupeManager donneInstance(){
		return null;
	}

	private void libereInstance(){}

	private Groupe first(){
		return null;
	}

	private Groupe next(){
		return null;
	}

	private boolean isDone(){
		return true;
	}

	private Groupe groupeActuel(){
		return null;
	}

	private void creerGroupe(String nom){
	}
}