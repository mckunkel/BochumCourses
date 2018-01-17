package statStarAntonius;

public class Const {

	private Const() {
	}

	protected static double pi = Math.PI;
	protected static double c = 2.99792458e10; // cm/s
	protected static double gravitationalConstant = 6.67300e-8;// dyne cm^2/g^2;
	protected static double kB = 1.380658e-16; // erg/Kelvin
	protected static double sigma = 5.67051e-5; // erg / cm^2 / s / K^4
	protected static double a = 4. * sigma / c;
	protected static double mSolar = 1.989E33; // g
	protected static double rSolar = 6.9599e10; // cm
	protected static double lSolar = 3.826e33; // erg/s
	protected static double tSolar = 5770; // kelvin
	protected static double mH = 1.673534e-24; // g
	protected static double gamma = 5. / 3.; // adiabatic gamma for a momatomic
												// gas
	protected static double gamrat = gamma / (gamma - 1.0); // delimiter between
															// convection and
															// radiation

	protected static double P0 = 0.0;// surface Pressure
	protected static double T0 = 0.0;// surface temperature

	protected static int nStart = 10;
	protected static int nStop = 1000;
}
