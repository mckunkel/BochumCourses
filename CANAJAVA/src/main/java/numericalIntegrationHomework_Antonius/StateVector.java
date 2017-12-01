package numericalIntegrationHomework_Antonius;

public class StateVector {

	public double[] state;

	public StateVector(double[] state) {
		this.state = state;
	}

	public StateVector plus(StateVector other) {
		assert this.state.length == other.state.length;
		double[] new_state = new double[this.state.length];
		for (int i = 0; i < this.state.length; i++) {
			new_state[i] = this.state[i] + other.state[i];
		}

		return new StateVector(new_state);
	}

	public StateVector times(double factor) {
		double[] new_state = new double[this.state.length];
		for (int i = 0; i < this.state.length; i++) {
			new_state[i] = factor * this.state[i];
		}

		return new StateVector(new_state);
	}
}
