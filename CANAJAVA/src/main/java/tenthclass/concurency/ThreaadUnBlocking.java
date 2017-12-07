package tenthclass.concurency;

public class ThreaadUnBlocking {

	private static int counter1 = 0;
	private static int counter2 = 0;

	public static synchronized void firstAdder() {
		counter1++;
	}

	public static synchronized void secondAdder() {
		counter2++;
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
				add();
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				add();
			}
		});
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Value of counter1: " + counter1);
		System.out.println("Value of counter2: " + counter2);

	}

}
