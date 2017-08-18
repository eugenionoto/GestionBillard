import java.util.*;

public class RepetAction {
  private Timer t;
  private Billard b;

  public RepetAction(Billard b){
	
	  this.b = b;
	  t = new Timer();
	  t.scheduleAtFixedRate(new MonAction(), 0, 1000);
    }

  class MonAction extends TimerTask {
	    

	    public void run() {
	      if (b.isOpened()) {
	        //b.setPlayedTime(b.getPlayedTime()+1);
	    	  b.tickAction();
	      } else {
	        t.cancel();
	 
	      }
	    }
	  }

  public Billard getB() {
	  return b;
  }

  public void setB(Billard b) {
	  this.b = b;
  }

  public Timer getT() {
	  return t;
  }

  public void setT(Timer t) {
	  this.t = t;
  }
}
