package homeworkAntonius;

public class Hydrogen extends Element {
	
	public Hydrogen() {
		this.N = 0;
		this.Z = 1;

		this.minN = 0;
		this.isotopeMasses = new double[]{1.007825, 2.014102, 3.016049}; // Note: mass numbers taken from https://chemistry.sciences.ncsu.edu/msf/pdf/IsotopicMass_NaturalAbundance.pdf
		
		this.name = "hydrogen";
	}
}
