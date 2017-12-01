package eigthclasshomeworktimo;

import domain.utils.Constants;

public class IntegrateODE {

	protected static double dpdr(double r, double rho) {
		double G = Constants.gravitationalConstant;
		return -4. * Math.PI / 3. * G * Math.pow(rho, 2) * r;
	}

	protected static double stepEuler(double p, double r, double dr, double rho) {
		return p + dpdr(r, rho) * dr;
	}

	protected static double stepRK4(double p, double r, double dr, double rho) {
		// RK4 implementation using wikipedia notation

		double k1 = dpdr(r, rho);
		double k2 = dpdr(r + dr / 2., rho);
		double k3 = dpdr(r + dr / 2, rho);
		double k4 = dpdr(r + dr, rho);

		return p + dr / 6. * (k1 + 2 * k2 + 2 * k3 + k4);
	}

	protected static double iterateToR(double R0, double R, double rho, int steps, String usemethod) {
		// input: radius R0 of the star in solar radii, radius R where the pressure p
		// in pa should be calculated, density in solar densities of the star, number of
		// integration steps and String that specifies the integration method.

		rho *= 1.41e3;
		R0 *= Constants.solarRadius;
		R *= Constants.solarRadius;

		double dr = (R - R0) / steps;
		double p = 0;
		double r = R0;

		switch (usemethod) {

		case "Euler":
			for (int i = 0; i < steps; i++) {
				p = stepEuler(p, r, dr, rho);
				r += dr;
			}
			;
			break;

		case "RK4":
			for (int i = 0; i < steps; i++) {
				p = stepRK4(p, r, dr, rho);
				r += dr;
			}
			;
			break;
		}

		return p;
	}

	public static void main(String[] args) {
		System.out.println(iterateToR(1., 0.7, 1., 1000, "Euler"));
		System.out.println(iterateToR(1., 0.7, 1., 1000, "RK4"));
	}

}
