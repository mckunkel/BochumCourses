package statStarAntonius.integrator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

public class FileOutputListener implements IntegrationStepEventListener {

	private FileWriter fileWriter;
	private boolean writerValid;

	public FileOutputListener() {
		try {
			this.fileWriter = new FileWriter("output");
			this.writerValid = true;
		} catch (IOException e) {
			System.out.println("There was a problem opening the output file: " + e.getMessage());
			this.writerValid = false;
		}
	}

	@Override
	public void nextIntegrationStep(IntegrationStepEvent event) {
		Calendar now = Calendar.getInstance();
		if (this.writerValid) {
			try {
				this.fileWriter.write("IntegrationStepEvent on " + now.getTime() + ": t=" + event.integrationState.t
						+ ", state=" + Arrays.toString(event.integrationState.stateVector.state));
				if (event.finished) {
					this.fileWriter.write(" (integration finished)\n");
				} else {
					this.fileWriter.write("\n");
				}
				this.fileWriter.flush();
			} catch (IOException e) {
				System.out.println("There was a problem writing to the output file: " + e.getMessage());
			}
		}
	}
}
