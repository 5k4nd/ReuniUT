package fr.baptabl.reuniut;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private EnsCreneau creneaux_possibles;
	private Creneau creneau_valide;
	private boolean creneau_confirme;

	//Constructor 
	public Reunion(Groupe essentiel, Groupe optionnel, Date date_min, Date fin_max, String sujet){
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
		ListIterator<Creneau> i = ens.listIterator(0);
		while (i.hasNext() && ens.get(i.nextIndex()).getFin().getTime()<d.getTime())
			/*Tant que les créneaux se finnissent avant que la plage ne commence*/
		{
			i.next();
			i.remove();//On supprime l'élèment
		}
		while (i.hasNext() && ens.get(i.nextIndex()).getDebut().getTime()<f.getTime())
			/*Tant que  les créneaux commencent avant la fin*/
		{
			if(ens.get(i.nextIndex()).getDebut().getTime()-d.getTime()>duree)
				/*Si la différence entre le début du créneau suivant et le début de la plage horraire restante est suffisament grand*/
			{
				
				ens.get(i.nextIndex());
				e=d;
				d=ens.get(i.nextIndex()).getFin();//Le début de la plage horraire prend comme valeur la fin du créneau
				Date m=min(ens.get(i.nextIndex()).getDebut(),f);
				ens.get(i.nextIndex()).setFin(min(ens.get(i.nextIndex()).getDebut(),f));//La fin du créneau prend comme valeur le min entre le début du creneau et la fin
				ens.get(i.nextIndex());
				ens.get(i.nextIndex()).setDebut(e);//Le début du créneau prend comme valeur le début de la plage horraire
				i.next();
			}
			else
			{
				d=ens.get(i.nextIndex()).getFin();
				i.next();
				i.remove();//On avance et supprime

			}
		}
		while(i.hasNext())//On supprime ce qui reste
		{
			i.next();
			i.remove();
		}
		if(f.getTime()-d.getTime()>=duree)
		{
			ens.addLast(new Creneau(d, f));
		}

		return ens;

	}


}