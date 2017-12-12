package eigthclasshomeworktimo;

public class IterationEvent {
	private double p;
	private double L;
	private double T;
	private double r;
	private int step;
	private boolean started;
	private boolean finished;
	private double progress;

	public IterationEvent(double p, double L, double T, double r, int step, boolean started, boolean finished,
			double progress) {
		this.p = p;
		this.L = L;
		this.T = T;
		this.r = r;
		this.step = step;
		this.started = started;
		this.finished = finished;
		this.progress = progress;
	}

	public double getP() {
		return p;
	}

	public double getL() {
		return L;
	}

	public double getT() {
		return T;
	}

	public double getR() {
		return r;
	}

	public int getStep() {
		return step;
	}

	public double getProgress() {
		return progress;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isStarted() {
		return started;
	}

}
