package numericalIntegrationHomework_Antonius;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

public class EulerIntegrator extends NumericalIntegrator {

	public EulerIntegrator(double dt, double initial_t, StateVector initial_state,
			Function<Pair<Double, StateVector>, StateVector> differential) {
		super(dt, initial_t, initial_state, differential);
	}

	public void step() {
		StateVector delta = this.differential.apply(Pair.of(t, this.state));
		this.state.iplus(delta.times(this.dt));
		this.t += this.dt;
	}
}
