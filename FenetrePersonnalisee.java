import java.awt.event.*;
import java.awt.*;

import javax.swing.*;


public class FenetrePersonnalisee extends JFrame implements ActionListener, MouseListener{
	
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
	private JLabel labelTable = new JLabel();
	private JLabel labelCost = new JLabel();
	private JLabel labelHeure = new JLabel();
	private JLabel labelPay = new JLabel();
	private JLabel labelTemps = new JLabel();
	private JLabel table5;
	private JLabel table6;
	private JLabel table7;
	private JLabel table8;
	private JLabel table9;
	private JLabel table10;
	private JLabel table11;
	private JLabel table12;
//	private JLabel table13;
	private BillardButtonGUI tempBil;
	
	
	//constructeur
	public FenetrePersonnalisee(int numTables,Gestionnaire g){
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
	        	   new TerminerProgramme(FenetrePersonnalisee.g.getTotal(),FenetrePersonnalisee.g);
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
	     label2 = new JLabel(g.getHour(),SwingConstants.CENTER);
			label2.setText(g.getHour());
	     label2.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     label2.setForeground(g.getSettings().getTextColor());
	     label3 = new JLabel(g.getDate(),SwingConstants.RIGHT);
	     label3.setText(g.getDate());
	     label3.setFont(new Font("TimesRoman ",Font.BOLD,20));
	     label3.setForeground(g.getSettings().getTextColor());
		 	Dateur maDate = new Dateur(this);
	     
	     sup.add(label1);
	     sup.add(label2);
	     sup.add(label3);
	     
	   //panel inférieur
	     inf = new JPanel();
	     inf.setMaximumSize(new Dimension(largeur, hauteur-hauteurSup));
	     inf.setBounds(0,hauteurSup,largeur, hauteur-hauteurSup);
	     //inf.setLayout(new GridLayout(g.getSettings().getNumTable() + 2,4));
	     inf.setLayout(null);
	     inf.setBackground(g.getSettings().getLabelColor());
	     
	     table9 = new JLabel();
	     table9.setIcon(new ImageIcon("billardPerso.gif"));
	     table9.setBounds(60,20, 180, 250);
	     BillardGUI [] temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[4]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     int nombreEffectif = g.getTables()[4].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(80,28,143,50);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(80,78,143,50);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(80,120,143,50);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(80,166,143,50);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(80,210,143,50);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 g.getTables()[4].setTab(temp);
    	 //bouton standBy
    	 tempBil = new BillardButtonGUI(g.getTables()[4]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(135,260,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[4].setBouton(tempBil);
    	 tempBil  = null;
    	 
    	 
	     table10 = new JLabel();
	     table10.setIcon(new ImageIcon("billardPerso.gif"));
	     table10.setBounds(300,20, 180, 250);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[5]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[5].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(320,28,143,50);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(320,78,143,50);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(320,120,143,50);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(320,166,143,50);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(320,210,143,50);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
	     
    	 g.getTables()[5].setTab(temp);
    	 //stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[5]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(375,260,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[5].setBouton(tempBil);
    	 tempBil  = null;
    	 
	     table11 = new JLabel();
	     table11.setIcon(new ImageIcon("billardPerso.gif"));
	     table11.setBounds(540,20, 180, 250);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[6]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[6].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(560,28,143,50);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(560,78,143,50);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(560,120,143,50);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(560,166,143,50);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(560,210,143,50);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
	     
    	 g.getTables()[6].setTab(temp);
    	//stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[6]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(615,260,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[6].setBouton(tempBil);
    	 tempBil  = null;
    	 
    	 
	     table12 = new JLabel();
	     table12.setIcon(new ImageIcon("billardPerso.gif"));
	     table12.setBounds(780,20, 180, 250);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[7]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[7].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(800,28,143,50);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(800,78,143,50);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(800,120,143,50);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(800,166,143,50);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(800,210,143,50);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
	     
    	 g.getTables()[7].setTab(temp);
    	//stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[7]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(855,260,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[7].setBouton(tempBil);
    	 tempBil  = null;
    	 
	     table8 = new JLabel();
	     table8.setIcon(new ImageIcon("billardPerso2.gif"));
	     table8.setBounds(150,300, 250, 180);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[3]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[3].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(170,320,210,30);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(170,350,210,30);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(170,380,210,30);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(170,410,210,30);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(170,440,210,25);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 g.getTables()[3].setTab(temp);
    	//stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[3]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(395,375,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[3].setBouton(tempBil);
    	 tempBil  = null;
    	 
	     
	     table6 = new JLabel();
	     table6.setIcon(new ImageIcon("billardPerso2.gif"));
	     table6.setBounds(550,300, 250, 180);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[1]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[1].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(570,320,210,30);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(570,350,210,30);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(570,380,210,30);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(570,410,210,30);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(570,440,210,25);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 g.getTables()[1].setTab(temp);
    	//stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[1]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(795,375,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[1].setBouton(tempBil);
    	 tempBil  = null;
    	 
    	 
	     table7 = new JLabel();
	     table7.setIcon(new ImageIcon("billardPerso2.gif"));
	     table7.setBounds(310,500, 250, 180);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[2]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[2].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(330,520,210,30);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(330,550,210,30);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(330,580,210,30);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(330,610,210,30);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(330,640,210,25);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
	     
    	 g.getTables()[2].setTab(temp);
    	//stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[2]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(555,575,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[2].setBouton(tempBil);
    	 tempBil  = null;
    	 
    	 
	     table5 = new JLabel();
	     table5.setIcon(new ImageIcon("billardPerso2.gif"));
	     table5.setBounds(710,500, 250, 180);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[0]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[0].getNumber()+ 4;
    	 temp[5].setText("TABLE "+nombreEffectif);
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(730,520,210,30);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(730,550,210,30);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(730,580,210,30);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(730,610,210,30);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(730,640,210,25);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
	     
    	 g.getTables()[0].setTab(temp);
    	//stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[0]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(955,575,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[0].setBouton(tempBil);
    	 tempBil  = null;
    	 /*
    	 table13 = new JLabel();
	     table13.setIcon(new ImageIcon("shuffle.GIF"));
	     table13.setBounds(0,400, 130, 320);
	     temp= new BillardGUI [10];
	     for (int i=5; i<10;i++){
	    	 temp[i] = new BillardGUI(g.getTables()[8]);
	    	 inf.add(temp[i]);
	    	 temp[i].addMouseListener(this);
	     }
	     for(int i =0;i<5;i++){
	    	 temp[i] = temp[5].getB().getTab()[i];
	     }
	     
	     nombreEffectif = g.getTables()[8].getNumber()+ 4;
    	 temp[5].setText("SHUFFLE");
    	 temp[5].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[5].setOpaque(false);
    	 temp[5].setBounds(15,415,100,50);
    	 temp[5].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[6].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[6].setOpaque(false);
    	 temp[6].setBounds(15,465,100,50);
    	 temp[6].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
      	 temp[7].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[7].setOpaque(false);
    	 temp[7].setBounds(15,515,100,50);
    	 temp[7].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	
    	 temp[8].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[8].setOpaque(false);
    	 temp[8].setBounds(15,565,100,50);
    	 temp[8].setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 
    	 temp[9].setHorizontalAlignment(SwingConstants.CENTER);
    	 temp[9].setOpaque(false);
    	 temp[9].setBounds(15,615,100,40);
    	 temp[9].setFont(new Font("TimesRoman ",Font.BOLD,20));
	     
    	 g.getTables()[8].setTab(temp);
    	//stand by
    	 tempBil = new BillardButtonGUI(g.getTables()[8]);
    	 inf.add(tempBil);
	     tempBil.setHorizontalAlignment(SwingConstants.CENTER);
	     tempBil.setBounds(125,550,30,30);
	     tempBil.setFont(new Font("TimesRoman ",Font.BOLD,20));
    	 tempBil.setText("S");
    	 tempBil.addMouseListener(this);
    	 g.getTables()[8].setBouton(tempBil);
    	 tempBil  = null;
    	 */
    	 
  //  	 inf.add(table13);
	     inf.add(table9);
	     inf.add(table10);
	     inf.add(table11);
	     inf.add(table12);
	     inf.add(table8);
	     inf.add(table6);
	     inf.add(table7);
	     inf.add(table5);
	     
	     
	     
	     contentPane.add(inf);
	     contentPane.add(sup);
	     
	     setResizable(false);
	     setVisible(true);
	     
	}

	//tick pour affichage de date et heure
	public void tickAction(){
		label2.setText(g.getHour());
	}
		
	
	//evenements
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
		else if (evt.getSource()instanceof BillardButtonGUI){
				BillardButtonGUI bilTemp = (BillardButtonGUI)evt.getSource();
				
			   if(bilTemp.getB().isStandBy()){
				   bilTemp.getB().stopStandBy();
			   }
			   else{
				   bilTemp.getB().standBy();
			   }
				//MiniOptionsFrame f = new MiniOptionsFrame(bilTemp.getB());
		}
	}
	
	public void mousePressed (MouseEvent evt){
		
	}
	
	public void mouseEntered (MouseEvent evt){
		
	}

	public static Gestionnaire getG() {
		return g;
	}

	public JMenuBar getMbar() {
		return mbar;
	}

	public JMenu getM1() {
		return m1;
	}

	public JMenu getM2() {
		return m2;
	}

	public JMenuItem getM11() {
		return m11;
	}

	public JMenuItem getM12() {
		return m12;
	}

	public JMenuItem getM13() {
		return m13;
	}

	public JMenuItem getM21() {
		return m21;
	}

	public JMenuItem getM22() {
		return m22;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public JPanel getSup() {
		return sup;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public JPanel getInf() {
		return inf;
	}

	public JLabel getLabelTable() {
		return labelTable;
	}

	public static void setG(Gestionnaire g) {
		FenetrePersonnalisee.g = g;
	}

	public void setMbar(JMenuBar mbar) {
		this.mbar = mbar;
	}

	public void setM1(JMenu m1) {
		this.m1 = m1;
	}

	public void setM2(JMenu m2) {
		this.m2 = m2;
	}

	public void setM11(JMenuItem m11) {
		this.m11 = m11;
	}

	public void setM12(JMenuItem m12) {
		this.m12 = m12;
	}

	public void setM13(JMenuItem m13) {
		this.m13 = m13;
	}

	public void setM21(JMenuItem m21) {
		this.m21 = m21;
	}

	public void setM22(JMenuItem m22) {
		this.m22 = m22;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void setSup(JPanel sup) {
		this.sup = sup;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}

	public void setInf(JPanel inf) {
		this.inf = inf;
	}

	public void setLabelTable(JLabel labelTable) {
		this.labelTable = labelTable;
	}

	public JLabel getLabelCost() {
		return labelCost;
	}

	public JLabel getLabelHeure() {
		return labelHeure;
	}

	public JLabel getLabelPay() {
		return labelPay;
	}

	public JLabel getLabelTemps() {
		return labelTemps;
	}

	public void setLabelCost(JLabel labelCost) {
		this.labelCost = labelCost;
	}

	public void setLabelHeure(JLabel labelHeure) {
		this.labelHeure = labelHeure;
	}

	public void setLabelPay(JLabel labelPay) {
		this.labelPay = labelPay;
	}

	public void setLabelTemps(JLabel labelTemps) {
		this.labelTemps = labelTemps;
	}
	
	
	
}
