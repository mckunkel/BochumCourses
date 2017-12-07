package tenthclass.concurency;

public class SyncThread {
	private static int counter = 0;

	public static void aProcess() {
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					counter++;
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					counter++;
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

	public static void main(String[] args) {
		aProcess();
		System.out.println("Value of counter " + counter);
	}

}
