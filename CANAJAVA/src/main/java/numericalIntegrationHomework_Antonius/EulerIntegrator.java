package numericalIntegrationHomework_Antonius;

import java.util.function.Function;

public class EulerIntegrator extends NumericalIntegrator {

	public EulerIntegrator(double dt, double initial_t, double final_t, StateVector initial_state,
			Function<IntegrationState, StateVector> differential) {
		super(dt, initial_t, final_t, initial_state, differential);
	}

	public void step() {
		StateVector delta = this.differential.apply(new IntegrationState(t, this.stateVector));
		this.stateVector = this.stateVector.plus(delta.times(this.dt));
		this.t += this.dt;
		super.step();
	}
}
