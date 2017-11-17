package sixthclasshomeworktimo;

public class Element {

	protected int A;
	protected int Z;
	protected int N;

	public double getBindingEnergy() {
		double aV = 15.5; // MeV
		double aS = 16.8; // MeV
		double aC = 0.715; // MeV
		double aA = 23.; // MeV
		double aP = 11.3; // MeV

		double ret = 0.;

		ret += aV * this.A;
		ret -= aS * Math.pow(this.A, 2. / 3.);
		ret -= aC * Math.pow(this.Z, 2.) / Math.pow(this.A, 1. / 3.);
		ret -= aA * Math.pow((this.Z - this.N), 2) / this.A;

		int par1 = this.Z % 2;
		int par2 = this.N % 2;
		int par = par1 + par2;

		double delta = aP / Math.pow(this.A, 0.5);

		switch (par) {
		case 0:
			ret += delta;
		case 1:
			ret += 0;
		case 2:
			ret -= delta;
		}

		return ret / this.A;

	}

	public int getA() {
		return this.A;
	}

	public int getN() {
		return this.N;
	}

	public int getZ() {
		return this.Z;
	}

}
