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

	public NumericalIntegrator integrator;

	public static final double sigma = 5.67e-8;

	public Function<IntegrationState, StateVector> makeDifferential() {
		Function<IntegrationState, StateVector> differential = state -> {
			StatStar statStar = this; // capture the statStar instance locally so we can access the updated irc that
										// is computed each simulation step

			double r = state.t;
			double p = state.stateVector.state[StatStar.p];
			double L = state.stateVector.state[StatStar.L];
			double T = state.stateVector.state[StatStar.T];
			double M = state.stateVector.state[StatStar.M];
			EquationOfState eos = new EquationOfState(T, p, statStar);

			double dP = -Const.G * eos.rho * M / (r * r);
			double dL = eos.epsilon * eos.rho * 4 * Math.PI * (r * r);
			double dT;
			if (!irc) {
				dT = -3 / (16 * Math.PI * Const.a * (r * r)) * eos.kappa * eos.rho / Math.pow(T, 3) * L;
			} else {
				dT = -1 / Const.gamrat * Const.G * M / (r * r) * mu * Const.mH / Const.kB;
			}
			double dM = eos.rho * 4 * Math.PI * (r * r);
			return new StateVector(new double[] { dP, dL, dT, dM });
		};
		return differential;
	}

	public StatStar(double L, double M, double R, boolean useRK4, double X, double Y, double Z) {
		this.X = X;
		this.Y = Y;
		this.Z = Z;

		double dt = 0; // placeholder
		double finalt = 0; // placeholder

		StateVector initialState = new StateVector(new double[] { 0, L, 0, M });
		if (useRK4) {
			integrator = new RK4Integrator(dt, R, finalt, initialState, makeDifferential());
		} else {
			integrator = new EulerIntegrator(dt, R, finalt, initialState, makeDifferential());
		}
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
			irc = true;
		} else {
			irc = false;
		}

	}

}
