package statStarAntonius.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import statStarAntonius.Const;
import statStarAntonius.StatStar;
import statStarAntonius.integrator.FileOutputListener;
import statStarAntonius.integrator.IntegrationStepEvent;
import statStarAntonius.integrator.IntegrationStepEventListener;

public class MainFrame extends JFrame implements IntegrationStepEventListener {

	private JPanel contentPane;

	private JLabel xLabel;
	private DoubleField xField;

	private JLabel yLabel;
	private YFractionField yField;

	private JLabel zLabel;
	private DoubleField zField;

	private JLabel finalRadiusLabel;
	private DoubleField finalRadiusField;

	private JLabel stepSizeLabel;
	private DoubleField stepSizeField;

	private JLabel radiusLabel;
	private StellarRadiusField radiusField;
	private JLabel radiusResultLabel;

	private JLabel massLabel;
	private DoubleField massField;
	private JLabel massResultLabel;

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
	private StatStar statStar;
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

		xLabel = new JLabel("X");
		xField = new DoubleField(5);
		xField.setValidFunc(x -> (x >= 0 && x <= 1));
		runButton.addField(xField);

		zLabel = new JLabel("Z");
		zField = new DoubleField(5);
		zField.setValidFunc(x -> (x >= 0 && x <= 1));
		runButton.addField(zField);

		yLabel = new JLabel("Y");
		yField = new YFractionField(xField, zField);
		runButton.addField(yField);

		finalRadiusLabel = new JLabel("final radius");
		finalRadiusField = new DoubleField(5);
		runButton.addField(finalRadiusField);

		stepSizeLabel = new JLabel("step size");
		stepSizeField = new DoubleField(5);
		runButton.addField(stepSizeField);

		massLabel = new JLabel("enclosed mass");
		massField = new DoubleField(5);
		massResultLabel = new JLabel("--");
		massField.setValidFunc(x -> (x > 0));
		runButton.addField(massField);

		luminosityLabel = new JLabel("surface luminosity");
		luminosityField = new DoubleField(5);
		luminosityField.setValidFunc(x -> (x > 0));
		luminosityResultLabel = new JLabel("--");
		runButton.addField(luminosityField);

		temperatureLabel = new JLabel("surface temperature");
		temperatureField = new DoubleField(5);
		temperatureField.setValidFunc(x -> (x > 0));
		temperatureResultLabel = new JLabel("--");
		runButton.addField(temperatureField);

		radiusLabel = new JLabel("radius");
		radiusField = new StellarRadiusField(temperatureField, luminosityField);
		radiusResultLabel = new JLabel("--");

		integrationMethodLabel = new JLabel("Integration method");
		integrationMethodBox = new JComboBox<>(integrationMethods);

		progressBar = new JProgressBar();

		columnHeaderInitial = new JLabel("initial values");
		columnHeaderFinal = new JLabel("final values");

		// use preset values that make the simulation run long enough so you can see the
		// progress bar
		xField.setText("1");
		massField.setText("1");
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
				boolean useRK4 = (integrationMethodBox.getSelectedItem() == "RK4");
				statStar = new StatStar(luminosityField.getValue() * Const.lSolar, massField.getValue() * Const.mSolar,
						radiusField.getRadius(), stepSizeField.getValue(), useRK4, xField.getValue(),
						yField.getyFraction(), zField.getValue());
				statStar.integrator.addListener(this);
				statStar.integrator.addListener(new FileOutputListener());
				simulationThread = new Thread() {
					public void run() {
						statStar.integrator.run();
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
		xField.setEditable(false);
		zField.setEditable(false);
		massField.setEditable(false);
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
		xField.setEditable(true);
		zField.setEditable(true);
		massField.setEditable(true);
		stepSizeField.setEditable(true);
		finalRadiusField.setEditable(true);
		luminosityField.setEditable(true);
		temperatureField.setEditable(true);
	}

	private void stopSimulation() {
		if (running == true) {
			statStar.integrator.stop();
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

		contentPane.add(xLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(xField, new GridBagConstraints(1, y++, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(yLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(yField, new GridBagConstraints(1, y++, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(zLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(zField, new GridBagConstraints(1, y++, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
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

		contentPane.add(massLabel, new GridBagConstraints(0, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(massField, new GridBagConstraints(1, y, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(massResultLabel, new GridBagConstraints(2, y++, 1, 1, 1.0, 0.0,
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
		massResultLabel.setText(String.format("%.4f", event.integrationState.stateVector.state[StatStar.ip]));
		luminosityResultLabel.setText(String.format("%.4f", event.integrationState.stateVector.state[StatStar.iL]));
		temperatureResultLabel.setText(String.format("%.4f", event.integrationState.stateVector.state[StatStar.iT]));

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
