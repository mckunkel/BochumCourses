package numericalIntegrationHomework_Antonius;

public class StateVector {

	public double[] state;

	public StateVector(double[] state) {
		this.state = state;
	}

	public StateVector copy() {
		double[] newArray = new double[this.state.length];
		for (int i = 0; i < this.state.length; i++) {
			newArray[i] = this.state[i];
		}
		return new StateVector(newArray);
	}

	public StateVector itimes(double factor) {
		for (int i = 0; i < this.state.length; i++) {
			this.state[i] *= factor;
		}

		return this;
	}

	public StateVector iplus(StateVector b) {
		for (int i = 0; i < this.state.length; i++) {
			this.state[i] += b.state[i];
		}

		return this;
	}

	public StateVector times(double factor) {
		return this.copy().itimes(factor);
	}

	public StateVector plus(StateVector b) {
		return this.copy().iplus(b);
	}
}
