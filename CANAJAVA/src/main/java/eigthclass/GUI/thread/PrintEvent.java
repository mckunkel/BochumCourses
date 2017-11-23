package eigthclass.GUI.thread;

import java.io.FileWriter;
import java.util.Calendar;

public class PrintEvent implements IterationEventListener {

	@Override
	public void nextIteration(IterationEvent event) {
		Calendar now = Calendar.getInstance();
		try {
			FileWriter fw = new FileWriter("printedEvent.dat", true);
			fw.write("From PrintEvent: " + now.getTime() + " Value " + event.getValue() + "\n");
			if (event.isFinished()) {
				fw.write("From PrintEvent: " + now.getTime() + " Finished \n");
			}
			fw.close();
		} catch (Exception e) {
			System.err.println("Mistake was made" + e);
		}
	}
}
