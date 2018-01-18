package statStarAntonius;

import java.util.function.Function;

import statStarAntonius.integrator.EulerIntegrator;
import statStarAntonius.integrator.IntegrationState;
import statStarAntonius.integrator.IntegrationStepEvent;
import statStarAntonius.integrator.IntegrationStepEventListener;
import statStarAntonius.integrator.NumericalIntegrator;
import statStarAntonius.integrator.RK4Integrator;
import statStarAntonius.integrator.StateVector;

public class StatStar implements IntegrationStepEventListener {

	public static final int p = 0;
	public static final int L = 1;
	public static final int T = 2;
	public static final int M = 3;

	double mu;
	double X;
	double Y;
	double Z;
	double XCNO;

	private double prevP; // these store the value of p and T at the previous simulation step
	private double prevT; //

	boolean irc;
	double tOverGbf;
	double kPad;

	public EquationOfState extendedState;

	public static final double sigma = 5.67e-8;

	public Function<IntegrationState, StateVector> makeDifferential() {
		Function<IntegrationState, StateVector> differential = state -> {
			EquationOfState extendedState = this.extendedState;
			double r = state.t;
			double L = state.stateVector.state[StatStar.L];
			double T = state.stateVector.state[StatStar.T];
			double M = state.stateVector.state[StatStar.M];

			double dP = -Const.gravitationalConstant * extendedState.rho * M / (r * r);
			double dL = extendedState.epsilon * extendedState.rho * 4 * Math.PI * (r * r);
			double dT = -3 / (16 * Math.PI * Const.a * (r * r)) * extendedState.kappa * extendedState.rho
					/ Math.pow(T, 3) * L; // TODO: irc check
			double dM = extendedState.rho * 4 * Math.PI * (r * r);
			return new StateVector(new double[] { dP, dL, dT, dM });
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

	@Override
	public void nextIntegrationStep(IntegrationStepEvent event) {
		// this is basically an integration step hook that will perform the other
		// necessary tasks for each integration step, such as checking boundary
		// conditions and updating irc

		double p = event.integrationState.stateVector.state[StatStar.p];
		double T = event.integrationState.stateVector.state[StatStar.T];

		double dlPdlT = Math.log(p / this.prevP) / Math.log(T / this.prevT);
		if (dlPdlT < Const.gamrat) {
			irc = 1;
		} else {
			irc = 0;
		}

	}

}
