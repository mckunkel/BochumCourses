package homeworkAntonius;

public class Helium extends Element {

	public Helium() {
		this.N = 2;
		this.Z = 2;

		this.minN = 1;
		this.isotopeMasses = new double[]{3.016029, 4.002603}; // Note: mass numbers taken from https://chemistry.sciences.ncsu.edu/msf/pdf/IsotopicMass_NaturalAbundance.pdf
		
		this.name = "helium";
	}
}
