package statStarAntonius;

//Extended state for statStar integration, contains values for opacity, density, energy generation rate (which are not updated in the normal RK4 step), as well as the various flags (Igoof, irc) and other weird variables (tog_bf)
//This is kind of inelegant, since it basically has multiple roles:
//1. storing unchanging simulation parameters, which are set once in the beginning (mu, X, Y, Z, XCNO)
//2. storing changing simulation values (irc,  tOverGbf, kPad)
//3. serving as a way to evaluate the equation of state based on two explicit parameters (T and p) and the simulation parameters it also stores. Because Java does not have a simple way to return multiple values, the return values of the equation of state are _also_ stored in this class
//This is kinda ugly but honestly, so was the original StatStar code and I don't have the nerve right now to come up with a better solution
public class StatStarState {

	double rho;
	double kappa;
	double epsilon;

	public StatStarState(double T, double p, double mu, double X, double Y, double Z, double XCNO) {
		computeRho(T, p, mu);
		computeKappa(T, X, Z);
		computeEpsilon(T, X, XCNO);
	}

	public void computeRho(double T, double p, double mu) {
		double pRad = Const.a * Math.pow(T, 4) / 3.;
		double pGas = p - pRad;

		rho = mu * Const.mH / Const.kB * pGas / T;
	}

	public void computeKappa(double T, double X, double Z) {
		double tOverGbf = 2.82 * Math.pow(rho * (1 + X), 0.2);

		double k_bf = 4.34e25 / tOverGbf * Z * (1.0 + X) * rho / Math.pow(T, 3.5);
		double k_ff = 3.68e22 * (1.0 - Z) * (1.0 + X) * rho / Math.pow(T, 3.5);
		double k_e = 0.2 * (1.0 + X);
		kappa = k_bf + k_ff + k_e;
	}

	public void computeEpsilon(double T, double X, double XCNO) {
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

		epsilon = epspp + epsCNO;
	}
}
