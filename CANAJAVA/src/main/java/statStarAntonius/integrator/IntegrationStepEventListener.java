package statStarAntonius.integrator;

// An interface for listeners that would like to receive updates on each
// integration step.
public interface IntegrationStepEventListener {
	public void nextIntegrationStep(IntegrationStepEvent event);
}
