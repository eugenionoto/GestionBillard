import sun.net.www.http.Hurryable;





public class Billard {
	
	private int number;
	private boolean opened;
	private double cost;
	private String initialTime;
	private String finalTime;
	private long playedTime;
	private double pay;
	private RepetAction tim;
	private BillardGUI [] tab = new BillardGUI [10];
	//pour le stand by
	private BillardButtonGUI bouton;
	private Gestionnaire g;
	//pour le stand by
	private long totalStandBy;
	private long tempStendBy;
	private String initialStandBy;
	private boolean isStandBy;
	
	
	Billard(int number, Gestionnaire g) {
		this.number = number;
		this.g = g;
		opened = false;
		cost = g.getSettings().getCost();
		initialTime = null;
		finalTime = null;
		playedTime = 0;
		pay = 0;
		tim = null;
		
		//pour le stand by
		totalStandBy = 0;
		initialStandBy = null;
		isStandBy = false;
	}
	
	/**
	 * ouvrir la table
	 */
	public void openTable(){ 
		opened = true;
		playedTime = 0;
		pay = 0;
		cost = g.getSettings().getCost();
		initialTime = Gestionnaire.getHour();
		finalTime = Gestionnaire.getPreciseHour();
		tim = new RepetAction(this);
		tab[1].setText(""+cost);
		tab[6].setText(""+cost);
		tab[2].setText(""+initialTime);
		tab[7].setText(""+initialTime);
		//pour le stand by
		tempStendBy = 0;
		totalStandBy = 0;
		initialStandBy = null;
		isStandBy = false;
		
		//effacement des tables ouvertes dans fenetrePersonnalisee
		for(int i=0; i<g.getSettings().getNumTable();i++){
			if(!g.getTables()[i].isOpened()){
				g.getTables()[i].getTab()[6].setText("");
				g.getTables()[i].getTab()[7].setText("");
				g.getTables()[i].getTab()[8].setText("");
				g.getTables()[i].getTab()[9].setText("");
			}
		}
		
		g.getSettings().setNumTable(g.getTables().length);
		g.getSettings().setProbleme(true);
		//g.getSettings().setBil(g.getTables());
		
		g.writeSettings("settings.spk");
		g.writeTablesInFile("tables.dat");
	}
	
	/**
	 * ouvrir la table en cas de probleme
	 */
	public void openSpecial(){ 
		int c = finalTime.indexOf('m');
		initialTime = finalTime.substring(0, c);
		
		
		cost = g.getSettings().getCost();
		tim = new RepetAction(this);
		tab[1].setText(""+cost);
		tab[6].setText(""+cost);
		tab[2].setText(""+initialTime);
		tab[7].setText(""+initialTime);
		playedTime = calculerTemps(finalTime);
		tempStendBy = calculerTemps(initialStandBy);
		playedTime = playedTime - (tempStendBy + totalStandBy);
		
	}
	
	/**
	 * fermer la table
	 */
	public void closeTable(){
		opened = false;
		//finalTime = Gestionnaire.getHour();
		tim = null;
		finalTime = null;
		double tot = arrondir(g.getTotal()+arrondir(pay));
		//System.out.println(arrondir(pay)+"   "+g.getTotal()+"     "+ tot);
		g.setTotal(tot);
		/*tab[0].setText("TABLE "+ number +"  ---");
		tab[1].setText("");
		tab[2].setText("");
		tab[3].setText("");*/
		ptitTrait(this.number-1);
		
		
		g.getSettings().setNumTable(g.getTables().length);
		g.getSettings().setProbleme(true);
		//g.getSettings().setBil(g.getTables());
		
		g.writeSettings("settings.spk");
		g.writeTablesInFile("tables.dat");
		
	}
	
	public void tickAction(){
		if(this.isStandBy){
			tempStendBy ++;
		}
		else{
			playedTime ++;
			int h = (int)(playedTime/3600);
			int min = (int)(playedTime/60)%60;
			int sec = (int)(playedTime % 60); 
			if(h>0){
				tab[3].setText(h+"h "+min+"min "+sec+"sec");
				tab[8].setText(h+"h "+min+"min "+sec+"sec");
			}
			else if (min > 0){
				tab[3].setText(min+"min "+sec+"sec");
				tab[8].setText(min+"min "+sec+"sec");
			}
			else{
				tab[3].setText(sec+"sec");
				tab[8].setText(sec+"sec");
			}
			
			pay = (double) playedTime * cost /3600;
			tab[4].setText(arrondir(pay)+"0 Fr");
			tab[9].setText(arrondir(pay)+"0 Fr");
		}
	}
	
	public double arrondir(double num){
		num *= 10;
		num = (int)(num+0.5);
		num /=10;
		return num;
	}
	
	public int calculerTemps(String time){
		
		String retard;
		int heureDebut;
		int heureRetard;
		int minDebut;
		int minRetard;
		int secDebut;
		int secRetard;
		int index1 = 0;
		int index2 = 0;
		int transcourru;
		
		retard = Gestionnaire.getPreciseHour();
		index2 = retard.indexOf("h");
		heureRetard = Integer.parseInt(retard.substring(index1,index2));
		index1 = index2+1;
		index2 = retard.indexOf("m");
		minRetard = Integer.parseInt(retard.substring(index1,index2));
		index1 = index2 +1;
		index2 = retard.length();
		secRetard = Integer.parseInt(retard.substring(index1,index2));
		
		
		/*System.out.println(heureRetard);
		System.out.println(minRetard);
		System.out.println(secRetard);*/
		
		index1 = 0;
		index2 = time.indexOf("h");
		heureDebut = Integer.parseInt(time.substring(index1,index2));
		index1 = index2+1;
		index2 = time.indexOf("m");
		minDebut = Integer.parseInt(time.substring(index1,index2));
		index1 = index2+1;
		index2 = time.length();
		secDebut = Integer.parseInt(time.substring(index1,index2));
		
		/*System.out.println("sdfgdfghf");
		System.out.println(heureDebut);
		System.out.println(minDebut);
		System.out.println(secDebut);*/
		if (heureRetard < heureDebut){
			heureRetard += 24;
		}
		
		transcourru = (heureRetard-heureDebut)*3600+(minRetard - minDebut)*60+(secRetard-secDebut);
		
		//System.out.println(transcourru);
		
		return transcourru;
	}
	
	public void ptitTrait(int indice){
		for(int i=0;i<g.getTables().length;i++){
			if(!g.getTables()[i].isOpened()){
				g.getTables()[i].tab[1].setText("");
				g.getTables()[i].tab[6].setText("");
				//g.getTables()[i].tab[2].setText("");
				//g.getTables()[i].tab[3].setText("");
			}
			g.getTables()[indice].tab[1].setText("-----");
			g.getTables()[indice].tab[6].setText("-----");
			//g.getTables()[indice].tab[2].setText("-----");
			//g.getTables()[indice].tab[3].setText("-----");
		}
	}
	/**
	 * met la table en stand by
	 */
	public void standBy(){
		isStandBy = true;
		tempStendBy = 0;
		initialStandBy = g.getPreciseHour();
		
	}
	
	/**
	 * stopStandBy
	 */
	public void stopStandBy(){
		isStandBy = false;
		totalStandBy += tempStendBy;
		initialStandBy = null;
		tempStendBy = 0;
		g.writeSettings("settings.spk");
		g.writeTablesInFile("tables.dat");
		
	}
	
	/**
	 * getter/setter
	 * @param p
	 */
	public void setPlayedTime(long p){
		playedTime = p;
	}
	
	public long getPlayedTime(){
	return playedTime;	
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(String initialTime) {
		this.initialTime = initialTime;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public String getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(String finalTime) {
		this.finalTime = finalTime;
	}

	public BillardGUI[] getTab() {
		return tab;
	}

	
	public void setTab(BillardGUI[] tab) {
		this.tab = tab;
	}

	public RepetAction getTim() {
		return tim;
	}

	public void setTim(RepetAction tim) {
		this.tim = tim;
	}

	public Gestionnaire getG() {
		return g;
	}

	public void setG(Gestionnaire g) {
		this.g = g;
	}

	public long getTotalStandBy() {
		return totalStandBy;
	}

	public long getTempStendBy() {
		return tempStendBy;
	}

	public String getInitialStandBy() {
		return initialStandBy;
	}

	public boolean isStandBy() {
		return isStandBy;
	}

	public void setTotalStandBy(long totalStandBy) {
		this.totalStandBy = totalStandBy;
	}

	public void setTempStendBy(long tempStendBy) {
		this.tempStendBy = tempStendBy;
	}

	public void setInitialStandBy(String initialStandBy) {
		this.initialStandBy = initialStandBy;
	}

	public void setStandBy(boolean isStandBy) {
		this.isStandBy = isStandBy;
	}

	public BillardButtonGUI getBouton() {
		return bouton;
	}

	public void setBouton(BillardButtonGUI bouton) {
		this.bouton = bouton;
	}
	
	
}
