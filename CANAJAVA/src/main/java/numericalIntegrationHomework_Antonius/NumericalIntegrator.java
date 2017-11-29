package numericalIntegrationHomework_Antonius;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

public abstract class NumericalIntegrator {

	protected double dt;
	protected double t;
	protected StateVector state;
	protected Function<Pair<Double, StateVector>, StateVector> differential;

	public NumericalIntegrator(double dt, double initial_t, StateVector initial_state,
			Function<Pair<Double, StateVector>, StateVector> differential) {
		this.dt = dt;
		this.t = initial_t;
		this.state = initial_state;
		this.differential = differential;
	}

	public abstract void step();

	public double getDt() {
		return dt;
	}

	public double getT() {
		return t;
	}

	public StateVector getState() {
		return state;
	}

	public Function<Pair<Double, StateVector>, StateVector> getDifferential() {
		return differential;
	}

}
