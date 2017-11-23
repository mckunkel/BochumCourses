package eigthclass.GUI.middle;

import java.util.ArrayList;
import java.util.List;

public class OddNumberMultiplication {

	private int result;
	private List<IterationEventListener> myListeners;

	public OddNumberMultiplication() {
		this.result = 0;
		this.myListeners = new ArrayList<>();
	}

	public void calculateOddMultiplication() {
		for (int i = 1; i < 10; i++) {
			for (int j = i - 1; j < 10; j++) {
				if (isOdd(i) && isOdd(j)) {
					this.result = i * j;
					notifymyListeners(this.result, false);
				}
			}
		}
		notifymyListeners(result, true);
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

	private void notifymyListeners(int value, boolean finished) {
		IterationEvent event = new IterationEvent(value, finished);

		for (IterationEventListener iterationEventListener : this.myListeners) {
			IterationEventListener iListener = (IterationEventListener) iterationEventListener;
			iListener.nextIteration(event);
		}
	}

}
