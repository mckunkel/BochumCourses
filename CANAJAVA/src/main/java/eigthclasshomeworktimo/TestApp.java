package eigthclasshomeworktimo;

public class TestApp implements IterationEventListener {

	public static void main(String[] args) {
		System.out.println("Starting");
		TestApp app = new TestApp();
		app.runAppNoThread();
		System.out.println("Ending");
	}

	@Override
	public void nextIteration(IterationEvent event) {
		if (event.isFinished()) {
			System.out.println("Finished");
		} else {
			System.out.println("Step " + event.getStep() + " r = " + event.getR() + "m,  p = " + event.getP() + " Pa,");
		}
	}

	public void runAppNoThread() {
		IntegrationWorker worker = new IntegrationWorker(1.41, 1, 1, 1, 0.5, 1, 6000, 1000, "Euler");
		worker.addListener(this);
		worker.addListener(new PrintPressure());
		worker.iterateToR();
		worker.removeListener(this);
	}

}
