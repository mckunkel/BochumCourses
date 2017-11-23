package eigthclass.GUI.start;

public class App {
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
		System.out.println("Working with no thread");
		oMultiplication.calculateOddMultiplication();
	}
}
