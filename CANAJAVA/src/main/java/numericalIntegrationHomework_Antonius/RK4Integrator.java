package numericalIntegrationHomework_Antonius;

import java.util.function.Function;

public class RK4Integrator extends NumericalIntegrator {

	public RK4Integrator(double dt, double initial_t, double final_t, StateVector initial_state,
			Function<IntegrationState, StateVector> differential) {
		super(dt, initial_t, final_t, initial_state, differential);
	}

	@Override
	public void step() {
		StateVector k1 = this.differential.apply(new IntegrationState(this.t, this.stateVector));
		StateVector k2 = this.differential
				.apply(new IntegrationState(this.t + this.dt / 2, this.stateVector.plus(k1.times(dt / 2))));
		StateVector k3 = this.differential
				.apply(new IntegrationState(this.t + this.dt / 2, this.stateVector.plus(k2.times(dt / 2))));
		StateVector k4 = this.differential
				.apply(new IntegrationState(this.t + this.dt, this.stateVector.plus(k3.times(this.dt))));

		this.stateVector = this.stateVector.plus(k1.plus(k2.times(2)).plus(k3.times(2)).plus(k4).times(this.dt / 6));
		this.t += this.dt;
		super.step();
	}

}
