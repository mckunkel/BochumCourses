package statStarAntonius;

// Compute density, opacity, and energy generation rate at given parameters.
// Since it is not possible for a Java function to return multiple parameters,
// they are returned in the EquationOfState object. The function evaluation
// happens in the object's constructor. Naturally, all of the object's fields
// are thus final.
public class EquationOfState {

	final double rho;
	final double kappa;
	final double epsilon;

	public EquationOfState(double T, double p, StatStar statStar) {
		rho = computeRho(T, p, statStar.mu);
		kappa = computeKappa(T, statStar.X, statStar.Z);
		epsilon = computeEpsilon(T, statStar.X, statStar.XCNO);
	}

	private double computeRho(double T, double p, double mu) {
		double pRad = Const.a * Math.pow(T, 4) / 3.;
		double pGas = p - pRad;

		return mu * Const.mH / Const.kB * pGas / T;
	}

	private double computeKappa(double T, double X, double Z) {
		double tOverGbf = 2.82 * Math.pow(rho * (1 + X), 0.2);

		double k_bf = 4.34e25 / tOverGbf * Z * (1.0 + X) * rho / Math.pow(T, 3.5);
		double k_ff = 3.68e22 * (1.0 - Z) * (1.0 + X) * rho / Math.pow(T, 3.5);
		double k_e = 0.2 * (1.0 + X);
		return k_bf + k_ff + k_e;
	}

	private double computeEpsilon(double T, double X, double XCNO) {
		double T6 = T * 1e-6;
		double T613 = Math.pow(T6, 1.0 / 3.0);
		double T623 = Math.pow(T6, 2.0 / 3.0);

		double fx = 0.133 * X * Math.sqrt((3.0 + X) * rho) / Math.pow(T6, 1.5);
		double fpp = 1.0 + fx * X;
		double psipp = 1.0 + 1.412e8 * (1.0 / X - 1.0) * Math.exp(-49.98 / T613);
		double Cpp = 1.0 + 0.0123 * T613 + 0.0109 * T623 + 0.000938 * T6;
		double epspp = 2.38e6 * rho * X * X * fpp * psipp * Cpp / T623 * Math.exp(-33.8 / T613);
		double CCNO = 1.0 + 0.0027 * T613 - 0.00778 * T623 - 0.000149 * T6;
		double epsCNO = 8.67e27 * rho * X * XCNO * CCNO / T623 * Math.exp(-152.28 / T613);

		return epspp + epsCNO;
	}
}
