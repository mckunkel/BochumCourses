package sixthclasshomeworktimo;

import org.jlab.groot.data.H2F;
import org.jlab.groot.ui.TCanvas;

//grade 1.0
public class Plot2DHist {

	static void plotHistogram() {
		// plot binding energy per nucleon for Z <= 5, A <= 25

		H2F aH2f = new H2F("LiquidDropModel", 5, 1, 5, 6, 0, 5);

		for (int Z = 1; Z <= 5; Z++) {

			Element[] elements = new Element[6];

			for (int N = 0; N < 6; N++) {
				// fill element array with correct atoms for each Z

				if (Z == 1) {
					elements[N] = new Hydrogen(N);
				}

				if (Z == 2) {
					elements[N] = new Helium(N);
				}

				if (Z == 3) {
					elements[N] = new Lithium(N);
				}

				if (Z == 4) {
					elements[N] = new Beryllium(N);
				}

				if (Z == 5) {
					elements[N] = new Boron(N);
				}

				aH2f.setBinContent(Z - 1, N, elements[N].getBindingEnergy());
			}

		}

		aH2f.setTitleX("Z");
		aH2f.setTitleY("N");
		aH2f.setTitle("Liquid drop model binding energy per nucleon");

		TCanvas canv = new TCanvas("LiquidDrop", 800, 600);

		canv.draw(aH2f);
		// bin position is off, no time to fix it.

	}

	public static void main(String[] args) {
		plotHistogram();
	}

}
