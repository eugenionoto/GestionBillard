import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.io.*;
import java.util.*;
import java.awt.*;



public class Gestionnaire {
	
		private Settings settings;
		private FenetrePersonnalisee f;
		private Fenetre f1;
		private Billard [] tables;
		private double total;
		
	


	Gestionnaire(){
		
		total = 0.0;
		
	}
	
	
	/**
	 * 
	 * @return la dete courante
	 */
	public static String getDate(){
//		créer la date d'aujour d'hui
		//trouvée dans le système
		Date date = new Date();
		
		//selectionne la langue
		//du système
		Locale langue = Locale.getDefault();
		
		//format d'affichage de la date
		DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, langue);
		
		return format.format(date);
	}
	/**
	 * 
	 * @return l'heure courante precise
	 */
		
	public static String getPreciseHour(){
		
//		creation du calendrier
		GregorianCalendar g = new GregorianCalendar();
		
		//récolte heure min et sec
		int hour = g.get(Calendar.HOUR_OF_DAY);
		int minute = g.get(Calendar.MINUTE);
		int second = g.get(Calendar.SECOND);
		
		String ret = String.valueOf(hour) + "h"+String.valueOf(minute)+"m"+String.valueOf(second);
		
		return ret;
		
	}
	
	/**
	 * 
	 * @return l'heure cournte
	 */
	
	public static String getHour(){
		
		//creation du calendrier
		GregorianCalendar g = new GregorianCalendar();
		
		//récolte heure min et sec
		int hour = g.get(Calendar.HOUR_OF_DAY);
		int minute = g.get(Calendar.MINUTE);
		int second = g.get(Calendar.SECOND);
		
		String ret = String.valueOf(hour) + "h"+String.valueOf(minute);
		
		return ret;
	}
	/**
	 * méthode qui sauvegarde les paramètres
	 */
	public void writeSettings(String url){
		try{
		File fichier= new File(url);
		ObjectOutputStream flotEcriture = 
            new ObjectOutputStream(
               new FileOutputStream(fichier));
		
		flotEcriture.writeObject(settings);
        flotEcriture.close();
		}
		catch(IOException e){
			
		}
	}
	/**
	 * méthode qui sauvegarde les données sur les tables
	 */
	public void writeTablesInFile(String url){
		try{
			PrintWriter ecrivain;
			
			ecrivain =  new PrintWriter(new BufferedWriter
					   (new FileWriter(url)));
			
			ecrivain.println(total);
			
			for (int i=0; i<getSettings().getNumTable();i++){
				ecrivain.println(getTables()[i].isOpened());
				ecrivain.println(getTables()[i].getFinalTime());
				ecrivain.println(getTables()[i].getCost());
				ecrivain.println(getTables()[i].isStandBy());
				ecrivain.println(getTables()[i].getInitialStandBy());
				ecrivain.println(getTables()[i].getTotalStandBy());
				
			}
			
			ecrivain.close();
			
		}
		catch(IOException e){
			
		}
	}

	/**
	 * méthode servant a charger les paramètres
	 * @param url
	 * @return
	 */
	public void readTablesFromFile(String url){
			
		BufferedReader lecteurAvecBuffer = null;
	    String ligne;
		
		try{
		    lecteurAvecBuffer = new BufferedReader
			  (new FileReader(url));
		    
		    ligne = lecteurAvecBuffer.readLine();
		    
		    setTotal(Double.parseDouble(ligne));
			
			 for(int i=0; i<getSettings().getNumTable();i++){
				
				 ligne = lecteurAvecBuffer.readLine();
				 getTables()[i].setOpened(Boolean.parseBoolean(ligne));
				 if(getTables()[i].isOpened()){
					 ligne = lecteurAvecBuffer.readLine();
					 getTables()[i].setFinalTime(ligne);
					 ligne = lecteurAvecBuffer.readLine();
					 getTables()[i].setCost(Double.parseDouble(ligne));
					 //pour le standby
					 ligne = lecteurAvecBuffer.readLine();
					 getTables()[i].setStandBy(Boolean.parseBoolean(ligne));
					 if(getTables()[i].isStandBy()){
						 ligne = lecteurAvecBuffer.readLine();
						 getTables()[i].setInitialStandBy(ligne);
						 ligne = lecteurAvecBuffer.readLine();
						 getTables()[i].setTotalStandBy(Long.parseLong(ligne));
					 	}
					 else{
						 ligne = lecteurAvecBuffer.readLine();
						 getTables()[i].setInitialStandBy(this.getPreciseHour());
						 ligne = lecteurAvecBuffer.readLine();
						 getTables()[i].setTotalStandBy(Long.parseLong(ligne));
						 
					 }
					 getTables()[i].openSpecial();
				 }
				 else{
					 ligne = lecteurAvecBuffer.readLine();
					 ligne = lecteurAvecBuffer.readLine();
						 ligne = lecteurAvecBuffer.readLine();
						 ligne = lecteurAvecBuffer.readLine();
						 ligne = lecteurAvecBuffer.readLine();
				 }
			}
		   
		}
		catch(IOException e){
			
		}		
	}
	
	/**
	 * methode qui ecrit l'ancien total
	 */
	public void writeTotalInFile (String url){
		try{
			PrintWriter ecrivain;
			
			ecrivain =  new PrintWriter(new BufferedWriter
					   (new FileWriter(url)));
			
			ecrivain.println(total);
			
			ecrivain.close();
			
		}
		catch(IOException e){
			
		}
	}
	/**
	 * methode qui lit l'ancien total
	 */
	public double readTotalFromFile(String url){
		BufferedReader lecteurAvecBuffer = null;
	    String ligne;
		
		try{
		    lecteurAvecBuffer = new BufferedReader
			  (new FileReader(url));
		    
		    ligne = lecteurAvecBuffer.readLine();
		    return (Double.parseDouble(ligne));
		}
		catch(IOException e){
			return 0;
		}
	}
	/**
	 * méthode servant a charger les paramètres
	 * @param url
	 * @return
	 */
	public Settings readSettings(String url){
		
		
		Settings temp = null;
		
		try{
			
			
			File fichier = new File(url);
			ObjectInputStream flotLecture = 
	             new ObjectInputStream(
	                new FileInputStream(fichier));
			Object lu = flotLecture.readObject();
			if (lu instanceof Settings) {
				temp = (Settings) lu;
			}		
		}
		
		catch(Exception e){
			
		}
		return temp;
	}
	
	
	/**
	 * getter et setter
	 * @return
	 */
	
	public FenetrePersonnalisee getF() {
		return f;
	}


	public void setF(FenetrePersonnalisee f) {
		this.f = f;
	}


	public Settings getSettings() {
		return settings;
	}


	public void setSettings(Settings settings) {
		this.settings = settings;
	}


	public Billard[] getTables() {
		return tables;
	}


	public void setTables(Billard[] tables) {
		this.tables = tables;
	}

	public double getTotal() {
		return total;
	}

	
	public Fenetre getF1() {
		return f1;
	}


	public void setF1(Fenetre f1) {
		this.f1 = f1;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	/**
	 * méthode main
	 * @param args
	 */
	public static void main(String[] args) {
		//test
		/*
		Billard b = new Billard(5);
		int i = 1;
		while (i != 0){
			System.out.println("entrez une valeur");
			System.out.println("1)---> date");
			System.out.println("2)---> heure");
			System.out.println("3)---> ouvrir");
			System.out.println("4)---> fermer");
			System.out.println("5)---> quitter");
			
			
			int choix = Support.readInt();
			
			switch(choix){
			case(1):	System.out.println(getDate());break;
			case(2):	System.out.println(getHour());break;
			case(3):	b.openTable();break;
			case(4):	b.closeTable();break;
			case(5):	i=0;break;
			default : 	;
			}
		}
		*/
		//création du nouveau gestionnaire
		Gestionnaire g = new Gestionnaire();
		
	//ecriture
		//g.settings  = new Settings(20, new Color(0,255,0),new Color(0,0,0), 15.00, 13.00, false, true);
		//g.writeSettings("settings.spk");
	
		//lecture
		//chargement des paramètres
		g.settings = g.readSettings("settings.spk");
		if(g.getSettings().isPersonalise()){
			g.getSettings().setNumTable(8);
		}
		//nombre de tables
		g.tables = new Billard [g.settings.getNumTable()];
		
		//cas ou il n'y a pas eut de problèmes dans le lancement précédant
		if(!g.getSettings().isProbleme()){

			//initialisation des tables de billard sans partie graphique

			//fenetre normale
			for(int i = 0; i<g.settings.getNumTable();i++){
				g.tables[i] = new Billard(i+1,g);
			}


			g.f = new FenetrePersonnalisee(g.settings.getNumTable(), g);
			g.f1 = new Fenetre(g.settings.getNumTable(), g);
			//g.getF().getSup().setBackground(Color.WHITE);
		}
		else{
			//g.readTablesFromFile("tables.dat");

			for(int i = 0; i<g.settings.getNumTable();i++){
				g.tables[i] = new Billard(i+1,g);
			}

			g.f = new FenetrePersonnalisee(g.settings.getNumTable(), g);
			g.f1 = new Fenetre(g.settings.getNumTable(), g);

			g.readTablesFromFile("tables.dat");

		}
		
		if(g.getSettings().isPersonalise()){
			g.getF1().setVisible(false);
		}
		else {
			g.getF().setVisible(false);
		}
	}
}
