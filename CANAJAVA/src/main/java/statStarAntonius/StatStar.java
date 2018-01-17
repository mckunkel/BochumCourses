package statStarAntonius;

import java.util.function.Function;

import domain.utils.Constants;
import statStarAntonius.integrator.EulerIntegrator;
import statStarAntonius.integrator.IntegrationState;
import statStarAntonius.integrator.NumericalIntegrator;
import statStarAntonius.integrator.RK4Integrator;
import statStarAntonius.integrator.StateVector;

public class StellarIntegrator {

	public static final int p = 0;
	public static final int L = 1;
	public static final int T = 2;

	public static final double sigma = 5.67e-8;

	public static Function<IntegrationState, StateVector> makeDifferential(double rho, double epsilon, double kappa) {
		Function<IntegrationState, StateVector> differential = state -> {
			double r = state.t;
			double L = state.stateVector.state[StellarIntegrator.L];
			double T = state.stateVector.state[StellarIntegrator.T];
			double dP = -4. / 3. * Math.PI * Constants.gravitationalConstant * (rho * rho) * r;
			double dL = epsilon * rho * 4 * Math.PI * (r * r);
			double dT = -3 / (16 * Math.PI * 4 * sigma * (r * r)) * kappa * rho / Math.pow(T, 3) * L;
			return new StateVector(new double[] { dP, dL, dT });
		};
		return differential;
	}

	public static NumericalIntegrator makePressureIntegrator(double rho, double initial_radius, double final_radius,
			double initial_pressure, double epsilon, double kappa, double initial_L, double initial_T,
			boolean use_RK4) {

		Function<IntegrationState, StateVector> differential = makeDifferential(rho, epsilon, kappa);
		NumericalIntegrator integrator;

		if (use_RK4) {
			integrator = new RK4Integrator(-.1, initial_radius, final_radius,
					new StateVector(new double[] { initial_pressure, initial_L, initial_T }), differential);
		} else {
			integrator = new EulerIntegrator(-.1, initial_radius, final_radius,
					new StateVector(new double[] { initial_pressure, initial_L, initial_T }), differential);
		}

		return integrator;
	}

	public static void main(String[] args) {

		// NumericalIntegrator integrator = makePressureIntegrator(1000., 100., 0., 1.,
		// false);
		// integrator.run();
		// System.out.println(integrator.getStateVector().state[0]);

		// for (int i = 0; i < 1000; i++) {
		// integrator.integrator.step();
		// System.out.println();
		// System.out.println(integrator.integrator.getStateVector().state[0]);
		// System.out.println(integrator.integrator.getT());
		// }

	}

}
