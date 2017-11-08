package fourthclass;

import java.util.stream.IntStream;

import domain.utils.Constants;
import groupwork.LiquidDropModel;

class HydrogenTimo {

	// A,Z and M are the mass number, charge and mass of the objects of class
	// Hydrogen

	// put general information here
	private int MostAbundantA = 1;
	private int Z = 1;
	private int Num = 3; // number of stable isotopes
	private double MostAbundantM = MostAbundantA * Constants.massOfHydogenInGev
			+ LiquidDropModel.returnBindingEnergy(Z, MostAbundantA - Z);

	private int A;
	private double M;

	// these arrays contain information on the existing stable isotopes
	private int[] IsotopesA;
	private double[] IsotopesM;

	private void setIsotopesA() {
		// create list of existing isotope mass numbers
		IsotopesA = new int[Num];
		for (int i = 0; i < Num; i++) {
			IsotopesA[i] = i + 1;
		}
	}

	private void setIsotopesM() {
		// create a list with the corresponding isotope masses for each A
		// compute masses in GeV using the liquid drop model
		IsotopesM = new double[Num];
		for (int i = 0; i < Num; i++) {
			IsotopesM[i] = Constants.massOfHydogenInGev * IsotopesA[i]
					+ LiquidDropModel.returnBindingEnergy(this.Z, IsotopesA[i] - this.Z);
		}

	}

	public HydrogenTimo() {
		// if no input is given, set most abundant A, Z and M
		setIsotopesA();
		setIsotopesM();

		this.A = this.MostAbundantA;
		// in order to approximate the nucleus masses using the liquid drop model
		// (which might be not the best approximation for small nuclei..)
		// the mass of 1-H is adjusted for consistency..
		this.M = this.MostAbundantA * Constants.massOfHydogenInGev
				+ LiquidDropModel.returnBindingEnergy(this.Z, this.MostAbundantA - this.Z);
	}

	public HydrogenTimo(int A) {
		// Set A, Z and M according to the chosen A if the isotopes is stable

		setIsotopesA();
		setIsotopesM();

		// check if input A corresponds to a stable isotope
		boolean contains = IntStream.of(this.IsotopesA).anyMatch(x -> x == A);

		if (contains) {
			this.A = A;
			this.M = this.A * Constants.massOfHydogenInGev
					+ LiquidDropModel.returnBindingEnergy(this.Z, this.A - this.Z);
		} else {
			// set most abundant if A does not correspond to a stable isotope
			System.out.println("Requested isotope is unstable, setting most abundant isotope instead.");
			this.A = this.MostAbundantA;
			this.M = this.MostAbundantA * Constants.massOfHydogenInGev
					+ LiquidDropModel.returnBindingEnergy(this.Z, this.A - this.Z);
		}
	}

	public int getN() {
		return A - Z;
	}

	// default getters

	public int getA() {
		return A;
	}

	public int getZ() {
		return Z;
	}

	public int getMostAbundantA() {
		return MostAbundantA;
	}

	public double getMostAbundantM() {
		return MostAbundantM;
	}

	public double getM() {
		return M;
	}

	public int[] getIsotopesA() {
		return IsotopesA;
	}

	public double[] getIsotopesM() {
		return IsotopesM;
	}
}
