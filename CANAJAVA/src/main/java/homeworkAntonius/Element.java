package homeworkAntonius;

// I made this a superclass, so all those methods (especially the
// printElementInfo though) wouldn't have to be duplicated across all those
// element classes. I found the abstract keyword accidentally through eclipse's
// automatic fixes, but I think it works well in this case because this class
// shouldn't be instantiated directly and is only there to provide common
// method implementations.

public abstract class Element {

	int N;
	int Z;

	int minN;
	double[] isotopeMasses;

	String name;

	public int getN() {
		return N;
	}

	public int getZ() {
		return Z;
	}

	public int getMinN() {
		return minN;
	}

	public double[] getIsotopeMasses() {
		return isotopeMasses;
	}

	public String getName() {
		return name;
	}

	void printElementInfo() {
		System.out.println("Element " + this.name + " info:");
		System.out.println(" - Z=" + this.Z);
		System.out.println(" - Isotope masses:");

		int N = this.minN;
		for (double mass : this.isotopeMasses) {

			System.out.print("  - N=" + N + ": " + mass + "u");
			if (N == this.N) {
				System.out.print("   <-- most abundant isotope");
			}

			System.out.println("");

			N += 1;
		}

		System.out.println("");
	}
}