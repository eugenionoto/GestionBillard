import java.awt.event.*;
import java.awt.*;

import javax.swing.*;


public class FenetreTotal extends JFrame implements ActionListener{

	private double total;
	private String session;
	private JLabel text;
	private JButton ok;
	private JButton cancel;
	
	
	FenetreTotal(double total, String session){
		this.total = total;
		this.session = session;
		
		FenetrePersonnalisee.g.getF().setBlocked(true);
		//calcul des dimensions
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		setTitle("total " + session);
		setBounds(largeur*3/8, hauteur*3/8, largeur/4, hauteur/4);
		
//		gestion evenementielle de la fermeture de la fenêtre
	    addWindowListener(new WindowAdapter()
	           {public void windowClosing(WindowEvent e)
	                  {
	        	   		FenetrePersonnalisee.g.getF().setBlocked(false);
	                  }
	             } ) ;
		
		Container contentPane = getContentPane();
	    contentPane.setLayout(null);
	    
	    text = new JLabel();
	    text.setText("<html>le total de la session " +session+"<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; total : " + total+"0</html>");
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
				FenetrePersonnalisee.g.getF().setBlocked(false);
				this.dispose();
			}
			else {
				FenetrePersonnalisee.g.getF().setBlocked(false);
				this.dispose();
			}
		}
	}
}

