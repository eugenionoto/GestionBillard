import java.awt.event.*;
import java.awt.*;

import javax.swing.*;



public class Fenetre extends JFrame implements ActionListener, MouseListener{
	
	static Gestionnaire g;
	private JMenuBar mbar;
	private JMenu m1;
	private JMenu m2;
	private JMenuItem m11;
	private JMenuItem m12;
	private JMenuItem m13;
	private JMenuItem m21;
	private JMenuItem m22;
	private boolean blocked = false;
	private JPanel sup;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JPanel inf;
	private JLabel labelTable;
	private JLabel labelCost;
	private JLabel labelHeure;
	private JLabel labelPay;
	private JLabel labelTemps;
	
	Fenetre(int numTablesl, Gestionnaire g){
		this.g = g;
		//dimensions de l'ecran
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		setTitle("Gestion des tables");
		setBounds(0,0,largeur,hauteur);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
//		
//		gestion evenementielle de la fermeture de la fenêtre
	    addWindowListener(new WindowAdapter()
	           {public void windowClosing(WindowEvent e)
	                  {
	        	   new TerminerProgramme(Fenetre.g.getTotal(),Fenetre.g);
	                  }
	             } ) ; 
//	     utilisation d'un toolkit pour l'affichage
	  // d'une icone associla tIconImage(Toolkit.getDefaultToolkit().getImage("billard.gif"));
	    setIconImage(Toolkit.getDefaultToolkit().getImage("billard.gif"));    
	    
//Ajout d'une barre de menus ?la fenêtre
	     mbar = new JMenuBar();
	     m1 = new JMenu("Fichier");
	     m11 = new JMenuItem("Options");
	     m11.addActionListener(this); // installation d'un ecouteur d'action
	     m1.add(m11);  // ajout d'une option ?un menu
	     m13 = new JMenuItem("En cas de problème");
	     m13.addActionListener(this);
	     m1.add(m13);
	     m12 = new JMenuItem("Quitter");
	     m12.addActionListener(this);
	     m1.add(m12);   
	     mbar.add(m1); 
//ajout du 2eme menu, celui de la vue du total
	     m2 = new JMenu("Total");
	     m21 = new JMenuItem("Total actuel");
	     m21.addActionListener(this);//ajout d'un ecouteur d'action
	     m2.add(m21);//ajout du total actuel dans le menu
	     m22 = new JMenuItem("Total de la derniere session");
	     m22.addActionListener(this);//ajout d'un ecouteur d'action
	     m2.add(m22);//ajout de l'ancien total dans le menu
	     mbar.add(m2);
	     setJMenuBar(mbar); 
	        
	     
//Ajout du panel conteneur sans layout
	     Container contentPane = getContentPane();
	     contentPane.setLayout(null);
	     //contentPane.setBackground(Color.red);
	     //contentPane.addMouseListener(this);
	     
//Ajout du panel supérieur	     
	     sup = new JPanel();
	     int hauteurSup = hauteur/20;
	     sup.setMaximumSize(new Dimension(largeur -5, hauteurSup));
	     sup.setBounds(0, 0, largeur -5 , hauteurSup);
	     sup.setLayout(new GridLayout(1,3));
	     sup.setBackground(g.getSettings().getLabelColor());
	     
//labels du panel supérieur
	     label1 = new JLabel();
	     label1.setText("Programme de gestion de billard");
	     label1.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     label1.setForeground(g.getSettings().getTextColor());
	     label2 = new JLabel();
	     label3 = new JLabel(g.getDate(),SwingConstants.RIGHT);
	     label3.setText(g.getDate());
	     label3.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     label3.setForeground(g.getSettings().getTextColor());
	     
	     sup.add(label1);
	     sup.add(label2);
	     sup.add(label3);
	     
//panel inférieur
	     inf = new JPanel();
	     inf.setMaximumSize(new Dimension(largeur, hauteur-hauteurSup));
	     inf.setBounds(0,hauteurSup,largeur, hauteur-hauteurSup);
	     inf.setLayout(new GridLayout(g.getSettings().getNumTable() + 2,4));
	     inf.setBackground(g.getSettings().getLabelColor());
	     
//labels du panel inférieur
	     labelTable = new JLabel("Table numéro",SwingConstants.LEFT);
	     labelTable.setForeground(g.getSettings().getTextColor());
	     labelTable.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     labelCost = new JLabel("Prix ?l'heure",SwingConstants.CENTER);
	     labelCost.setForeground(g.getSettings().getTextColor());
	     labelCost.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     labelHeure = new JLabel("Heure d'ouverture",SwingConstants.CENTER);
	     labelHeure.setForeground(g.getSettings().getTextColor());
	     labelHeure.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     labelPay = new JLabel("A payer",SwingConstants.CENTER);
	     labelPay.setForeground(g.getSettings().getTextColor());
	     labelPay.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     labelTemps = new JLabel("temps de jeu",SwingConstants.CENTER);
	     labelTemps.setForeground(g.getSettings().getTextColor());
	     labelTemps.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     
	     
	     
	     inf.add(labelTable);
	     inf.add(labelCost);
	     inf.add(labelHeure);
	     inf.add(labelTemps);
	     inf.add(labelPay);
	     
//les labels table
	     for(int i=0; i<g.getSettings().getNumTable(); i++){
	    	 BillardGUI [] temp = new BillardGUI [10];
	    	 for(int j=0; j<5;j++){
	    		 temp[j] = new BillardGUI(g.getTables()[i]);
	    		 inf.add(temp[j]);
	    		 temp[j].addMouseListener(this);
	    	 }
	    	 int nombreEffectif = g.getTables()[i].getNumber()+ 4;
	    	 temp[0].setText("TABLE "+nombreEffectif +"      ----");
	    	 temp[0].setHorizontalAlignment(SwingConstants.LEFT);
	    	 temp[0].setForeground(g.getSettings().getTextColor());
	    	 temp[0].setFont(new Font("TimesRoman ",Font.BOLD,20));
	    	 
	    	 temp[1].setHorizontalAlignment(SwingConstants.CENTER);
	    	 temp[1].setForeground(g.getSettings().getTextColor());
	    	 temp[1].setFont(new Font("TimesRoman ",Font.BOLD,20));
	    	 
	    	 temp[2].setHorizontalAlignment(SwingConstants.CENTER);
	    	 temp[2].setForeground(g.getSettings().getTextColor());
	    	 temp[2].setFont(new Font("TimesRoman ",Font.BOLD,20));
	    	 
	    	 temp[3].setHorizontalAlignment(SwingConstants.CENTER);
	    	 temp[3].setForeground(g.getSettings().getTextColor());
	    	 temp[3].setFont(new Font("TimesRoman ",Font.BOLD,20));
	    	 
	    	 temp[4].setHorizontalAlignment(SwingConstants.CENTER);
	    	 temp[4].setForeground(g.getSettings().getTextColor());
	    	 temp[4].setFont(new Font("TimesRoman ",Font.BOLD,20));
	    	 
	    	 for(int k=5;k<10;k++){
	    		 temp[k] = temp[0].getB().getTab()[k]; 
	   	 }
	    	 
	    	 g.getTables()[i].setTab(temp);
	     }
	     
	    
	     
	     contentPane.add(inf);
	     contentPane.add(sup);
	     
	     setResizable(false);
	     setVisible(true);
	       
	       
	}
	/*
	 * liste des labels 
	 * sup, inf
	 * label1, label2, label3
	 * labelTable, labelCost, labelHeure, labelPay
	 */
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource()instanceof JMenuItem)  {
			String ChoixOption = evt.getActionCommand(); 
			if (ChoixOption.equals("Options")){
				FenetreOptions fo = new FenetreOptions(g);
			}
			else if (ChoixOption.equals("Quitter")){
				TerminerProgramme tp = new TerminerProgramme(g.getTotal(),g);
			}
			else if (ChoixOption.equals("Total actuel")){
				FenetreTotal ft = new FenetreTotal(g.getTotal(),"actuelle");
			}
			else if (ChoixOption.equals("Total de la derniere session")){
				FenetreTotal ft = new FenetreTotal(g.readTotalFromFile("total.tot"),"precedente");
			}
			else{
				g.getSettings().setNumTable(g.getTables().length);
				g.getSettings().setProbleme(true);
				//g.getSettings().setBil(g.getTables());
				
				g.writeSettings("settings.spk");
				g.writeTablesInFile("tables.dat");
				System.exit(0);
			}
		}
	}
	
	public void mouseClicked(MouseEvent evt){
		
	}
	
	public void mouseExited(MouseEvent evt){
		
	}
	
	public void mouseReleased (MouseEvent evt){
		/*if (evt.getSource()instanceof BillardGUI)  {
			BillardGUI bilTemp = (BillardGUI)evt.getSource();
			bilTemp.getB().getTab()[2].setText("grui");
		}*/
		if (evt.getSource()instanceof BillardGUI)  {
			BillardGUI bilTemp = (BillardGUI)evt.getSource();
			//ouvre si la table est ouverte
			//getSup().setBackground(Color.yellow);
			if(!bilTemp.getB().isOpened() && !blocked){
				blocked = true;
				FenetreOuvrir ouverture = new FenetreOuvrir(bilTemp.getB().getNumber(),g);
			}
			else if (bilTemp.getB().isOpened() && !blocked){
				blocked = true;
				FenetreFermer fermeture = new FenetreFermer(bilTemp.getB().getNumber(),g);
			}
		}
	}
	
	public void mousePressed (MouseEvent evt){
		
	}
	
	public void mouseEntered (MouseEvent evt){
		
	}
	
	
	
	/**
	 * getters et setters
	 */
	
	public JPanel getInf() {
		return inf;
	}
	public void setInf(JPanel inf) {
		this.inf = inf;
	}
	public JLabel getLabel1() {
		return label1;
	}
	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}
	public JLabel getLabel2() {
		return label2;
	}
	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}
	public JLabel getLabel3() {
		return label3;
	}
	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}
	public JLabel getLabelCost() {
		return labelCost;
	}
	public void setLabelCost(JLabel labelCost) {
		this.labelCost = labelCost;
	}
	public JLabel getLabelHeure() {
		return labelHeure;
	}
	public void setLabelHeure(JLabel labelHeure) {
		this.labelHeure = labelHeure;
	}
	public JLabel getLabelPay() {
		return labelPay;
	}
	public void setLabelPay(JLabel labelPay) {
		this.labelPay = labelPay;
	}
	
	public JLabel getLabelTemps() {
		return labelTemps;
	}
	public void setLabelTemps(JLabel labelTemps) {
		this.labelTemps = labelTemps;
	}
	public JLabel getLabelTable() {
		return labelTable;
	}
	public void setLabelTable(JLabel labelTable) {
		this.labelTable = labelTable;
	}
	public JMenu getM1() {
		return m1;
	}
	public void setM1(JMenu m1) {
		this.m1 = m1;
	}
	public JMenuItem getM11() {
		return m11;
	}
	public void setM11(JMenuItem m11) {
		this.m11 = m11;
	}
	public JMenuItem getM12() {
		return m12;
	}
	public void setM12(JMenuItem m12) {
		this.m12 = m12;
	}
	public JMenuBar getMbar() {
		return mbar;
	}
	public void setMbar(JMenuBar mbar) {
		this.mbar = mbar;
	}
	public JPanel getSup() {
		return sup;
	}
	public void setSup(JPanel sup) {
		this.sup = sup;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public Gestionnaire getG() {
		return g;
	}
	public void setG(Gestionnaire g) {
		this.g = g;
	}
	
}
