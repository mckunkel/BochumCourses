package eigthclass.GUI.thread;

public class IterationEvent {
	private int value;
	private boolean finished;

	public IterationEvent(int value, boolean finished) {
		this.value = value;
		this.finished = finished;
	}

	public int getValue() {
		return value;
	}

	public boolean isFinished() {
		return finished;
	}

}
