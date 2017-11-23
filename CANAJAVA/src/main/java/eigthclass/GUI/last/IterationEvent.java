package eigthclass.GUI.last;

public class IterationEvent {
	private int value;
	private boolean finished;
	private double progress;

	public IterationEvent(int value, boolean finished, double progress) {
		this.value = value;
		this.finished = finished;
		this.progress = progress;
	}

	public int getValue() {
		return value;
	}

	public double getProgress() {
		return progress;
	}

	public boolean isFinished() {
		return finished;
	}

}
