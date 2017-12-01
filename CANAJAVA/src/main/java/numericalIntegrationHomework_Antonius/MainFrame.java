package numericalIntegrationHomework_Antonius;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements IntegrationStepEventListener {

	private JPanel contentPane;
	private JLabel densityLabel;
	private JTextField densityField;
	private JLabel pressureLabel;
	private JTextField pressureField;
	private JLabel radiusLabel;
	private JTextField radiusField;
	private JLabel finalRadiusLabel;
	private JTextField finalRadiusField;
	private JLabel stepSizeLabel;
	private JTextField stepSizeField;
	private JLabel finalPressureLabel;
	private JLabel finalPressureResult;
	private JButton runButton;
	private JProgressBar progressBar;

	private boolean running;
	private NumericalIntegrator integrator;
	private Thread simulationThread;
	private double startingT;

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
		densityField = new JTextField(5);
		pressureLabel = new JLabel("initial pressure");
		pressureField = new JTextField(5);
		radiusLabel = new JLabel("initial radius");
		radiusField = new JTextField(5);
		finalRadiusLabel = new JLabel("final radius");
		finalRadiusField = new JTextField(5);
		stepSizeLabel = new JLabel("step size");
		stepSizeField = new JTextField(5);
		finalPressureLabel = new JLabel("final pressure");
		finalPressureResult = new JLabel();
		runButton = new JButton("Run");
		runButton.setActionCommand("run");
		progressBar = new JProgressBar();
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
			startingT = Double.parseDouble(radiusField.getText());
			integrator = PressureIntegrator.makePressureIntegrator(Double.parseDouble(densityField.getText()),
					Double.parseDouble(radiusField.getText()), Double.parseDouble(finalRadiusField.getText()),
					Double.parseDouble(finalRadiusField.getText()), true);
			integrator.addListener(this);
			simulationThread = new Thread() {
				public void run() {
					integrator.run();
				}
			};
			simulationThread.start();
			uiStartRunning();
		}
	}

	private void uiStartRunning() {
		running = true;
		runButton.setText("Stop");
		runButton.setActionCommand("stop");
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
		densityField.setEditable(true);
		pressureField.setEditable(true);
		radiusField.setEditable(true);
		stepSizeField.setEditable(true);
		finalRadiusField.setEditable(true);
	}

	private void stopSimulation() {
		if (running == true) {
			// TODO: stop simulation thread
			integrator.stop();
			integrator.removeListener(this);
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
			integrator.removeListener(this);
			uiStopRunning();
			finalPressureResult.setText(String.format("%.4f", event.integrationState.stateVector.state[0]));
		} else {
			finalPressureResult.setText(String.format("%.4f", event.integrationState.stateVector.state[0]));
			int progress = (int) Math
					.round(Math.abs(event.integrationState.t / (integrator.getFinal_t() - startingT) * 100));
			if (integrator.getDt() < 0) {
				progress = 100 - progress;
			}
			progressBar.setValue(progress);
		}
	}
}
