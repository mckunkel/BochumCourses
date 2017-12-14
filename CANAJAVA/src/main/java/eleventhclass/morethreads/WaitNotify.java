package eleventhclass.morethreads;

class Worker {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producing...");
			wait();
			System.out.println("Producing again...");
		}
	}

	public void consumer() throws InterruptedException {
		Thread.sleep(1000);
		synchronized (this) {
			System.out.println("Consuming...");
			notify();
			System.out.println("Demostrate that if I do anything after");
			System.out.println("\"notify\" that java will run this");
			System.out.println("before the \"waiting thread\" executes");

		}
	}
}

public class WaitNotify {
	public static void main(String[] args) {
		Worker worker = new Worker();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					worker.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
