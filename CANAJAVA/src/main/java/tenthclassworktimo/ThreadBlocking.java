package tenthclassworktimo;

public class ThreadBlocking {

	private static int counter1 = 0;
	private static int counter2 = 0;

	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public static void firstAdder() {
		synchronized (lock1) {
			counter1++;
		}
	}

	public static void secondAdder() {
		synchronized (lock2) {
			counter2++;
		}
	}

	public static void add() {
		for (int i = 0; i < 1000; i++) {
			firstAdder();
			secondAdder();
		}
	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				add();
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				add();
			}
		});

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("Value of counter1: " + counter1);
		System.out.println("Value of counter2: " + counter2);
	}
}
