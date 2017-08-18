import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MiniOptionsFrame extends JFrame implements ActionListener{
	

	private Billard b;
	private int tableNumber;
	private JLabel text;
	private JTextField cout;
	private JLabel labelPause;
	private JButton pause;
	private JButton ok;
	private JButton cancel;
	
	
	MiniOptionsFrame(Billard b){
		this.b =b;
		
		//calcul des dimensions
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int)tailleEcran.getHeight();
		int largeur = (int)tailleEcran.getWidth();
		
		setTitle("options de la table "+(tableNumber+4));
		setBounds(largeur*3/8, hauteur*3/8, largeur/4, hauteur/4);
		
//		gestion evenementielle de la fermeture de la fenêtre
	    addWindowListener(new WindowAdapter()
	           {public void windowClosing(WindowEvent e)
	                  {
	        	   		Fenetre.g.getF().setBlocked(false);
	                  }
	             } ) ;
		
		Container contentPane = getContentPane();
	    contentPane.setLayout(new GridLayout());

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(4,3));
	   contentPane.add(content);
	    
	    text = new JLabel();
	    text.setText("Prix de la table");
	    text.setHorizontalAlignment(SwingConstants.CENTER);
	    content.add(text);

		cout = new JTextField(""+b.getCost());
		content.add(cout);

		labelPause = new JLabel();
		if(b.isStandBy()){
			labelPause.setText("reprendre");
		}
		else{
			labelPause.setText("pause");
		}
		text.setHorizontalAlignment(SwingConstants.CENTER);
		content.add(labelPause);		

		pause = new JButton("bouton pause");
		
		pause.addActionListener(this);
		content.add(pause);
		
		content.add(new JLabel());
		content.add(new JLabel());

		ok = new JButton("OK");
		ok.setHorizontalAlignment(SwingConstants.CENTER);
		ok.setVerticalAlignment(SwingConstants.CENTER);
		ok.addActionListener(this);
		content.add(ok);
		
		cancel = new JButton("annuler");
		cancel.setHorizontalAlignment(SwingConstants.CENTER);
		cancel.setVerticalAlignment(SwingConstants.CENTER);
		cancel.addActionListener(this);
		content.add(cancel);
		//g.getF().getSup().setBackground(Color.yellow);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
		
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource()instanceof JButton)  {
			String ChoixOption = evt.getActionCommand();
			if (ChoixOption.equals("OK")){
					b.setCost(Double.parseDouble(cout.getText()));
					if(labelPause.getText().equals("pause")){
						b.setStandBy(false);
					}
					else{
						b.setStandBy(true);
					}
					b.getTab()[1].setText(""+b.getCost());
					b.getTab()[6].setText(""+b.getCost());
				this.dispose();
			}

			else if(ChoixOption.equals("annuler")){
				this.dispose();
			}

			else{
				if(labelPause.getText().equals("pause")){
					labelPause.setText("reprendre");
				}
				else{
					labelPause.setText("pause");
				}	
			}

		}
	}
}
