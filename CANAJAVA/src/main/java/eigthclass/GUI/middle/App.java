package eigthclass.GUI.middle;

public class App implements IterationEventListener {
	public static void main(String[] args) {

		App app = new App();
		System.out.println("Starting Mock UI");
		app.workWithNoThread();
		System.out.println("Ending Mock UI");

	}

	public App() {

	}

	public void workWithNoThread() {

		final OddNumberMultiplication oMultiplication = new OddNumberMultiplication();
		oMultiplication.addListener(this);
		System.out.println("Working with no thread");
		oMultiplication.calculateOddMultiplication();
		oMultiplication.removeListener(this);
	}

	@Override
	public void nextIteration(IterationEvent event) {
		System.out.println("In App : " + event.getValue());
		if (event.isFinished()) {
			System.out.println("Finished program");
		}
	}
}
