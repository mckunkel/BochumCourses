package tenthclassworktimo;

class VolatileWorker implements Runnable {

	private volatile boolean isFinished = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!isFinished) {
			System.out.println("Worker working");
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

public class WorkerVolatile {
	public static void main(String[] args) {
		VolatileWorker volatileWorker = new VolatileWorker();
		Thread thread = new Thread(volatileWorker);
		thread.start();
		try {
			thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		volatileWorker.setFinished(true);
		System.out.println("Finished");
	}
}
