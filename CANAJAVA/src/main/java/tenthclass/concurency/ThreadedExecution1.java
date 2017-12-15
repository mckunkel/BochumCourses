package tenthclass.concurency;

class AWorker1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Worker1: " + i);
		}
	}
}

class AWorker2 implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Worker2: " + i);
		}
	}
}

public class ThreadedExecution1 {

	public static void main(String[] args) {

		Thread thread = new Thread(new AWorker1());
		Thread thread2 = new Thread(new AWorker2());

		thread.start();
		thread2.start();
	}
}
