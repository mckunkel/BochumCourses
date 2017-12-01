package ninthclass.gui.demowindowsbuilder;

public class App implements IterationEventListener {

	public App() {

	}

	public static void main(String[] args) {

		System.out.println("Starting App");
		App app = new App();
		// app.runAppNoThread();
		app.runAppThread();

		System.out.println("Ending App");

	}

	public void runAppNoThread() {
		Worker worker = new Worker();
		worker.addListener(this);
		worker.addListener(new PrintEvent());
		worker.runNumbers();
		worker.removeListener(this);
	}

	public void runAppThread() {
		Worker worker = new Worker();
		worker.addListener(this);

		Thread thread = new Thread() {
			public void run() {

				worker.addListener(new PrintEvent());
				worker.runNumbers();
			}
		};
		thread.start();
		try {
			thread.join();
		} catch (Exception e) {
			// TODO: handle exception
		}

		worker.removeListener(this);
	}

	@Override
	public void nextIteration(IterationEvent event) {
		System.out.println("Event is " + event.getValue());
		if (event.isFinished()) {
			System.out.println("Program is finished");
		}
	}
}
