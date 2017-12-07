package tenthclass.concurency;

class ThreadWorker1 extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Worker1: " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class ThreadWorker2 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Worker2: " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class ThreadedExecution2 {
	private static void showSystemThreadExample() {
		System.out.println("We have finished the task!!");
	}

	public static void main(String[] args) {

		ThreadWorker1 worker1 = new ThreadWorker1();
		ThreadWorker2 worker2 = new ThreadWorker2();

		worker1.start();
		worker2.start();

		try {
			worker1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		showSystemThreadExample();
	}
}
