import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class FenetreOptions extends JFrame implements ActionListener{

	private JLabel nbTablesLabel;
	private JLabel prixLabel;
	private JLabel prixLabel2;
	private JLabel couleurFondLabel;
	private JLabel couleurTexteLabel;
	private JLabel fenetrePersoLabel;
	private JTextField nbTablesText;
	private JTextField prixText;
	private JTextField prixText2;
	private JButton couleurFondButton;
	private JButton couleurTexteButton;
	private JCheckBox boxFenetrePerso;
	private JButton okButton;
	private JButton cancelButton;
	private Gestionnaire g;
	private Settings nouveau;
	
	
	FenetreOptions(Gestionnaire g){
		this.g = g;
		nouveau = new Settings(g.getSettings().getNumTable(),g.getSettings().getLabelColor(),g.getSettings().getTextColor(),g.getSettings().getCost(),g.getSettings().getSecondaryCost(), false,g.getSettings().isPersonalise());
		
//		dimensions de l'ecran
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		setTitle("Options");
		setBounds(largeur/3,hauteur/3,largeur/2,hauteur/4);
		
		
		
		Container content = getContentPane();
	    content.setLayout(new GridLayout());
	    
	    JPanel contentPane = new JPanel();
	    contentPane.setLayout(new GridLayout(7,3));
	    content.add(contentPane);
	    
	    //elements a ins�rer
	    nbTablesLabel = new JLabel("nombre de tables");
	  //version graphique=nbTables statiques
	    //contentPane.add(nbTablesLabel);
	    //contentPane.add(new JLabel());
	    nbTablesText = new JTextField(""+g.getSettings().getNumTable());
	    nbTablesText.setHorizontalAlignment(SwingConstants.RIGHT);
	    //version graphique=nbTables statiques
	    //contentPane.add(nbTablesText);
	    
	    prixLabel = new JLabel("prix de l'heure");
	    contentPane.add(prixLabel);
	    contentPane.add(new JLabel());
	    prixText = new JTextField(""+ g.getSettings().getCost());
	    prixText.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(prixText);

		 prixLabel2 = new JLabel("prix rabais");
	    contentPane.add(prixLabel2);
	    contentPane.add(new JLabel());
	    prixText2 = new JTextField(""+ g.getSettings().getSecondaryCost());
	    prixText2.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(prixText2);
	    
	    couleurFondLabel = new JLabel("couleur du fond");
	    contentPane.add(couleurFondLabel);
	    contentPane.add(new JLabel());
	    couleurFondButton = new JButton();
	    couleurFondButton.setBackground(g.getSettings().getLabelColor());
	    couleurFondButton.addActionListener(this);
	    contentPane.add(couleurFondButton);
	    
	    couleurTexteLabel = new JLabel("couleur du texte");
	    contentPane.add(couleurTexteLabel);
	    contentPane.add(new JLabel());
	    couleurTexteButton = new JButton();
	    couleurTexteButton.setBackground(g.getSettings().getTextColor());
	    couleurTexteButton.addActionListener(this);
	    contentPane.add(couleurTexteButton);
	    
	    fenetrePersoLabel = new JLabel("fenetre personnalisee");
	    contentPane.add(fenetrePersoLabel);
	    contentPane.add(new JLabel());
	    boxFenetrePerso = new JCheckBox();
	    boxFenetrePerso.setSelected(g.getSettings().isPersonalise());
	    contentPane.add(boxFenetrePerso);
	    
	    contentPane.add(new JLabel());
	    contentPane.add(new JLabel());
	    contentPane.add(new JLabel());
	    
	    okButton = new JButton("OK");
	    okButton.addActionListener(this);
	    contentPane.add(okButton);
	    contentPane.add(new JLabel());
	    cancelButton = new JButton("annuler");
	    cancelButton.addActionListener(this);
	    contentPane.add(cancelButton);
	    
	    
	     pack();
	     
	     setAlwaysOnTop(true);
	     setVisible(true);
	}
	
	
	public Settings getNouveau() {
		return nouveau;
	}


	public void setNouveau(Settings nouveau) {
		this.nouveau = nouveau;
	}


	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource()instanceof JButton)  {
			JButton grui = (JButton)evt.getSource();
			String ChoixOption = evt.getActionCommand(); 
			if (ChoixOption.equals("annuler")){
				this.dispose();
			}
			else if (ChoixOption.equals("OK")){
				int numTab = Integer.parseInt(nbTablesText.getText());
				Color fond = couleurFondButton.getBackground();
				Color text = couleurTexteButton.getBackground();
				double prix = Double.parseDouble(prixText.getText());
				double prix2 = Double.parseDouble(prixText2.getText());
				boolean fenetrePerso = boxFenetrePerso.isSelected();
				nouveau = new Settings(numTab,fond,text,prix, prix2,false, fenetrePerso);
				if (!(nouveau.getLabelColor().equals(g.getSettings().getLabelColor()))){
					g.getF().getSup().setBackground(nouveau.getLabelColor());
					g.getF().getInf().setBackground(nouveau.getLabelColor());
					
					g.getF1().getSup().setBackground(nouveau.getLabelColor());
					g.getF1().getInf().setBackground(nouveau.getLabelColor());
				}
				if (!(nouveau.getTextColor().equals(g.getSettings().getTextColor()))){
					g.getF().getLabel1().setForeground(nouveau.getTextColor());
					g.getF().getLabel2().setForeground(nouveau.getTextColor());
					g.getF().getLabel3().setForeground(nouveau.getTextColor());
					
					g.getF1().getLabel1().setForeground(nouveau.getTextColor());
					g.getF1().getLabel2().setForeground(nouveau.getTextColor());
					g.getF1().getLabel3().setForeground(nouveau.getTextColor());
					
					g.getF().getLabelTable().setForeground(nouveau.getTextColor());
					g.getF().getLabelCost().setForeground(nouveau.getTextColor());
					g.getF().getLabelHeure().setForeground(nouveau.getTextColor());
					g.getF().getLabelPay().setForeground(nouveau.getTextColor());
					g.getF().getLabelTemps().setForeground(nouveau.getTextColor());
					
					g.getF1().getLabelTable().setForeground(nouveau.getTextColor());
					g.getF1().getLabelCost().setForeground(nouveau.getTextColor());
					g.getF1().getLabelHeure().setForeground(nouveau.getTextColor());
					g.getF1().getLabelPay().setForeground(nouveau.getTextColor());
					g.getF1().getLabelTemps().setForeground(nouveau.getTextColor());
						
					
					for(int i=0; i<g.getTables().length;i++){
						for(int j=0; j<5;j++){
							g.getTables()[i].getTab()[j].setForeground(nouveau.getTextColor());
							g.getTables()[i].getTab()[j+5].setForeground(nouveau.getTextColor());
						}
					}
					
				}
				
				if(nouveau.isPersonalise()){
					g.getF().setVisible(true);
					g.getF1().setVisible(false);
				}
				else{
					g.getF1().setVisible(true);
					g.getF().setVisible(false);
				}
				
				g.setSettings(nouveau);
				this.dispose();
			}
			else if (ChoixOption.equals("") && (grui.getBackground().equals(nouveau.getLabelColor()))){
				ColorDialog cg = new ColorDialog(this, g,grui,0);
			}
			else {//if (ChoixOption.equals("") && (grui.getForeground().equals(nouveau.getTextColor()))){
				ColorDialog cg = new ColorDialog(this, g,grui,1);
			}
		}
	}
	
	
}
