package tenthclassworktimo;

class Worker1 {
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Worker1: " + i);

		}
	}
}

class Worker2 {
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Worker2: " + i);

		}
	}
}

public class SequentialExecution {
	public static void main(String[] args) {
		Worker1 worker1 = new Worker1();
		Worker2 worker2 = new Worker2();

		worker1.run();
		worker2.run();
	}
}
