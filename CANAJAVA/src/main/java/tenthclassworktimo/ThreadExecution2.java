package tenthclassworktimo;

class ThreadedWorker1 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Worker1: " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}

class ThreadedWorker2 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Worker2: " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}

public class ThreadExecution2 {
	public static void main(String[] args) {
		ThreadedWorker1 threadedWorker1 = new ThreadedWorker1();
		ThreadedWorker2 threadedWorker2 = new ThreadedWorker2();
		threadedWorker1.start();
		threadedWorker2.start();
		try {
			threadedWorker1.join();
			// threadedWorker2.join();
		} catch (InterruptedException e) {

		}
		System.out.println("We are finished");
	}
}
