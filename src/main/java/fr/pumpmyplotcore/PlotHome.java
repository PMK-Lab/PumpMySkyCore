package fr.pumpmyplotcore;

public class PlotHome {

	private double x;
	private double y;
	private double z;
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	
	public PlotHome(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof PlotHome && this.x == ((PlotHome) obj).getX() && this.y == ((PlotHome) obj).getY() && this.z == ((PlotHome) obj).getZ();
	}
	
}
