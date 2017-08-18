import java.awt.event.*;
import java.awt.*;
import java.io.*;

import javax.swing.*;

public class TerminerProgramme extends JFrame implements ActionListener{
	
	private Gestionnaire g;
	private double total;
	private JLabel text;
	private JButton ok;
	private JButton cancel;
	
	
	TerminerProgramme(double total, Gestionnaire g){
		
		this.total = total;
		this.g = g;
		
		//calcul des dimensions
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		setTitle("Quitter le programme");
		setBounds(largeur*3/8, hauteur*3/8, largeur/4, hauteur/4);
		
		
		Container contentPane = getContentPane();
	    contentPane.setLayout(null);
	    
	    text = new JLabel("<html>le total de la session est : <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+total+"0"+" Fr <br><br> Etes vous sur de vouloir quitter le programme?"+ "</html>");
	    //text.setText("le total de la session est : "+total +" Fr" +" souhaitez vous quitter le programme?");
	    text.setBounds(0, 0, largeur/4, hauteur*3/16);
	    text.setHorizontalAlignment(SwingConstants.CENTER);
	    text.setVerticalAlignment(SwingConstants.CENTER);
	    contentPane.add(text);
		
		ok = new JButton("OK");
		ok.setHorizontalAlignment(SwingConstants.CENTER);
		ok.setVerticalAlignment(SwingConstants.CENTER);
		ok.setBounds(0,hauteur*3/16,largeur/8, hauteur/32-2);
		ok.addActionListener(this);
		contentPane.add(ok);
		
		cancel = new JButton("annuler");
		cancel.setHorizontalAlignment(SwingConstants.CENTER);
		cancel.setVerticalAlignment(SwingConstants.CENTER);
		cancel.setBounds(largeur/8+2,hauteur*3/16,largeur/8-8, hauteur/32-2);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		//g.getF().getSup().setBackground(Color.yellow);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
		
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		boolean v = false;
		if (evt.getSource()instanceof JButton)  {
			String ChoixOption = evt.getActionCommand();
			if (ChoixOption.equals("OK")){
				g.getSettings().setProbleme(false);
				
				
				for (int i=0; i<g.getSettings().getNumTable();i++){
					if(g.getTables()[i].isOpened()){
						//g.getTables()[i].closeTable();
						v = true;
					}
				}
				if(v){
					// si pas toutes les tables sont fermees
					// on fait en cas de problemes
					g.getSettings().setNumTable(g.getTables().length);
					g.getSettings().setProbleme(true);
					//g.getSettings().setBil(g.getTables());
					
					g.writeSettings("settings.spk");
					g.writeTablesInFile("tables.dat");
					System.exit(0);
					
				}
				else {
					g.writeSettings("settings.spk");
					g.writeTotalInFile("total.tot");
					g.writeTablesInFile("tables.dat");
					System.exit(0);
				}
			}
			else {
				this.dispose();
			}
		}
	}
}
