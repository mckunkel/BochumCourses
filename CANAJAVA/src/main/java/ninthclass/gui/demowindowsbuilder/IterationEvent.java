package ninthclass.gui.demowindowsbuilder;

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

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
