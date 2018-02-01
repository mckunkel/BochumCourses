package statStarAntonius;

import java.util.function.Function;

import statStarAntonius.integrator.EulerIntegrator;
import statStarAntonius.integrator.IntegrationState;
import statStarAntonius.integrator.IntegrationStepEvent;
import statStarAntonius.integrator.IntegrationStepEventListener;
import statStarAntonius.integrator.NumericalIntegrator;
import statStarAntonius.integrator.RK4Integrator;
import statStarAntonius.integrator.StateVector;

// This class contains the StatStar implementation. It handles the surface
// pre-integration, and sets up the main body integration using either an RK4 or
// Euler integrator.
public class StatStar implements IntegrationStepEventListener {

	public static final int ip = 0;
	public static final int iL = 1;
	public static final int iT = 2;
	public static final int iM = 3;

	double mu;
	double X;
	double Y;
	double Z;
	double XCNO;

	final double Rs;
	final double Ms;
	final double Ls;

	private double prevP; // these store the value of p and T at the previous simulation step
	private double prevT; //

	boolean irc = false;
	double tOverGbf = 0.01;
	double kPad = 0.3; // "arbitrary value" -- statstar.f

	public EquationOfState extendedState;

	public NumericalIntegrator integrator;

	public static final double sigma = 5.67e-8;

	public Function<IntegrationState, StateVector> makeDifferential() {
		Function<IntegrationState, StateVector> differential = state -> {
			StatStar statStar = this; // capture the statStar instance locally so we can access the updated irc that
										// is computed each simulation step

			double r = state.t;
			double p = state.stateVector.state[StatStar.ip];
			double L = state.stateVector.state[StatStar.iL];
			double T = state.stateVector.state[StatStar.iT];
			double M = state.stateVector.state[StatStar.iM];
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

	public StatStar(double L, double M, double R, double dR, boolean useRK4, double X, double Y, double Z) {
		this.X = X;
		this.Y = Y;
		this.Z = Z;
		this.XCNO = Z / 2.;
		this.mu = 1. / (2. * X + 0.75 * Y + 0.5 * Z);

		this.Ms = M;
		this.Ls = L;
		this.Rs = R;

		double finalt = 0; // TODO: placeholder

		StateVector initialState = surfaceIntegration(L, M, R, dR);
		if (useRK4) {
			integrator = new RK4Integrator(dR, R, finalt, initialState, makeDifferential());
		} else {
			integrator = new EulerIntegrator(dR, R, finalt, initialState, makeDifferential());
		}

		integrator.addListener(this);
	}

	@Override
	public void nextIntegrationStep(IntegrationStepEvent event) {
		// this is basically an integration step hook that will perform the other
		// necessary tasks for each integration step, such as checking boundary
		// conditions and updating irc

		double p = event.integrationState.stateVector.state[StatStar.ip];
		double T = event.integrationState.stateVector.state[StatStar.iT];

		double dlPdlT = Math.log(p / this.prevP) / Math.log(T / this.prevT);
		if (dlPdlT < Const.gamrat) {
			irc = true;
		} else {
			irc = false;
		}

	}

	private StateVector surfaceIntegration(double L, double M, double R, double dR) {
		double T = 0;
		double p = 0;

		for (int i = 0; i < 20; i++) { // TODO: ask for # surface steps in GUI?
			R += dR;
			if (!irc) {
				T = Const.G * M * mu * Const.mH / (4.25 * Const.kB) * (1.0 / R - 1.0 / Rs);
				double A_bf = 4.34e25 * Z * (1.0 + X) / tOverGbf;
				double A_ff = 3.68e22 * (1.0 - Z) * (1.0 + X);
				double Afac = A_bf + A_ff;
				p = Math.sqrt((1.0 / 4.25) * (16.0 / 3.0 * Math.PI * Const.a * Const.c) * (Const.G * M / L)
						* (Const.kB / (Afac * mu * Const.mH))) * Math.pow(T, 4.25);
			} else {

				T = Const.G * M * mu * Const.mH / Const.kB * (1.0 / R - 1.0 / Rs) / Const.gamrat;
				p = kPad * Math.pow(T, Const.gamrat);
			}
			EquationOfState eos = new EquationOfState(T, p, this);
			double dM = 4 * Math.PI * eos.rho * (R * R);
			M += dM;

			this.prevT = T;
			this.prevP = p;
			if (Math.abs(dM) > .001 * Ms) {
				break;
			}
		}

		return new StateVector(new double[] { p, L, T, M });
	}

}
