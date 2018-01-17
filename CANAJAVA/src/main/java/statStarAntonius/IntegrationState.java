package statStarAntonius;

public class IntegrationState {

	public StateVector stateVector;
	public double t;

	public IntegrationState(double t, StateVector stateVector) {
		this.t = t;
		this.stateVector = stateVector;
	}

}
