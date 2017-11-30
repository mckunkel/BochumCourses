package numericalIntegrationHomework_Antonius;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import domain.utils.Constants;

public class PressureIntegration {

	public class State extends StateVector {

		public State(double[] state) {
			super(state);

			if (state.length != 1) {
				throw new IllegalArgumentException("State vector must have exactly one element");
			}
		}

		public State(double P) {
			this(new double[] { P });
		}

		public PressureIntegration.State copy() {
			return new PressureIntegration.State(super.copy().state);
		}

		public double getP() {
			return this.state[0];
		}
	}

	static double rho;

	public static final Function<Pair<Double, StateVector>, StateVector> differential = state -> {
		double r = state.getLeft();
		double dP = -4. / 3. * Math.PI * Constants.gravitationalConstant * (rho * rho) * r;
		return new PressureIntegration().new State(dP);
	};

	// this is actually not really optimal at this point, especially with the value
	// of rho being captured from the class, but I'll wait to see how this will get
	// integrated into the GUI before I improve this.
	public static NumericalIntegrator makeIntegrator(double initial_radius, double initial_pressure, boolean use_RK4) {

		NumericalIntegrator integrator;

		if (use_RK4) {
			integrator = new RK4Integrator(-.1, initial_radius, new PressureIntegration().new State(initial_pressure),
					differential);
		} else {
			integrator = new EulerIntegrator(-.1, initial_radius, new PressureIntegration().new State(initial_pressure),
					differential);
		}

		return integrator;
	}

	public static void main(String[] args) {
		rho = 1.;

		NumericalIntegrator integrator = makeIntegrator(100., 1, true);

		for (int i = 0; i < 1000; i++) {
			integrator.step();
			System.out.println();
			System.out.println(((State) integrator.getState()).getP());
			System.out.println(integrator.getT());
		}

	}

}
