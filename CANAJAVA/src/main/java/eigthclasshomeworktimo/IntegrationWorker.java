package eigthclasshomeworktimo;

import java.util.ArrayList;
import java.util.List;

import domain.utils.Constants;

public class IntegrationWorker {

	private double G;
	private double rho;
	private double R0;
	private double R;
	private int steps;
	private double dr;
	private String methodname;

	private double p;
	private List<IterationEventListener> myListeners;

	public IntegrationWorker(double rho, double R0, double R, int steps, String methodname) {

		this.G = Constants.gravitationalConstant;
		this.rho = rho * 1.41e3;
		this.R0 = R0 * Constants.solarRadius;
		this.R = R * Constants.solarRadius;
		this.steps = steps;
		this.dr = (this.R - this.R0) / steps;
		this.methodname = methodname;

		this.p = 0;
		this.myListeners = new ArrayList<>();
	}

	private double dpdr(double r) {
		return -4. * Math.PI / 3. * G * Math.pow(rho, 2) * r;
	}

	private double stepEuler(double p, double r) {
		return p + dpdr(r) * dr;
	}

	private double stepRK4(double p, double r) {
		// RK4 implementation using wikipedia notation

		double k1 = dpdr(r);
		double k2 = dpdr(r + dr / 2.);
		double k3 = dpdr(r + dr / 2.);
		double k4 = dpdr(r + dr);

		return p + dr / 6. * (k1 + 2 * k2 + 2 * k3 + k4);
	}

	public void iterateToR() {

		double r = R0;

		notifymyListeners(p, r, 0, true, false, 0.);

		for (int i = 1; i <= steps; i++) {

			switch (methodname) {
			case "Euler":
				p = stepEuler(p, r);
			case "RK4":
				p = stepRK4(p, r);
			}
			r += dr;

			notifymyListeners(p, r, i, false, false, i * 100. / steps);
		}

		notifymyListeners(p, r, steps, false, true, 100.);

	}

	public void addListener(IterationEventListener listener) {
		this.myListeners.add(listener);
	}

	public void removeListener(IterationEventListener listener) {
		this.myListeners.remove(listener);

	}

	private void notifymyListeners(double p, double r, int step, boolean started, boolean finished, double progress) {
		IterationEvent event = new IterationEvent(p, r, step, started, finished, progress);
		for (IterationEventListener iterationEventListener : this.myListeners) {
			IterationEventListener iListener = (IterationEventListener) iterationEventListener;
			iListener.nextIteration(event);
		}
	}

}
