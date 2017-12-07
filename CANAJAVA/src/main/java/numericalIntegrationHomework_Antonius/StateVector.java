package numericalIntegrationHomework_Antonius;

public class StateVector {

	public double[] state;

	public StateVector(double[] state) {
		this.state = state;
	}

	public StateVector copy() {
		double[] newArray = new double[this.state.length];
		// You know you can always try this and save yourself a forloop
		System.arraycopy(this.state, 0, newArray, 0, this.state.length);
		// for (int i = 0; i < this.state.length; i++) {
		// newArray[i] = this.state[i];
		// }
		return new StateVector(newArray);
	}

	public StateVector iTimes(double factor) {// lets use Java convention of
												// iTimes
		for (int i = 0; i < this.state.length; i++) {
			this.state[i] *= factor;
		}

		return this;
	}

	public StateVector iPlus(StateVector b) {// lets use Java convention of
		// iPlus
		for (int i = 0; i < this.state.length; i++) {
			this.state[i] += b.state[i];
		}

		return this;
	}

	public StateVector times(double factor) {
		return this.copy().iTimes(factor);
	}

	public StateVector plus(StateVector b) {
		return this.copy().iPlus(b);
	}
}
