import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FenetreOuvrir extends JFrame implements ActionListener{

	private Gestionnaire g;
	private int tableNumber;
	private JLabel text;
	private JButton ok;
	private JButton cancel;
	private JButton prix;
	private JButton secondaryPrix;
	private double valeur;
	
	
	FenetreOuvrir(int tableNumber, Gestionnaire g){
		this.g = g;
		this.tableNumber = tableNumber;
		valeur = g.getSettings().getCost();		 

		//calcul des dimensions
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		setTitle("ouverture de la table "+(tableNumber+4));
		setBounds(largeur*3/8, hauteur*3/8, largeur/4, hauteur/4);
		
//		gestion evenementielle de la fermeture de la fen�tre
	    addWindowListener(new WindowAdapter()
	           {public void windowClosing(WindowEvent e)
	                  {
	        	   		Fenetre.g.getF().setBlocked(false);
	                  }
	             } ) ;
		
		Container contentPane = getContentPane();
	    contentPane.setLayout(null);

		prix = new JButton(""+g.getSettings().getCost());
		prix.setHorizontalAlignment(SwingConstants.CENTER);
		prix.setVerticalAlignment(SwingConstants.CENTER);
		prix.setBounds(0,0,largeur/8, 30-2);
		prix.addActionListener(this);
		contentPane.add(prix);

		secondaryPrix = new JButton(""+g.getSettings().getSecondaryCost());
		secondaryPrix.setHorizontalAlignment(SwingConstants.CENTER);
		secondaryPrix.setVerticalAlignment(SwingConstants.CENTER);
		secondaryPrix.setBounds(largeur/8+2,0,largeur/8-8, 30-2);
		secondaryPrix.addActionListener(this);
		contentPane.add(secondaryPrix);
	    
	    text = new JLabel();
	    text.setText("<html>voulez vous ouvrir la table " +(tableNumber+4)+ "<br><br> prix = "+ valeur+"0</html>");
	    text.setBounds(0, 15, largeur/4, hauteur*3/16);
	    text.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPane.add(text);
		
		ok = new JButton("OK");
		ok.setHorizontalAlignment(SwingConstants.CENTER);
		ok.setVerticalAlignment(SwingConstants.CENTER);
		ok.setBounds(0,hauteur*5/32,largeur/8, hauteur/16-2);
		ok.addActionListener(this);
		contentPane.add(ok);
		
		cancel = new JButton("annuler");
		cancel.setHorizontalAlignment(SwingConstants.CENTER);
		cancel.setVerticalAlignment(SwingConstants.CENTER);
		cancel.setBounds(largeur/8+2,hauteur*5/32,largeur/8-8, hauteur/16-2);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		//g.getF().getSup().setBackground(Color.yellow);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
		
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource()instanceof JButton)  {
			String ChoixOption = evt.getActionCommand();
			if (ChoixOption.equals("OK")){
				g.getF().setBlocked(false);
				g.getF1().setBlocked(false);
				g.getTables()[tableNumber-1].openTable();
				g.getTables()[tableNumber-1].setCost(valeur);
				g.getTables()[tableNumber-1].getTab()[1].setText(""+g.getTables()[tableNumber-1].getCost());
				g.getTables()[tableNumber-1].getTab()[6].setText(""+g.getTables()[tableNumber-1].getCost());
				
				this.dispose();
			}
			else if (ChoixOption.equals("annuler")){
				g.getF().setBlocked(false);
				g.getF1().setBlocked(false);
				this.dispose();
			}
			else if (ChoixOption.equals(Double.toString(g.getSettings().getSecondaryCost()))){
				valeur = g.getSettings().getSecondaryCost();
				text.setText("<html>voulez vous ouvrir la table " +(tableNumber+4)+ "<br><br> prix = "+ valeur+"0</html>");
			}
			else{
				valeur = g.getSettings().getCost();
				text.setText("<html>voulez vous ouvrir la table " +(tableNumber+4)+ "<br><br> prix = "+ valeur+"0</html>");
			}
		}
	}
}
