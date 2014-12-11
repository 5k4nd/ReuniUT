package fr.baptabl.reuniut;

/**
 * Created by Quentin on 10/12/14.
 */


public class Emploi{
	private EnsCreneau journee[];

	private Emploi(EnsCreneau journee[]){
		this.journee=journee;
	}

    public EnsCreneau getJournee(int n){
        return journee[n];
    }
}
