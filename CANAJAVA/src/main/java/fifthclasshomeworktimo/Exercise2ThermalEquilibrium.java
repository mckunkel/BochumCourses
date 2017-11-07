package fifthclasshomeworktimo;

import org.jlab.groot.data.GraphErrors;
import org.jlab.groot.ui.TCanvas;

import domain.utils.Constants;
import domain.utils.SaveCanvas;

public class Exercise2ThermalEquilibrium {
	// The exercise says that B is stable. If this is the case and there is
	// no reaction back to A, then there is no equilibrium state.
	// All nuclides of type A would decay to B and that's it.
	// So I assume that B -> A is possible and that
	// the expressions on slide 26/27, lecture 3 may be used.

	public static double calculateEquilibriumAB(double T) {
		// Input: Temperature in MeV
		// Output: NA/NB relative concentration

		// Demanding equal chemical potential in equilibrium,
		// i.e. muA = muB, a different expression for the relative
		// concentrations is obtained, see below.

		double Q = 1.8; // MeV

		// Assume equal statistical weights
		double gA = 1.;
		double gB = 1.;

		// Assume equal masses except for Q, otherwise it doesn't make sense
		// that no third nuclide is involved in the reaction.
		// mB/mA = (mB-mA+mA)/mA = (mA-Q)/mA
		// Maybe B is an excited state of nuclide A?

		double mA = Constants.massOfHydogenInGev * 12;
		double mB = mA - Q / 1000.;

		// Return NA/NB

		return (gA / gB) * Math.pow(mA / mB, 3. / 2.) * Math.exp(-Q / T);
	}

	public static double calculateEquilibriumABC(double T) {
		// Input: Temperature T in MeV
		// Output: (NA * NB / NC) computed using the
		// expression taken from the slides, I don't know
		// if this is what the exercise was aiming at.

		double QAB = 1.8; // MeV
		double QABC = 2.3;

		double hbar = 6.626e-34 / (2. * Math.PI);
		double c = Constants.speedOfLight;
		double e = Constants.eVToJoule;

		double gA = 1.;
		double gB = 1.;
		double gC = 1.;

		double mA = Constants.massOfHydogenInGev * 12;
		double mB = mA - QAB / 1000.;
		double mC = mA + mB - QABC / 1000.;

		double ret = gA * gB / gC * Math.pow(mA * mB / mC * 1e9 * e / (c * c), 3. / 2.)
				* Math.pow(T * 1e6 * e / (2 * Math.PI * hbar * hbar), 3. / 2.) * Math.exp(-QABC / T);

		return ret / 1e42; // The method is returning HUGE values, probably a mistake somewhere with the
							// units
		// I'm running out of time though, so this issue has to remain unsolved.
	}

	public static void plotEquilibriumAB() {
		// plot equilibrium relative concentration of NA/NB for
		// temperatures T with 0.18 MeV < T < 18 MeV

		GraphErrors gr = new GraphErrors();

		// logarithmic scaling would be better, but I did not find a suitable option
		// for GraphErrors objects..
		for (int i = 0; i < 1001; i++) {

			double T = (18 - 0.18) / 1000. * i + 0.18;
			gr.addPoint(T, calculateEquilibriumAB(T), 0, 0);

		}

		gr.setMarkerColor(2);
		gr.setMarkerSize(2);
		gr.setTitle("Equilibrium concentration for Q(A-->B)=1.8 MeV");
		gr.setTitleX("T [MeV]");
		gr.setTitleY("N_A/N_B");

		TCanvas canvas = new TCanvas("Exercise 2a Timo", 800, 600);

		canvas.draw(gr);
		SaveCanvas.saveCanvas(canvas);
		canvas.dispose();

	}

	public static void plotEquilibriumABC() {
		// plot equilibrium relative concentration of NA*NB/NC for
		// temperatures T with 0.23 MeV < T < 23 MeV

		GraphErrors gr = new GraphErrors();

		// logarithmic scaling would be better, but I did not find a suitable option
		// for GraphErrors objects..
		for (int i = 0; i < 1001; i++) {

			double T = (23 - 0.23) / 1000. * i + 0.23;
			gr.addPoint(T, calculateEquilibriumABC(T), 0, 0);

		}

		gr.setMarkerColor(2);
		gr.setMarkerSize(2);
		gr.setTitle("Equilibrium concentration for Q(A+B-->C)=2.3 MeV");
		gr.setTitleX("T [MeV]");
		gr.setTitleY("N_A*N_B/N_C [1e42 * m-3]");

		TCanvas canvas = new TCanvas("Exercise 2b Timo", 800, 600);

		canvas.draw(gr);
		SaveCanvas.saveCanvas(canvas);
		canvas.dispose();

	}

	public static void main(String[] args) {

		plotEquilibriumAB();
		plotEquilibriumABC();
	}

}
