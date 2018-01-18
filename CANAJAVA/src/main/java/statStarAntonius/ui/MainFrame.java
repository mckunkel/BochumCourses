package statStarAntonius.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import statStarAntonius.StatStar;
import statStarAntonius.integrator.FileOutputListener;
import statStarAntonius.integrator.IntegrationStepEvent;
import statStarAntonius.integrator.IntegrationStepEventListener;
import statStarAntonius.integrator.NumericalIntegrator;

public class MainFrame extends JFrame implements IntegrationStepEventListener {

	private JPanel contentPane;

	private JLabel densityLabel;
	private DoubleField densityField;

	private JLabel opacityLabel;
	private DoubleField opacityField;

	private JLabel epsilonLabel;
	private DoubleField epsilonField;

	private JLabel finalRadiusLabel;
	private DoubleField finalRadiusField;

	private JLabel stepSizeLabel;
	private DoubleField stepSizeField;

	private JLabel radiusLabel;
	private StellarRadiusField radiusField;
	private JLabel radiusResultLabel;

	private JLabel pressureLabel;
	private DoubleField pressureField;
	private JLabel pressureResultLabel;

	private JLabel luminosityLabel;
	private DoubleField luminosityField;
	private JLabel luminosityResultLabel;

	private JLabel temperatureLabel;
	private DoubleField temperatureField;
	private JLabel temperatureResultLabel;

	private JLabel columnHeaderInitial;
	private JLabel columnHeaderFinal;

	private ConditionalButton runButton;
	private JProgressBar progressBar;

	private JLabel integrationMethodLabel;
	private JComboBox<String> integrationMethodBox;
	private static final String[] integrationMethods = { "Euler", "RK4" };

	private boolean running;
	private NumericalIntegrator integrator;
	private Thread simulationThread;
	private double initialRadius;
	private double finalRadius;
	private double stepSize;

	public MainFrame() {
		super("Pressure Integration");
		initializeVariables();
		constructLayout();
		setListeners();
		constructAppWindow();
	}

	public void initializeVariables() {
		contentPane = (JPanel) this.getContentPane();

		runButton = new ConditionalButton("Run");
		runButton.setActionCommand("run");

		densityLabel = new JLabel("density");
		densityField = new DoubleField(5);
		runButton.addField(densityField);

		opacityLabel = new JLabel("opacity");
		opacityField = new DoubleField(5);
		runButton.addField(opacityField);

		epsilonLabel = new JLabel("energy generation rate");
		epsilonField = new DoubleField(5);
		runButton.addField(epsilonField);

		finalRadiusLabel = new JLabel("final radius");
		finalRadiusField = new DoubleField(5);
		runButton.addField(finalRadiusField);

		stepSizeLabel = new JLabel("step size");
		stepSizeField = new DoubleField(5);
		runButton.addField(stepSizeField);

		pressureLabel = new JLabel("initial pressure");
		pressureField = new DoubleField(5);
		pressureResultLabel = new JLabel("--");
		runButton.addField(pressureField);

		luminosityLabel = new JLabel("initial luminosity");
		luminosityField = new DoubleField(5);
		luminosityResultLabel = new JLabel("--");
		runButton.addField(luminosityField);

		temperatureLabel = new JLabel("initial temperature");
		temperatureField = new DoubleField(5);
		temperatureResultLabel = new JLabel("--");
		runButton.addField(temperatureField);

		radiusLabel = new JLabel("initial radius");
		radiusField = new StellarRadiusField(temperatureField, luminosityField);
		radiusResultLabel = new JLabel("--");

		integrationMethodLabel = new JLabel("Integration method");
		integrationMethodBox = new JComboBox<>(integrationMethods);

		progressBar = new JProgressBar();

		columnHeaderInitial = new JLabel("initial values");
		columnHeaderFinal = new JLabel("final values");

		// use preset values that make the simulation run long enough so you can see the
		// progress bar
		densityField.setText("1");
		pressureField.setText("1");
		stepSizeField.setText("-0.00001");
		finalRadiusField.setText("1");
	}

	private void setListeners() {
		runButton.addActionListener(e -> {
			if (e.getActionCommand() == "run") {
				startSimulation();
			} else if (e.getActionCommand() == "stop") {
				stopSimulation();
			}
		});
	}

	private void startSimulation() {
		if (running == false) {
			try {
				initialRadius = radiusField.getRadius();
				finalRadius = finalRadiusField.getValue();
				stepSize = stepSizeField.getValue();
				boolean use_rk4 = (integrationMethodBox.getSelectedItem() == "RK4");
				integrator = StatStar.makePressureIntegrator(densityField.getValue(), radiusField.getRadius(),
						finalRadiusField.getValue(), pressureField.getValue(), epsilonField.getValue(),
						opacityField.getValue(), luminosityField.getValue(), temperatureField.getValue(), use_rk4);
				integrator.addListener(this);
				integrator.addListener(new FileOutputListener());
				simulationThread = new Thread() {
					public void run() {
						integrator.run();
					}
				};
				simulationThread.start();
				uiStartRunning();
			} catch (NumberFormatException exception) {
			}
		}
	}

	private void uiStartRunning() {
		running = true;
		runButton.setText("Stop");
		runButton.setActionCommand("stop");
		runButton.setListening(false);
		runButton.setEnabled(true);
		densityField.setEditable(false);
		opacityField.setEditable(false);
		epsilonField.setEditable(false);
		pressureField.setEditable(false);
		stepSizeField.setEditable(false);
		finalRadiusField.setEditable(false);
		luminosityField.setEditable(false);
		temperatureField.setEditable(false);
	}

	private void uiStopRunning() {
		running = false;
		runButton.setText("Run");
		runButton.setActionCommand("run");
		runButton.setListening(true);
		densityField.setEditable(true);
		opacityField.setEditable(true);
		epsilonField.setEditable(true);
		pressureField.setEditable(true);
		stepSizeField.setEditable(true);
		finalRadiusField.setEditable(true);
		luminosityField.setEditable(true);
		temperatureField.setEditable(true);
	}

	private void stopSimulation() {
		if (running == true) {
			integrator.stop();
			uiStopRunning();
		}
	}

	public void constructLayout() {
		contentPane.setLayout(new GridBagLayout());

		int y = 0;

		contentPane.add(columnHeaderInitial, new GridBagConstraints(1, y, 1, 1, 0.0, 0.0,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(columnHeaderFinal, new GridBagConstraints(2, y++, 1, 1, 0.0, 0.0,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(densityLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(densityField, new GridBagConstraints(1, y++, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(opacityLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(opacityField, new GridBagConstraints(1, y++, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(epsilonLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(epsilonField, new GridBagConstraints(1, y++, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(finalRadiusLabel, new GridBagConstraints(0, y, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(finalRadiusField, new GridBagConstraints(1, y++, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(stepSizeLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(stepSizeField, new GridBagConstraints(1, y++, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(radiusLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(radiusField, new GridBagConstraints(1, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(radiusResultLabel, new GridBagConstraints(2, y++, 1, 1, 1.0, 0.0,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(pressureLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(pressureField, new GridBagConstraints(1, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(pressureResultLabel, new GridBagConstraints(2, y++, 1, 1, 1.0, 0.0,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(luminosityLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(luminosityField, new GridBagConstraints(1, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(luminosityResultLabel, new GridBagConstraints(2, y++, 1, 1, 1.0, 0.0,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(temperatureLabel, new GridBagConstraints(0, y, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(temperatureField, new GridBagConstraints(1, y, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(temperatureResultLabel, new GridBagConstraints(2, y++, 1, 1, 1.0, 0.0,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(integrationMethodLabel, new GridBagConstraints(0, y, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(integrationMethodBox, new GridBagConstraints(1, y++, 1, 1, 1.0, 0.0,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(runButton, new GridBagConstraints(0, y++, 2, 1, 0., 0., GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(progressBar, new GridBagConstraints(0, y++, 2, 1, 0., 0., GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}

	public void constructAppWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(640, 400);
		setVisible(true);
	}

	@Override
	public void nextIntegrationStep(IntegrationStepEvent event) {
		radiusResultLabel.setText(String.format("%.4f", event.integrationState.t));
		pressureResultLabel.setText(String.format("%.4f", event.integrationState.stateVector.state[StatStar.p]));
		luminosityResultLabel.setText(String.format("%.4f", event.integrationState.stateVector.state[StatStar.L]));
		temperatureResultLabel.setText(String.format("%.4f", event.integrationState.stateVector.state[StatStar.T]));

		if (event.finished == true) {
			uiStopRunning();
		} else {
			int progress = (int) Math.round(Math.abs(event.integrationState.t / (initialRadius - finalRadius) * 100));
			if (stepSize < 0) {
				progress = 100 - progress;
			}
			progressBar.setValue(progress);
		}
	}
}
