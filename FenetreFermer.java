import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FenetreFermer extends JFrame implements ActionListener{
	

	private Gestionnaire g;
	private int tableNumber;
	private JLabel text;
	private JButton ok;
	private JButton cancel;
	
	
	FenetreFermer(int tableNumber, Gestionnaire g){
		this.g = g;
		this.tableNumber = tableNumber;
		
		//calcul des dimensions
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		setTitle("fermeture de la table "+(tableNumber+4));
		setBounds(largeur*3/8, hauteur*3/8, largeur/4, hauteur/4);
		
//		gestion evenementielle de la fermeture de la fenêtre
	    addWindowListener(new WindowAdapter()
	           {public void windowClosing(WindowEvent e)
	                  {
	        	   		Fenetre.g.getF().setBlocked(false);
	                  }
	             } ) ;
		
		Container contentPane = getContentPane();
	    contentPane.setLayout(null);
	    
	    text = new JLabel();
	    text.setText("<html>voulez vous fermer la table " +(tableNumber +4)+"<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; prix : " + g.getTables()[tableNumber-1].arrondir(g.getTables()[tableNumber-1].getPay())+"0</html>");
	    text.setBounds(0, 0, largeur/4, hauteur*3/16);
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
				g.getTables()[tableNumber-1].closeTable();
				this.dispose();
			}
			else {
				g.getF().setBlocked(false);
				g.getF1().setBlocked(false);
				this.dispose();
			}
		}
	}
}
