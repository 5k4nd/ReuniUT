package fr.baptabl.reuniut;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * Created by Quentin on 10/12/14.
 */

public class EnsCreneau extends LinkedList<Creneau> {

	public String montreEnsCreneau()
	{
    	Iterator<Creneau> i= this.iterator();
    	StringBuilder sb = new StringBuilder("");

    	while(i.hasNext())
    	{
    		sb.append(i.next().montreCreneau());
    	}
    	return sb.toString();
	}
}