package tenthclassworktimo;

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

public class ThreadExecution1 {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new AWorker1());
		Thread thread2 = new Thread(new AWorker2());
		thread1.start();
		thread2.start(); // executed in parallel, ouptput order is messed up
	}
}
