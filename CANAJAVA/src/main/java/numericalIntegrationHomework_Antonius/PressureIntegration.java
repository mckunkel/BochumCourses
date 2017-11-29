package numericalIntegrationHomework_Antonius;

public class PressureIntegration {

	public class State extends StateVector {

		public State(double[] state) {
			super(state);

			if (state.length != 1) {
				throw new IllegalArgumentException("State vector must have exactly one element");
			}
		}

		public State(double P) {
			this(new double[] { P });
		}

		public PressureIntegration.State copy() {
			return new PressureIntegration.State(super.copy().state);
		}

		public double getP() {
			return this.state[0];
		}
	}

	public static void main(String[] args) {
		NumericalIntegrator integrator = new EulerIntegrator(.1, 0, new PressureIntegration().new State(1.),
				state -> new PressureIntegration().new State(state.getLeft()));

		for (int i = 0; i < 1000; i++) {
			integrator.step();
			System.out.println(((State) integrator.getState()).getP());
		}

	}

}
