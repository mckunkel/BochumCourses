package eleventhclass.morethreads;

import java.util.ArrayList;
import java.util.List;

class Process {

	private List<Integer> aList = new ArrayList<>();
	private final int MAX = 5;
	private final int MIN = 0;
	private final Object lock = new Object();
	private int value = 0;

	public void produce() throws InterruptedException {

		synchronized (lock) {
			while (true) {
				if (aList.size() == MAX) {
					System.out.println("Waiting for removing " + "items from the list");
					lock.wait();
				} else {
					System.out.println("Adding: " + value);
					aList.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(2000);
			}
		}
	}

	public void consumer() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (aList.size() == MIN) {
					System.out.println("Waiting for adding " + "items from the list");
					lock.wait();
				} else {
					System.out.println("Removed: " + aList.remove(--value));
					lock.notify();
				}
				Thread.sleep(2000);
			}
		}
	}

}

public class ProducerConsumer {
	public static void main(String[] args) {
		Process process = new Process();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					process.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					process.consumer();
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
