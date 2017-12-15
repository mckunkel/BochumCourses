package eleventhclass.morethreads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	private static int counter = 0;
	private static Lock lock = new ReentrantLock();

	public static void increment() {
		lock.lock(); // comment this and unlock out to demonstrate the lock
		// // behavior
		for (int i = 0; i < 1000; i++) {
			counter++;
		}
		lock.unlock(); // comment this out to demonstrate the lock behavior

		// for best practice of RenentrantLock, use try/finally block
		// because if a method throws an exception , i.e. the "loop"
		// then unlock will never be called and the thread will be in a deadlock
		// situation
		try {
			for (int i = 0; i < 1000; i++) {
				counter++;
			}
		} finally {
			lock.unlock();// unlocks no matter what
		}
	}

	// I can call unlock from another method, example
	public static void unlockThread() {
		lock.unlock();
	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				increment();
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				increment();
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
		System.out.println("Value of counter is: " + counter);
	}
}
