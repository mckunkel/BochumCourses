package homeworkAntonius;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.jlab.groot.data.GraphErrors;
import org.jlab.groot.ui.TCanvas;

//This was unique and fun to read
//consider documenting your codes
//grade 1.0
public class DecayChain {

	public static void main(String[] args) {

		double A0 = 1;
		double tStep = 1.;
		double tEnd = 10 * 60;

		double tauA = 10 * 60;
		double tauB = 1 * 60;

		GraphErrors graphB1 = new GraphErrors();
		graphB1.setTitle("Behavior for tau_a=10min, tau_b=1min");
		graphB1.setTitleX("t / min");
		graphB1.setTitleY("Amount of nuclide B");
		graphB1.setMarkerSize(3);
		new SimulateDecayChain(A0, tauA, tauB, tStep, tEnd).forEachRemaining(state -> {
			if (state.i % 10 == 0) { // show only every tenth simulation step
				graphB1.addPoint(state.t / 60, state.B, 0, 0);
			}
		});
		TCanvas canvas1 = new TCanvas("plot 1", 640, 400);
		canvas1.draw(graphB1);

		tauA = 1 * 60;
		tauB = 10 * 60;

		GraphErrors graphB2 = new GraphErrors();
		graphB2.setTitle("Behavior for tau_a=1min, tau_b=10min");
		graphB2.setTitleX("t / min");
		graphB2.setTitleY("Amount of nuclide B");
		graphB2.setMarkerSize(3);
		new SimulateDecayChain(A0, tauA, tauB, tStep, tEnd).forEachRemaining(state -> {
			if (state.i % 10 == 0) {
				graphB2.addPoint(state.t / 60, state.B, 0, 0);
			}
		});
		TCanvas canvas2 = new TCanvas("plot 2", 640, 400);
		canvas2.draw(graphB2);

		tauA = 1 * 60;
		tauB = 1 * 60;

		GraphErrors graphB3 = new GraphErrors();
		graphB3.setTitle("Behavior for tau_a=1min, tau_b=1min");
		graphB3.setTitleX("t / min");
		graphB3.setTitleY("Amount of nuclide B");
		graphB3.setMarkerSize(3);
		GraphErrors graphC3 = new GraphErrors();
		graphC3.setTitleY("Amount of nuclide C");
		graphC3.setMarkerSize(3);
		graphC3.setMarkerColor(2);
		new SimulateDecayChain(A0, tauA, tauB, tStep, tEnd).forEachRemaining(state -> {
			if (state.i % 10 == 0) {
				graphB3.addPoint(state.t / 60, state.B, 0, 0);
				graphC3.addPoint(state.t / 60, state.C, 0, 0);
			}
		});
		TCanvas canvas3 = new TCanvas("plot 3", 640, 400);
		canvas3.draw(graphB3);
		canvas3.draw(graphC3, "same");
	}
}

class SimulationState {
	double A, B, C, t;
	int i;

	public SimulationState(double A, double B, double C, double t) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.t = t;
		this.i = 0;
	}
}

class SimulateDecayChain implements Iterator<SimulationState> {

	double tauA, tauB, tStep, tEnd;
	SimulationState state;

	public SimulateDecayChain(double A0, double tauA, double tauB, double tStep, double tEnd) {
		this.tauA = tauA;
		this.tauB = tauB;
		this.tStep = tStep;
		this.tEnd = tEnd;
		state = new SimulationState(A0, 0., 0., 0.);
	}

	@Override
	public boolean hasNext() {
		if (this.state.t < this.tEnd) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SimulationState next() {
		if (state.t >= tEnd) {
			throw new NoSuchElementException();
		}

		double dA = -state.A / tauA;
		double dB = -state.B / tauB + state.A / tauA;
		double dC = state.B / tauB;

		state.A += dA * tStep;
		state.B += dB * tStep;
		state.C += dC * tStep;
		state.t += tStep;

		state.i += 1;

		return state;
	}

}