package fr.baptabl.reuniut;

import java.util.Date;

/**
 * Created by jean on 20/11/14.
 */

public class Reunion {
	private Groupe essentiel;
	private Groupe optionnel;
	private Date date_min;
	private Date fin_max;
	private String sujet;
	private EnsCreneau creneaux_possibles;
	private Creneau creneau_valide;
	private boolean creneau_confirme;

	//Constructor 
	private Reunion(Groupe essentiel, Groupe optionnel, Date date_min, Date fin_max, String sujet, EnsCreneau creneaux_possibles, Creneau creneau_valide, boolean creneau_confirme){
		this.essentiel=essentiel;
		this.optionnel=optionnel;
		this.date_min=date_min;
		this.fin_max=fin_max;
		this.sujet=sujet;
		this.creneaux_possibles=creneaux_possibles;
		this.creneau_valide=creneau_valide;
		this.creneau_confirme=creneau_confirme;
	}

	//Getters and Setters
	private void setEssentiel(Groupe groupe){
		this.essentiel=groupe;
	}

	private void setOptionnel(Groupe groupe){
		this.essentiel=groupe;
	}

	private EnsCreneau trouveCreneau(){
		return null;
	}

	private boolean validerCreneau(){
		return true;
	}

	/*private EnsCreneau getLibre(EnsCreneau ens){


	}*/



}
