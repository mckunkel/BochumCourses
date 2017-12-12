package eigthclasshomeworktimo;

import java.io.FileWriter;
import java.util.Calendar;

public class PrintPressure implements IterationEventListener {

	@Override
	public void nextIteration(IterationEvent event) {

		Calendar now = Calendar.getInstance();

		try {
			if (event.isStarted()) {
				FileWriter fileWriter = new FileWriter("PressureData.dat", false);
				fileWriter.write("Started at " + now.getTime() + "\n");
				fileWriter.write("Step " + event.getStep() + ": r = " + event.getR() + " m, p = " + event.getP()
						+ "Pa, L = " + event.getL() + " W, T = " + event.getT() + " K \n");
				fileWriter.close();
			} else {
				FileWriter fileWriter = new FileWriter("PressureData.dat", true);
				if (event.isFinished()) {
					fileWriter.write("Finished at " + now.getTime() + "\n");
				} else
					fileWriter.write("Step " + event.getStep() + ": r = " + event.getR() + " m, p = " + event.getP()
							+ "Pa, L = " + event.getL() + " W, T = " + event.getT() + " K \n");
				fileWriter.close();
			}
		} catch (Exception e) {
			System.err.println("Mistakes were made" + e);
		}

	}

}
