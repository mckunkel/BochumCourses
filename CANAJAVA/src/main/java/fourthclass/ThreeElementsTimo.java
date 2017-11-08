package fourthclass;

import java.util.Arrays;

public class ThreeElementsTimo {

	public static void main(String[] args) {
		// the atom classes do not contain setters for A,M,Z, etc
		// as there is no reason why these values should be changed;
		// they are constants for the individual nuclei.

		// create tritium nucleus, the input argument is the mass number
		// all masses are approximated using the liquid drop model
		HydrogenTimo h2 = new HydrogenTimo(3);

		System.out.println("Tritium Mass = " + h2.getM() + " GeV");
		System.out.println("A = " + h2.getA());
		System.out.println("N = " + h2.getN());
		System.out.println("Z = " + h2.getZ());
		System.out.println("Most abundant isotope of H: A = " + h2.getA());

		System.out.println(" ");

		// create helium nucleus with A = 4 when no input argument is specified ( -->
		// most abundant)
		HeliumTimo he4 = new HeliumTimo();

		System.out.println("4-He Mass = " + he4.getM() + " GeV");
		System.out.println("A = " + he4.getA());
		System.out.println("N = " + he4.getN());
		System.out.println("Z = " + he4.getZ());
		System.out.println("Stable isotopes: A = " + Arrays.toString(he4.getIsotopesA()));

		System.out.println(" ");

		// creating a lithium nucleus with A = 280000 will create a 7-li nucleus instead
		// since it is the most abundant stable lithium nucleus
		LithiumTimo li7 = new LithiumTimo(28000);

		System.out.println("7-Li Mass = " + li7.getM() + " GeV");
		System.out.println("A = " + li7.getA());
		System.out.println("N = " + li7.getN());
		System.out.println("Z = " + li7.getZ());
		System.out.println("Stable isotopes: M = " + Arrays.toString(li7.getIsotopesM()) + " GeV");

		System.out.println(" ");

		// Very good
		// Hopefully later, you will see how you can make this homeowrk much easier with
		// polymorphism
		// grade 1.0/1.0
	}

}
