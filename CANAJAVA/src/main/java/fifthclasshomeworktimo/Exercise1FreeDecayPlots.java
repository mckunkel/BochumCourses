package fifthclasshomeworktimo;

//grade 1.0
//well documented

import org.jlab.groot.data.GraphErrors;
import org.jlab.groot.ui.TCanvas;

public class Exercise1FreeDecayPlots {
	// The different plots for varying time constants
	// are produced in a static method and saved as pdf.
	// The procedure is always the same: Objects of class Nuclide
	// are created with abundance and tau as variables and in each time step,
	// their quantity is updated via exponential decay.

	public static void plotDecay(double tauA, double tauB, String str) {

		double N = 1.;
		double dt = 0.01; // time step in minutes

		Nuclide a = new Nuclide(tauA, N);
		Nuclide b = new Nuclide(tauB, 0);
		Nuclide c = new Nuclide(0, 0);

		GraphErrors gra = new GraphErrors();
		GraphErrors grb = new GraphErrors();
		GraphErrors grc = new GraphErrors();

		for (int i = 0; i < 2000; i++) {

			b.addN(a.decay(dt)); // add number of decayed A nuclides to number
									// of B
			c.addN(b.decay(dt));

			gra.addPoint(i * dt, a.getN(), 0, 0);
			grb.addPoint(i * dt, b.getN(), 0, 0);
			grc.addPoint(i * dt, c.getN(), 0, 0);

		}

		gra.setTitleX("t [min]");
		gra.setTitleY("N [N_{0,A}]");

		gra.setTitle("[Exercise 1" + str + "] Nuclide A (tau = " + tauA + " min) in black, B (tau = " + tauB
				+ " min) in green, C (stable) in blue");
		gra.setMarkerColor(1);
		gra.setMarkerSize(2);
		grb.setMarkerColor(3);
		grb.setMarkerSize(2);
		grc.setMarkerColor(4);
		grc.setMarkerSize(2);

		TCanvas canvas = new TCanvas("Exercise 1" + str + " Timo", 800, 600);

		canvas.draw(gra);
		canvas.draw(grb, "same");
		canvas.draw(grc, "same");

		// SaveCanvas.saveCanvas(canvas);

		// canvas.dispose();

	}

	public static void main(String[] args) {

		plotDecay(10, 1, "a");
		plotDecay(1, 10, "b");
		plotDecay(1, 1, "c");
	}

}

class Nuclide {

	private double tau;
	private double N;

	public Nuclide(double tau, double N) {

		this.tau = tau; // min
		this.N = N; // number of nuclides at t=0

	}

	public double decay(double t) {
		double h = this.N;
		this.N = this.N * Math.exp(-t / this.tau);
		return h - this.N;
	}

	public void addN(double N) {
		this.N += N;
	}

	public double getN() {
		return this.N;
	}
}
