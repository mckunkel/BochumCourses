package numericalIntegrationHomework_Antonius;

import java.util.function.Function;

import domain.utils.Constants;

public class PressureIntegrator {

	public static Function<IntegrationState, StateVector> makeDifferential(double rho) {
		Function<IntegrationState, StateVector> differential = state -> {
			double r = state.t;
			double dP = -4. / 3. * Math.PI * Constants.gravitationalConstant * (rho * rho) * r;
			return new StateVector(new double[] { dP });
		};
		return differential;
	}

	public static NumericalIntegrator makePressureIntegrator(double rho, double initial_radius, double final_radius,
			double initial_pressure, boolean use_RK4) {

		Function<IntegrationState, StateVector> differential = makeDifferential(rho);
		NumericalIntegrator integrator;

		if (use_RK4) {
			integrator = new RK4Integrator(-.1, initial_radius, final_radius,
					new StateVector(new double[] { initial_pressure }), differential);
		} else {
			integrator = new EulerIntegrator(-.1, initial_radius, final_radius,
					new StateVector(new double[] { initial_pressure }), differential);
		}

		return integrator;
	}

	public static void main(String[] args) {

		NumericalIntegrator integrator = makePressureIntegrator(1000., 100., 0., 1., false);
		integrator.run();
		System.out.println(integrator.getStateVector().state[0]);

		// for (int i = 0; i < 1000; i++) {
		// integrator.integrator.step();
		// System.out.println();
		// System.out.println(integrator.integrator.getStateVector().state[0]);
		// System.out.println(integrator.integrator.getT());
		// }

	}

}
