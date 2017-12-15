package tenthclass.concurency;

class WorkerVolitile implements Runnable {

	private boolean isFinished = false;

	@Override
	public void run() {
		while (!isFinished) {
			System.out.println("Working worker");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

}

public class VolatileWorker {
	public static void main(String[] args) {
		WorkerVolitile workerVolitile = new WorkerVolitile();
		Thread thread = new Thread(workerVolitile);
		thread.start();
		// put the application thread to sleep by 2 secs = 2000ms
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// can I get the CPU to cache the boolean?
		workerVolitile.setFinished(true);
		System.out.println("We are Done!!");
	}
}
