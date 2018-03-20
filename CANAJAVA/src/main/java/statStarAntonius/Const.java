package statStarAntonius;

// Physical constants needed by StatStar
public class Const {

	private Const() {
	}

	public static final double pi = Math.PI;
	public static final double c = 2.99792458e10; // cm/s
	public static final double G = 6.67300e-8;// dyne cm^2/g^2;
	public static final double kB = 1.380658e-16; // erg/Kelvin
	public static final double sigma = 5.67051e-5; // erg / cm^2 / s / K^4
	public static final double a = 4. * sigma / c;
	public static final double mSolar = 1.989E33; // g
	public static final double rSolar = 6.9599e10; // cm
	public static final double lSolar = 3.826e33; // erg/s
	public static final double tSolar = 5770; // kelvin
	public static final double mH = 1.673534e-24; // g
	public static final double gamma = 5. / 3.; // adiabatic gamma for a momatomic gas
	public static final double gamrat = gamma / (gamma - 1.0); // delimiter between convection and radiation
}
