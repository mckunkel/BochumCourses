package numericalIntegrationHomework_Antonius;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

public class RK4Integrator extends NumericalIntegrator {

	public RK4Integrator(double dt, double initial_t, StateVector initial_state,
			Function<Pair<Double, StateVector>, StateVector> differential) {
		super(dt, initial_t, initial_state, differential);
	}

	@Override
	public void step() {
		StateVector k1 = this.differential.apply(Pair.of(this.t, this.state));
		StateVector k2 = this.differential.apply(Pair.of(this.t + this.dt / 2, this.state.plus(k1.times(dt / 2))));
		StateVector k3 = this.differential.apply(Pair.of(this.t + this.dt / 2, this.state.plus(k2.times(dt / 2))));
		StateVector k4 = this.differential.apply(Pair.of(this.t + this.dt, this.state.plus(k3.times(this.dt))));

		this.state.iplus(k1.plus(k2.times(2)).plus(k3.times(2)).plus(k4).times(this.dt / 6));
		this.t += this.dt;
	}

}
