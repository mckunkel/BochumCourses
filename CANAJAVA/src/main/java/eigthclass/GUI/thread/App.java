package eigthclass.GUI.thread;

public class App implements IterationEventListener {
	public static void main(String[] args) {

		App app = new App();
		System.out.println("Starting Mock UI");
		app.workWithNoThread();
		// app.workWithThread();
		System.out.println("Ending Mock UI");

	}

	public App() {

	}

	public void workWithNoThread() {

		final OddNumberMultiplication oMultiplication = new OddNumberMultiplication();
		oMultiplication.addListener(this);
		oMultiplication.addListener(new PrintEvent());
		System.out.println("Working with no thread");
		oMultiplication.calculateOddMultiplication();
		oMultiplication.removeListener(this);
	}

	public void workWithThread() {

		final OddNumberMultiplication oMultiplication = new OddNumberMultiplication();
		oMultiplication.addListener(this);
		oMultiplication.addListener(new PrintEvent());
		System.out.println("Working with a thread");
		Thread thread = new Thread() {
			public void run() {
				oMultiplication.calculateOddMultiplication();
			}
		};
		thread.start();
		try {
			thread.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
