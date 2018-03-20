package statStarAntonius.integrator;

public class IntegrationStepEvent {
	public IntegrationState integrationState;
	public boolean finished;

	public IntegrationStepEvent(IntegrationState integrationState, boolean finished) {
		this.integrationState = integrationState;
		this.finished = finished;
	}
}
