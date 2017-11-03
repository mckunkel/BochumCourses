package groupwork;

import org.jlab.groot.data.H2F;
import org.jlab.groot.ui.TCanvas;

public class LiquidDropModel {
	
	public static double aV = 15.5;
	public static double aS = 16.8;
	public static double aC = 0.715;
	public static double aA = 23.0;
	public static double aP = 11.3;

	
	public static void main (String[] args) {
		int Zmax = 200;
		int Nmax = 200;
		H2F hist = new H2F("Binding energies", Zmax, .5, Zmax + 0.5, Nmax, 0.5, Nmax + 0.5);
				
		for (int Z=1; Z<=Zmax; Z++) {
			for (int N=1; N<=Nmax; N++) {
				hist.setBinContent(Z-1, N-1, B(N, Z));
			}
		}
		
		TCanvas canvas = new TCanvas("canvas", 800, 600);
		canvas.draw(hist);
	}
	
	public static double B (int N, int Z) {
		int A = N + Z;

		double delta = aP / Math.sqrt(A);
		double delta_EP;
		
		if ((N*Z)%2 == 1) { //oe, eo
			delta_EP = 0;
		}
		
		else {
			if (N%2 == 0) { //ee
				delta_EP = delta;
			}
			else { //oo
				delta_EP = -delta;
			}
		}
		
		return (aV*A - aS*Math.pow(A, 2/3.) - aC * Z*Z / Math.pow(A, 1/3.) - aA * (Z-N)*(Z-N) / A + delta_EP)/A;
	}
	
}