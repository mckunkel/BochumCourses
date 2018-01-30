package pnRatioAntonius;

import java.io.FileWriter;
import java.io.IOException;

public class ComputePNRatio {

	static double neutronDecoupleTemperature = 1; // TODO
	static double freezeOutTemperature = .07; // TODO
	static double tauN = 881.5;
	static double MeVperK = 8.617330e-11; // MeV per Kelvin

	public static void computePNRatio(double neutronDecoupleTemperature, double freezeOutTemperature, String filename) {
		assert neutronDecoupleTemperature >= freezeOutTemperature;

		int nSteps = 1000;
		double startTimeLog = -2; // TODO
		double endTimeLog = 5; // TODO
		double decoupleRatio = -1;
		double decoupleTime = -1;
		double freezeOutRatio = -1;

		try {
			FileWriter fileWriter = new FileWriter(filename);
			fileWriter.write("# time, temperature, pn ratio\n");

			double factor = (endTimeLog - startTimeLog) / nSteps;
			for (int i = 0; i <= nSteps; i++) {
				double time = Math.pow(10, i * factor + startTimeLog);
				double temperature = timeToTemperature(time); // in units MeV
				double pnRatio;

				if (temperature > neutronDecoupleTemperature) {
					pnRatio = Math.exp(-1.29 / temperature);
					decoupleRatio = pnRatio;
					decoupleTime = time;
				} else if (temperature > freezeOutTemperature) {
					double t = time - decoupleTime;
					pnRatio = decoupleRatio * Math.exp(-t / tauN) / (1 + decoupleRatio * (1 - Math.exp(-t / tauN)));
					freezeOutRatio = pnRatio;
				} else {
					pnRatio = freezeOutRatio;
				}

				fileWriter.write(time + ", " + temperature + ", " + pnRatio + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error: couldn't write data to file");
			e.printStackTrace();
		}

	}

	public static double timeToTemperature(double t) {
		return 1.3e10 / Math.sqrt(t) * MeVperK;
	}

	public static void main(String[] args) {
		ComputePNRatio.computePNRatio(neutronDecoupleTemperature, freezeOutTemperature, "pn-ratio.csv");
		ComputePNRatio.computePNRatio(-1, -1, "pn-ratio-nodecouple.csv");
		ComputePNRatio.computePNRatio(neutronDecoupleTemperature, -1, "pn-ratio-nofreeze.csv");
	}
}
