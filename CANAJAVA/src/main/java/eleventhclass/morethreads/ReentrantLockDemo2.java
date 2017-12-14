package eleventhclass.morethreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker1 {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void produce() throws InterruptedException {
		lock.lock();
		System.out.println("Producing");
		condition.await();
		System.out.println("Producing again");
		lock.unlock();
	}

	public void consume() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		System.out.println("Consume");
		condition.signal();
		lock.unlock();
	}
}

public class ReentrantLockDemo2 {

	public static void main(String[] args) {
		Worker1 worker = new Worker1();

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
					worker.consume();
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
