package sixthclasshomeworktimo;

import org.jlab.groot.data.GraphErrors;
import org.jlab.groot.fitter.DataFitter;
import org.jlab.groot.math.F1D;
import org.jlab.groot.ui.TCanvas;

import domain.utils.Constants;

public class PlotHubbleLaw {

	static void plotVelocitytoDistance() {

		GraphErrors graphErrors = new GraphErrors();

		// set data
		Cluster[] clusterdata = new Cluster[6];
		clusterdata[0] = new Virgo();
		clusterdata[1] = new UrsaMajor();
		clusterdata[2] = new CoronaBorealis();
		clusterdata[3] = new Bootes();
		clusterdata[4] = new Hydra();
		clusterdata[5] = new Quasar();

		for (int i = 0; i < 6; i++) {
			graphErrors.addPoint(clusterdata[i].getDistance(), clusterdata[i].getVelocity(), 0, 0);
		}

		graphErrors.setTitleX("d [Mpc]");
		graphErrors.setTitleY("v [km/s]");

		/////////////////////////////////////////////////////////
		// fit constant with least square method
		double H;
		double v0;
		double sumvd = 0;
		double sumv2 = 0;
		double sumd2 = 0;
		double sumv = 0;
		double sumd = 0;

		for (int i = 0; i < 6; i++) {
			sumvd += clusterdata[i].getDistance() * clusterdata[i].getVelocity();
			sumv2 += clusterdata[i].getVelocity() * clusterdata[i].getVelocity();
			sumd2 += clusterdata[i].getDistance() * clusterdata[i].getDistance();
			sumv += clusterdata[i].getVelocity();
			sumd += clusterdata[i].getDistance();
		}

		H = (6 * sumvd - sumv * sumd) / (6 * sumd2 - sumd * sumd);
		v0 = (sumd2 * sumv - sumd * sumvd) / (6 * sumd2 - sumd * sumd);
		/////////////////////////////////////////////////////////

		// plot corresponding line
		F1D f1d = new F1D("f1", "[a] + [b] * x", 0, 4035);
		f1d.setParameter(0, v0);
		f1d.setParameter(1, H);

		DataFitter.fit(f1d, graphErrors, "Q");

		TCanvas canv = new TCanvas("Hubble's Law", 800, 600);

		canv.draw(graphErrors);
		canv.draw(f1d, "same");

		///////////////////////////////////////////////////////
		System.out.println("Hubble constant from linear regression: " + H + " km/(s * Mpc)");

		double mpctokm = 3.086e19;
		double sectoyear = 1 / Constants.yearToSeconds;
		double t = 1 / H * mpctokm * sectoyear;

		System.out.println("Corresponding age of universe is " + t + " years");

	}

	public static void main(String[] args) {
		plotVelocitytoDistance();
	}

}
