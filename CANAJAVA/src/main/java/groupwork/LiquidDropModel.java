package groupwork;

import org.jlab.groot.data.H2F;
import org.jlab.groot.ui.TCanvas;

public class LiquidDropModel {

	static double aV = 15.5; // MeV
	static double aS = 16.8; // MeV
	static double aC = 0.715; // MeV
	static double aA = 23.; // MeV
	static double aP = 11.3; // MeV

	public static double returnBindingEnergy(int Z, int N) {
		int A = Z + N;

		double ret = 0.;

		ret += aV * A;
		ret -= aS * Math.pow(A, 2. / 3.);
		ret -= aC * Math.pow(Z, 2.) / Math.pow(A, 1. / 3.);
		ret -= aA * Math.pow((Z - N), 2) / A;

		int par1 = Z % 2;
		int par2 = N % 2;
		int par = par1 + par2;

		double delta = aP / Math.pow(A, 0.5);

		switch (par) {
		case 0:
			ret += delta;
		case 1:
			ret += 0;
		case 2:
			ret -= delta;
		}

		return ret / 1000.;
	}

	public static void plotHistogram() {

		int binnum = 200;
		int rangemax = binnum + 1;

		H2F aH2f = new H2F("Liquid", binnum, 1, rangemax, binnum, 0, rangemax);
		for (int i = 1; i < binnum; i++) {
			for (int j = 0; j < binnum; j++) {
				aH2f.setBinContent(i, j, returnBindingEnergy(i, j));
			}
		}
		aH2f.setTitleX("Z");
		aH2f.setTitleY("N");
		aH2f.setTitle("Liquid drop model binding energy per nucleon");

		TCanvas canv = new TCanvas("LiquidDrop", 800, 600);

		canv.draw(aH2f);
	}

	public static void main(String[] args) {
		plotHistogram();
	}

}
