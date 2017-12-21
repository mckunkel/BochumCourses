package tenthclassworktimo;

public class SyncedThread {

	private static int counter = 0;

	private static void aProcess() {
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++) {
					incrementValue();
					;
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++) {
					incrementValue();
					;
				}
			}
		});
		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println(counter);
	}

	public static synchronized void incrementValue() {
		counter++;
	}

	public static void main(String[] args) {
		SyncedThread syncedThread = new SyncedThread();
		syncedThread.aProcess();
	}

}
