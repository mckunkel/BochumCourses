package thirdclass;

import org.jlab.groot.data.GraphErrors;
import org.jlab.groot.ui.TCanvas;

import domain.utils.Constants;

public class ThirdClassHomeworkSophieAerdker {
	// Remarks from MK
	// Well constructed program
	// I gave extra credit for the axes labels, marker style and color
	// You forgot the units on your axis
	// i.e. radius is in units of km? m? s? furlongs?
	// Answer radius/radius = arbitrary units
	// time was in what units? gargles? Han Solo parsecs?
	// Answer n*solar_age [years]
	// grade 1.15/1.0
	public static double radius(int x) {

		double gravConstant = Constants.gravitationalConstant;
		double massSun = Constants.massOfSun;
		double ageSun = Constants.ageOfSun * Constants.yearToSeconds;
		double luminosity = Constants.solarLuminosity * Constants.ergToJoule;

		double r = (3.0 / 10.0) * gravConstant * Math.pow(massSun, 2) / (x * ageSun * luminosity);

		return r;
	}

	public static void main(String[] args) {
		GraphErrors gr = new GraphErrors();

		for (int i = 1; i < 21; i++) {
			double r = radius(i) / Constants.solarRadius;
			gr.addPoint(i, r, 0, 0);
		}

		gr.setTitle("Solar radius with only gravitational heating");
		gr.setTitleX("Age of the sun [solar age]");
		gr.setTitleY("Radius of the sun [solar radius]");
		gr.setMarkerColor(4);
		gr.setMarkerStyle(2);

		TCanvas canvas = new TCanvas("Only gravitational heating", 800, 800);
		canvas.draw(gr);
		// SaveCanvas.saveCanvas(canvas);
	}

}
