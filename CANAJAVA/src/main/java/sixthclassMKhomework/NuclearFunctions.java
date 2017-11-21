/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package sixthclassMKhomework;

public class NuclearFunctions {

	private NuclearFunctions() {

	}

	public static final double bindingEnergy(int A, int Z) {

		return (volumeTerm(A) - surfaceTerm(A) - coulombTerm(A, Z) - asymmetryTerm(A, Z) + pairingTerm(A, Z));
	}

	private static final double volumeTerm(int A) {
		return Constants.aV * A;
	}

	private static final double surfaceTerm(int A) {
		return Constants.aS * Math.pow(A, 2. / 3.);
	}

	private static final double coulombTerm(int A, int Z) {
		return Constants.aC * (Z * (Z - 1)) / Math.pow(A, 1. / 3.);

	}

	private static final double asymmetryTerm(int A, int Z) {
		return Constants.aA * Math.pow((A - 2 * Z), 2.) / A;
	}

	private static final double pairingTerm(int A, int Z) {
		double retValue = 0.0;
		double delta = Constants.aP / Math.pow(A, 1. / 2.);
		int N = A - Z;

		if (N % 2 == 0 && Z % 2 == 0) {
			retValue = delta; // A is even Z, N even
		} else if ((N % 2 != 0 && Z % 2 == 0) || (N % 2 == 0 && Z % 2 != 0)) {
			retValue = 0.0; // A is odd
		} else if (N % 2 != 0 && Z % 2 != 0) {
			retValue = -delta; // A is even Z, N odd
		} else {
			System.out.println("You should never see this. If you do, you have bad logic");
		}

		return retValue;

	}
}
