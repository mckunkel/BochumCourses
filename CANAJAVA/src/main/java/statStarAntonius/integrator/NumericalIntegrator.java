package statStarAntonius.integrator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Generic numerical integrator superclass. This also provides convenience
// default implementations to provide an IntegrationStepEvent notifier and
// handle starting and stopping the simulation.
// In most cases, subclassed implementations only need to provide their own step
// function; to take full advantage of the features provided by this superclass,
// implementations should ensure to call super.step() in their step function.
public abstract class NumericalIntegrator {

	protected double dt;
	protected double t;
	protected StateVector stateVector;
	protected Function<IntegrationState, StateVector> differential;

	private List<IntegrationStepEventListener> listeners;
	private double final_t;
	private boolean finished;

	public NumericalIntegrator(double dt, double initial_t, double final_t, StateVector initial_state,
			Function<IntegrationState, StateVector> differential) {
		this.dt = dt;
		this.t = initial_t;
		this.stateVector = initial_state;
		this.differential = differential;
		this.listeners = new ArrayList<>();
		this.final_t = final_t;
		this.finished = false;
	}

	public void run() {
		while (!this.finished) {
			step();
		}
	}

	public void step() {
		if (!this.finished) {
			if (((this.dt > 0) && (this.t > this.final_t)) || ((this.dt < 0) && (this.t < this.final_t))) {
				this.finished = true;
			}
			notifyListeners(new IntegrationState(this.t, this.stateVector));
		}
	}

	public double getDt() {
		return dt;
	}

	public double getT() {
		return t;
	}

	public StateVector getStateVector() {
		return stateVector;
	}

	public Function<IntegrationState, StateVector> getDifferential() {
		return differential;
	}

	public double getFinal_t() {
		return final_t;
	}

	public boolean isFinished() {
		return finished;
	}

	public void stop() {
		this.finished = true;
	}

	public void addListener(IntegrationStepEventListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(IntegrationStepEventListener listener) {
		this.listeners.remove(listener);
	}

	private void notifyListeners(IntegrationState integrationState) {
		for (IntegrationStepEventListener listener : this.listeners) {
			listener.nextIntegrationStep(new IntegrationStepEvent(integrationState, this.finished));
		}
	}
}
