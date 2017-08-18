import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 
import java.awt.*;

import javax.swing.*;

public class BillardGUI extends JLabel implements ActionListener{

	private Billard b;
	
	BillardGUI(Billard b){
		this.b = b;
	}
	
	
	public void actionPerformed(ActionEvent event) { 
	   b.getG().getF1().setBackground(Color.WHITE);
	  }


	public Billard getB() {
		return b;
	}


	public void setB(Billard b) {
		this.b = b;
	} 
	
	
}
