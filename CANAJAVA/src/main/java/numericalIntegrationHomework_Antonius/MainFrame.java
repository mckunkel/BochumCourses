package numericalIntegrationHomework_Antonius;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MainFrame extends JFrame implements IntegrationStepEventListener {

	private JPanel contentPane;
	private JLabel densityLabel;
	private DoubleField densityField;
	private JLabel pressureLabel;
	private DoubleField pressureField;
	private JLabel radiusLabel;
	private DoubleField radiusField;
	private JLabel finalRadiusLabel;
	private DoubleField finalRadiusField;
	private JLabel stepSizeLabel;
	private DoubleField stepSizeField;
	private JLabel finalPressureLabel;
	private JLabel finalPressureResult;
	private ConditionalButton runButton;
	private JProgressBar progressBar;

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

		densityLabel = new JLabel("density");
		densityField = new DoubleField(5);

		pressureLabel = new JLabel("initial pressure");
		pressureField = new DoubleField(5);

		radiusLabel = new JLabel("initial radius");
		radiusField = new DoubleField(5);

		finalRadiusLabel = new JLabel("final radius");
		finalRadiusField = new DoubleField(5);

		stepSizeLabel = new JLabel("step size");
		stepSizeField = new DoubleField(5);

		finalPressureLabel = new JLabel("final pressure");
		finalPressureResult = new JLabel();

		runButton = new ConditionalButton("Run");
		runButton.setActionCommand("run");
		runButton.addField(densityField);
		runButton.addField(pressureField);
		runButton.addField(radiusField);
		runButton.addField(finalRadiusField);
		runButton.addField(stepSizeField);

		progressBar = new JProgressBar();

		// use preset values that make the simulation run long enough so you can see the
		// progress bar
		densityField.setText("1");
		pressureField.setText("1");
		radiusField.setText("100000");
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
				initialRadius = radiusField.getValue();
				finalRadius = finalRadiusField.getValue();
				stepSize = stepSizeField.getValue();
				integrator = PressureIntegrator.makePressureIntegrator(densityField.getValue(), radiusField.getValue(),
						finalRadiusField.getValue(), pressureField.getValue(), true);
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
		pressureField.setEditable(false);
		radiusField.setEditable(false);
		stepSizeField.setEditable(false);
		finalRadiusField.setEditable(false);
	}

	private void uiStopRunning() {
		running = false;
		runButton.setText("Run");
		runButton.setActionCommand("run");
		runButton.setListening(true);
		densityField.setEditable(true);
		pressureField.setEditable(true);
		radiusField.setEditable(true);
		stepSizeField.setEditable(true);
		finalRadiusField.setEditable(true);
	}

	private void stopSimulation() {
		if (running == true) {
			integrator.stop();
			uiStopRunning();
		}
	}

	public void constructLayout() {
		contentPane.setLayout(new GridBagLayout());

		contentPane.add(densityLabel, new GridBagConstraints(0, 0, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(densityField, new GridBagConstraints(1, 0, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(pressureLabel, new GridBagConstraints(0, 1, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(pressureField, new GridBagConstraints(1, 1, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(radiusLabel, new GridBagConstraints(0, 2, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(radiusField, new GridBagConstraints(1, 2, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(stepSizeLabel, new GridBagConstraints(0, 3, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(stepSizeField, new GridBagConstraints(1, 3, 1, 1, 0., 0., GridBagConstraints.BASELINE_LEADING,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(finalRadiusLabel, new GridBagConstraints(0, 4, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(finalRadiusField, new GridBagConstraints(1, 4, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(finalPressureLabel, new GridBagConstraints(0, 5, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(finalPressureResult, new GridBagConstraints(1, 5, 1, 1, 0., 0.,
				GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(runButton, new GridBagConstraints(0, 6, 2, 1, 0., 0., GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		contentPane.add(progressBar, new GridBagConstraints(0, 7, 2, 1, 0., 0., GridBagConstraints.CENTER,
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
		if (event.finished == true) {
			uiStopRunning();
			finalPressureResult.setText(String.format("%.4f", event.integrationState.stateVector.state[0]));
		} else {
			finalPressureResult.setText(String.format("%.4f", event.integrationState.stateVector.state[0]));
			int progress = (int) Math.round(Math.abs(event.integrationState.t / (initialRadius - finalRadius) * 100));
			if (stepSize < 0) {
				progress = 100 - progress;
			}
			progressBar.setValue(progress);
		}
	}
}
