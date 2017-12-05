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
		System.out.println("Step " + event.getStep() + ", p = " + event.getP() + " Pa, r = " + event.getR() + "m");
		if (event.isFinished()) {
			System.out.println("Finished");
		}
	}

	public void runAppNoThread() {
		IntegrationWorker worker = new IntegrationWorker(1, 1, 0.5, 100, "Euler");
		worker.addListener(this);
		worker.addListener(new PrintPressure());
		worker.iterateToR();
		worker.removeListener(this);
	}

}
