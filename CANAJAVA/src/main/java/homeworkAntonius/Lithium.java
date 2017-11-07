package homeworkAntonius;

public class Lithium extends Element {
	
	public Lithium() {
		this.N = 4;
		this.Z = 3;

		this.minN = 3;
		this.isotopeMasses = new double[]{6.015122, 7.016004}; // Note: mass numbers taken from https://chemistry.sciences.ncsu.edu/msf/pdf/IsotopicMass_NaturalAbundance.pdf
		
		this.name = "lithium";
	}
}
