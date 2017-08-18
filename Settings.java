import java.awt.*;
import java.io.*;

public class Settings implements Serializable{
	
	private int numTable;
	private Color labelColor;
	private Color textColor;
	private double cost;
	private double secondaryCost;
	private boolean probleme;
	private boolean personalise;

	
	Settings(int numTable, Color labelColor,Color textColor, double cost, double secondaryCost, boolean probleme, boolean personnalise){
		this.numTable = numTable;
		this.labelColor = labelColor;
		this.textColor = textColor;
		this.cost = cost;
		this.secondaryCost = secondaryCost;
		this.probleme = probleme;
		this.personalise = personnalise;

		
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getSecondaryCost() {
		return secondaryCost;
	}

	public void setSecondaryCost(double secondaryCost) {
		this.secondaryCost = secondaryCost;
	}

	public Color getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(Color labelColor) {
		this.labelColor = labelColor;
	}

	public int getNumTable() {
		return numTable;
	}

	public void setNumTable(int numTable) {
		this.numTable = numTable;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public boolean isProbleme() {
		return probleme;
	}

	public void setProbleme(boolean probleme) {
		this.probleme = probleme;
	}

	public boolean isPersonalise() {
		return personalise;
	}

	public void setPersonalise(boolean personalise) {
		this.personalise = personalise;
	}
	
	
	
}
