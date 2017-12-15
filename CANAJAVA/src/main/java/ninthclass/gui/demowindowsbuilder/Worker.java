package ninthclass.gui.demowindowsbuilder;

import java.util.ArrayList;
import java.util.List;

public class Worker {

	private int result;
	private List<IterationEventListener> myListeners;

	public Worker() {
		this.result = 0;
		this.myListeners = new ArrayList<>();
	}

	public void runNumbers() {
		for (int i = 0; i < 100000; i++) {
			result = i;
			notifymyListeners(this.result, false);
		}
		notifymyListeners(this.result, true);

	}

	private void notifymyListeners(int value, boolean finished) {
		IterationEvent event = new IterationEvent(value, finished);

		for (IterationEventListener iterationEventListener : myListeners) {
			IterationEventListener iEventListener = iterationEventListener;
			iEventListener.nextIteration(event);
		}
	}

	public void addListener(IterationEventListener listener) {
		myListeners.add(listener);
	}

	public void removeListener(IterationEventListener listener) {
		myListeners.remove(listener);

	}

}
