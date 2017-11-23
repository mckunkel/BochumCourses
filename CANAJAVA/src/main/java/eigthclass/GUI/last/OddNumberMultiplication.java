package eigthclass.GUI.last;

import java.util.ArrayList;
import java.util.List;

public class OddNumberMultiplication {

	private int result;
	private List<IterationEventListener> myListeners;
	private int iMax;
	private int jMax;

	public OddNumberMultiplication(int iMax, int jMax) {
		this.result = 0;
		this.myListeners = new ArrayList<>();

		this.iMax = iMax;
		this.jMax = jMax;
	}

	public void calculateOddMultiplication() {

		for (int i = 1; i < this.iMax; i++) {
			for (int j = i; j < this.jMax; j++) {
				if (isOdd(i) && isOdd(j)) {
					this.result = i * j;
					notifymyListeners(this.result, false, (double) (i + j) / (this.iMax + this.jMax) * 100);
				}
			}
		}
		notifymyListeners(result, true, 100.0);

	}

	private boolean isOdd(int n) {
		return n % 2 == 0 ? false : true;
	}

	public void addListener(IterationEventListener listener) {
		this.myListeners.add(listener);
	}

	public void removeListener(IterationEventListener listener) {
		this.myListeners.remove(listener);

	}

	private void notifymyListeners(int value, boolean finished, double progress) {
		IterationEvent event = new IterationEvent(value, finished, progress);
		for (IterationEventListener iterationEventListener : this.myListeners) {
			IterationEventListener iListener = (IterationEventListener) iterationEventListener;
			iListener.nextIteration(event);
		}
	}

}
