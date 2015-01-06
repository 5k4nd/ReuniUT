package fr.baptabl.reuniut;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;






/**
 * Created by jean on 20/11/14.
 */

public class Reunion {
	private Groupe essentiel;
	private Groupe optionnel;
	private Date date_min;
	private Date fin_max;
	private long duree;
	private String sujet;
    private String description;
	private EnsCreneauxPossibles creneaux_possibles;
	private Creneau creneau_valide;
	private boolean creneau_confirme;

	//Constructor 
	public Reunion(Groupe essentiel, Groupe optionnel, Date date_min, Date fin_max, String sujet, String description){
        this.description=description;
		this.essentiel=essentiel;
		this.optionnel=optionnel;
		this.date_min=date_min;
		this.fin_max=fin_max;
		this.sujet=sujet;

	}
	public Reunion(Date dmin, Date fmax, long d)//La duréee est à entrer en seconde
	{
		this.date_min = dmin;
		this.fin_max=fmax;
		this.duree=d;
	}

	//Getters and Setters
	public Date getMin()
	{
		return date_min;
	}
	public Date getMax()
	{
		return fin_max;
	}
	public long getDuree()
	{
		return duree;
	}
	public Groupe getEssentiel()
	{
		return essentiel;
	}
	public void setEssentiel(Groupe groupe){
		this.essentiel=groupe;
	}

	private void setOptionnel(Groupe groupe){
		this.essentiel=groupe;
	}
	public EnsCreneauxPossibles getPossibles()
	{
		return creneaux_possibles;
	}

	private boolean validerCreneau(){
		return true;
	}

	static private Date ConversionDateEnHeure(Date date)//Fonction de "neutralisation" de la Date
	{
		Date heure;
		try{
			SimpleDateFormat df =new SimpleDateFormat("HH:mm");
			String d = df.format(date);
			heure = df.parse(d);
			return heure;
		}
		catch (ParseException ex)
		{
			ex.printStackTrace();
		}
		return null;

	}
	static public Date min(Date d1, Date d2)
	{
		if(d2.getTime()<d1.getTime())
			return d2;
		else
			return d1;
	}
	@SuppressWarnings("deprecation")
	private EnsCreneau getDefaut()
	{
		//tambouille à la con pour avoir des dates qui correspondent aux heures des limites
		Date d=date_min;
		Date f=fin_max;
		Calendar c= Calendar.getInstance();
		Calendar c1=Calendar.getInstance();
		c1.setTime(f);
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
		f=c.getTime();


		EnsCreneau e =new EnsCreneau();
		while(d.getTime()< fin_max.getTime())
		{


			e.add(new Creneau(d,f));
			//Incrementation des dates
			c.setTime(d);
			c.add(Calendar.DATE, 1);
			d= c.getTime();
			c.setTime(f);
			c.add(Calendar.DATE, 1);
			f=c.getTime();
		}
		return e;

	}
	public EnsCreneau getLibre(EnsCreneau ens)
	{
		Date d= this.ConversionDateEnHeure(date_min);
		Date f= this.ConversionDateEnHeure(fin_max);
		Date e;
		EnsCreneau c = new EnsCreneau();
		ListIterator<Creneau> i = ens.listIterator(0);
		while (i.hasNext() && ens.get(i.nextIndex()).getFin().getTime()<=d.getTime())
			/*Tant que les créneaux se finnissent avant que la plage ne commence*/
		{
			i.next();//On incrémente
		}
		while (i.hasNext() && ens.get(i.nextIndex()).getDebut().getTime()<f.getTime())
			/*Tant que  les créneaux commencent avant la fin*/
		{
			//System.out.println(ens.get(i.nextIndex()).montreCreneau());
			if(ens.get(i.nextIndex()).getDebut().getTime()-d.getTime()>=duree)
				/*Si la différence entre le début du créneau suivant et le début de la plage horraire restante est suffisament grand*/
			{

				
				//ens.get(i.nextIndex()).setFin(min(ens.get(i.nextIndex()).getDebut(),f));//La fin du créneau prend comme valeur le min entre le début du creneau et la fin
				//ens.get(i.nextIndex());
				//ens.get(i.nextIndex()).setDebut(e);//Le début du créneau prend comme valeur le début de la plage horraire
				c.add(new Creneau(d, min(ens.get(i.nextIndex()).getDebut(),f)));

			}

			d=ens.get(i.nextIndex()).getFin();//Le début de la plage horraire prend comme valeur la fin du créneau
			i.next();
		}
		while(i.hasNext())//On supprime ce qui reste
		{
			i.next();
			i.remove();
		}
		if(f.getTime()-d.getTime()>=duree)
		{
			c.addLast(new Creneau(d, f));
		}

		return c;

	}
	@SuppressWarnings("deprecation")
	public void CreneauCommun(EnsCreneau ens, UTCeen U)
	{
		ListIterator<Creneau> i = ens.listIterator(0);
		ListIterator<Creneau> u;
		EnsCreneau e;
		Calendar c= Calendar.getInstance();
		Calendar c1= Calendar.getInstance();
		Date d, f;
		Creneau Cr;
		while(i.hasNext())//On parcourt les créneaux possibles
		{


			e= getLibre(U.getEmploi().getJournee(ens.get(i.nextIndex()).getJour()-1)); //Les espaces libres de la journée de l'UTCeen
			u=e.listIterator();
			while(u.hasNext() && ConversionDateEnHeure(e.get(u.nextIndex()).getFin()).getTime()<ConversionDateEnHeure(ens.get(i.nextIndex()).getDebut()).getTime())
				//Tant que la fin de l'espace est inférieure au début du créneau
			{
				u.next();//On parcourt

			}
			
			while(u.hasNext() && ConversionDateEnHeure(ens.get(i.nextIndex()).getFin()).getTime()-ConversionDateEnHeure(e.get(u.nextIndex()).getDebut()).getTime()>=this.duree)
				//Tant que le début la fin du créneau moins la fin de l'espace est supérieur à la durée
			{
				//On insère un créneau ayant pour début le max entre les deux débuts et pour fin le min entre les deux fins
				d=ens.get(i.nextIndex()).getDebut();
				if(ConversionDateEnHeure(e.get(u.nextIndex()).getDebut()).getTime() > ConversionDateEnHeure(d).getTime())
				{
					c.setTime(d);
					c1.setTime(e.get(u.nextIndex()).getDebut());
					c.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
					c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
					d=c.getTime();
				}
				f=ens.get(i.nextIndex()).getFin();
				if(ConversionDateEnHeure(e.get(u.nextIndex()).getFin()).getTime()<ConversionDateEnHeure(f).getTime())
				{
					c.setTime(f);
					c1.setTime(e.get(u.nextIndex()).getFin());
					c.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
					c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
					f=c.getTime();
				}
				Cr=new Creneau(d,f);
				
				i.add(Cr);
				u.next();
			}
			//On supprime le créneau originel
			i.next();
			i.remove();
			

		}

	}
	public void creneauxEssentiels(EnsCreneau ens)
	{
		Iterator<UTCeen> i= this.essentiel.iterator();

		while(i.hasNext())//Pour tous les UTCeens du groupe essentiel
		{
			CreneauCommun(ens, i.next());
		}

	}
    private EnsCreneauxPossibles creationCreneauxAcc(EnsCreneau ens)
    {
        ListIterator<UTCeen> it= this.optionnel.listIterator(0);
        ListIterator<Creneau> i = ens.listIterator(0);
        Calendar c= Calendar.getInstance();
        Calendar c1= Calendar.getInstance();
        Date d, f;
        ListIterator<Creneau> u;
        EnsCreneau e;
        EnsCreneauxPossibles ret=new EnsCreneauxPossibles();
        CreneauxPossibles Cr;
        while(it.hasNext())//Pour tous les UTCeens du groupe optionnel
        {
            while(i.hasNext())//On parcourt les créneaux possibles
            {


                e = getLibre(this.optionnel.get(it.nextIndex()).getEmploi().getJournee(ens.get(i.nextIndex()).getJour() - 1)); //Les espaces libres de la journée de l'UTCeen
                u = e.listIterator();
                while (u.hasNext() && ConversionDateEnHeure(e.get(u.nextIndex()).getFin()).getTime() < ConversionDateEnHeure(ens.get(i.nextIndex()).getDebut()).getTime())
                //Tant que la fin de l'espace est inférieure au début du créneau
                {
                    u.next();//On parcourt

                }
                while(u.hasNext() && ConversionDateEnHeure(ens.get(i.nextIndex()).getFin()).getTime()-ConversionDateEnHeure(e.get(u.nextIndex()).getDebut()).getTime()>=this.duree)
                //Tant que le début la fin du créneau moins la fin de l'espace est supérieur à la durée
                {
                    //On insère un créneau ayant pour début le max entre les deux débuts et pour fin le min entre les deux fins
                    d=ens.get(i.nextIndex()).getDebut();
                    if(ConversionDateEnHeure(e.get(u.nextIndex()).getDebut()).getTime() > ConversionDateEnHeure(d).getTime())
                    {
                        c.setTime(d);
                        c1.setTime(e.get(u.nextIndex()).getDebut());
                        c.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
                        c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
                        d=c.getTime();
                    }
                    f=ens.get(i.nextIndex()).getFin();
                    if(ConversionDateEnHeure(e.get(u.nextIndex()).getFin()).getTime()<ConversionDateEnHeure(f).getTime())
                    {
                        c.setTime(f);
                        c1.setTime(e.get(u.nextIndex()).getFin());
                        c.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
                        c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
                        f=c.getTime();
                    }
                    Cr=new CreneauxPossibles(0, d,f);
                    ret.add(Cr);
                    u.next();
                }

                i.next();
            }
            it.next();

        }
        return ret;
    }
    private void compteOptionnel(EnsCreneauxPossibles ens) {
        ListIterator<UTCeen> it = this.optionnel.listIterator(0);
        ListIterator<CreneauxPossibles> i = ens.listIterator(0);
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        Date d, f;
        ListIterator<Creneau> u;
        EnsCreneau e;
        while (i.hasNext())//On parcourt les créneaux possibles
        {
            ens.get(i.nextIndex()).setNbOptionnel(0);//On remet le compteur à zéro
            while(it.hasNext())//Pour tous les UTCeens du groupe optionnel
            {


                e = getLibre(this.optionnel.get(it.nextIndex()).getEmploi().getJournee(ens.get(i.nextIndex()).getJour() - 1)); //Les espaces libres de la journée de l'UTCeen
                u = e.listIterator();
                while (u.hasNext() && ConversionDateEnHeure(e.get(u.nextIndex()).getFin()).getTime() < ConversionDateEnHeure(ens.get(i.nextIndex()).getDebut()).getTime())
                //Tant que la fin de l'espace est inférieure au début du créneau
                {
                    u.next();//On parcourt
                }
                if (u.hasNext() && ConversionDateEnHeure(e.get(u.nextIndex()).getDebut()).getTime()>=ConversionDateEnHeure(ens.get(i.nextIndex()).getDebut()).getTime() && ConversionDateEnHeure(e.get(u.nextIndex()).getFin()).getTime() <= ConversionDateEnHeure(ens.get(i.nextIndex()).getDebut()).getTime())
                //Si le début de l'espace est inférieur ou égal au début du créneau et que la fin de l'espace est supérieur ou égal à la fin du créneau, donc si le créneau est libre pour l'étudiant
                {
                    //On incrémente le nombre de participants
                    ens.get(i.nextIndex()).incOpt();
                }
                it.next();
            }
            i.next();
        }
    }
	public void setPossible()
	{
		EnsCreneau e= getDefaut();
		creneauxEssentiels(e);
        creneaux_possibles=creationCreneauxAcc(e);
        compteOptionnel(creneaux_possibles);

	}


}