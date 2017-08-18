import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 
import java.awt.*;
import javax.swing.*;

public class BillardButtonGUI extends JButton implements ActionListener{
	
	private Billard b;
	
	public BillardButtonGUI(Billard b) {
		this.b = b;
	}
	
	
	public void actionPerformed(ActionEvent event) { 
		//System.out.println(b.isStandBy());
		/*   if(b.isStandBy()){
			   b.stopStandBy();
		   }
		   else{
			   b.standBy();
		   }*/
		  }

	/**
	 * getters / setters
	 * @return
	 */
	public Billard getB() {
		return b;
	}


	public void setB(Billard b) {
		this.b = b;
	}
	
	
}
