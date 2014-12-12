package fr.baptabl.reuniut;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * Created by Quentin on 10/12/14.
 */

public class EnsCreneau extends LinkedList<Creneau> {

	public void printEnsCreneau()
	{
    	Iterator<Creneau> i= this.iterator();
    	while(i.hasNext())
    		i.next().printCreneau();
	}
	
}
