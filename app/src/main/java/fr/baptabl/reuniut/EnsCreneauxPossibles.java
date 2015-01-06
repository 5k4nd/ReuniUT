package fr.baptabl.reuniut;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * Created by Quentin on 10/12/14.
 */

public class EnsCreneauxPossibles extends LinkedList<CreneauxPossibles> {

    public String montreEnsCreneau()
    {
        Iterator<CreneauxPossibles> i= this.iterator();
        StringBuilder sb = new StringBuilder("");

        while(i.hasNext())
        {
            sb.append(i.next().montreCreneau());
        }
        return sb.toString();
    }
    public EnsCreneauxPossibles(EnsCreneauxPossibles c)
    {
        super(c);
    }
    public EnsCreneauxPossibles()
    {
        super();
    }
}