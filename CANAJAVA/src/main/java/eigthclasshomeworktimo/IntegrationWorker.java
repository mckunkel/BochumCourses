package eigthclasshomeworktimo;

import java.util.ArrayList;
import java.util.List;

import domain.utils.Constants;

public class IntegrationWorker {

	private double G;
	private double c;
	private double a;

	private double rho;
	private double epsilon;
	private double kappa;

	private double R0;
	private double R;
	private int steps;
	private double dr;
	private String methodname;

	private double p;
	private double L;
	private double T;
	private List<IterationEventListener> myListeners;

	public IntegrationWorker(double rho, double epsilon, double kappa, double R0, double R, double L, double T,
			int steps, String methodname) {

		this.G = Constants.gravitationalConstant;
		this.c = Constants.speedOfLight;
		this.a = 4 * 5.67e-8 * c;

		this.rho = rho * 1e3;
		this.epsilon = epsilon * 6.9; // value at 20% of solar radius in W/m^3
		this.kappa = kappa * 1.16 / 10.;

		this.R0 = R0 * Constants.solarRadius;
		this.R = R * Constants.solarRadius;
		this.steps = steps;
		this.dr = (this.R - this.R0) / steps;
		this.methodname = methodname;

		this.p = 0; // boundary condition at surface
		this.L = L * Constants.solarLuminosity;
		this.T = T;

		this.myListeners = new ArrayList<>();
	}

	private double dpdr(double r) {
		return -4. * Math.PI / 3. * G * Math.pow(rho, 2) * r;
	}

	private double dLdr(double r) {
		return epsilon * rho * 4 * Math.PI * Math.pow(r, 2);
	}

	private double dTdr(double T, double L, double r) {
		return -3. / (16. * Math.PI * a * c * Math.pow(r, 2)) * kappa * rho / Math.pow(T, 3) * L;
	}

	private void stepEuler(double r) {
		double pNew = p + dpdr(r) * dr;
		double LNew = L + dLdr(r) * dr;
		double TNew = T + dTdr(T, L, r) * dr;

		p = pNew;
		L = LNew;
		T = TNew;
	}

	private void stepRK4(double r) {
		// RK4 implementation using wikipedia notation

		double p1 = dpdr(r);
		double p2 = dpdr(r + dr / 2.);
		double p3 = dpdr(r + dr / 2.);
		double p4 = dpdr(r + dr);

		double L1 = dLdr(r);
		double L2 = dLdr(r + dr / 2.);
		double L3 = dLdr(r + dr / 2.);
		double L4 = dLdr(r + dr);

		double T1 = dTdr(T, L, r);
		double T2 = dTdr(T + dr / 2. * T1, L + dr / 2. * L1, r + dr / 2.);
		double T3 = dTdr(T + dr / 2. * T2, L + dr / 2. * L2, r + dr / 2.);
		double T4 = dTdr(T + dr * T3, L + dr * L3, r + dr);

		p = p + dr / 6. * (p1 + 2 * p2 + 2 * p3 + p4);
		L = L + dr / 6. * (L1 + 2 * L2 + 2 * L3 + L4);
		T = p + dr / 6. * (T1 + 2 * T2 + 2 * T3 + T4);
	}

	public void iterateToR() {

		double r = R0;

		notifymyListeners(p, L, T, r, 0, true, false, 0.);

		for (int i = 1; i <= steps; i++) {

			switch (methodname) {
			case "Euler":
				stepEuler(r);
			case "RK4":
				stepRK4(r);
			}

			r += dr;

			notifymyListeners(p, L, T, r, i, false, false, i * 100. / steps);
		}

		notifymyListeners(p, L, T, r, steps, false, true, 100.);

	}

	public void addListener(IterationEventListener listener) {
		this.myListeners.add(listener);
	}

	public void removeListener(IterationEventListener listener) {
		this.myListeners.remove(listener);

	}

	private void notifymyListeners(double p, double L, double T, double r, int step, boolean started, boolean finished,
			double progress) {
		IterationEvent event = new IterationEvent(p, L, T, r, step, started, finished, progress);
		for (IterationEventListener iterationEventListener : this.myListeners) {
			IterationEventListener iListener = (IterationEventListener) iterationEventListener;
			iListener.nextIteration(event);
		}
	}

}
