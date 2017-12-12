package eigthclasshomeworktimo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;

import eigthclass.GUI.last.PanelConstraints;

public class MainFrame extends JFrame implements IterationEventListener {

	private JPanel panel;

	private JLabel statusLabel;
	private JLabel runLabel;
	private JLabel progressLabel;

	private JLabel currentRadiusLabel;
	private JLabel currentPressureLabel;
	private JLabel currentLuminosityLabel;
	private JLabel currentTemperatureLabel;

	private JLabel radius0Label;
	private JLabel radius1Label;
	private JLabel densityLabel;
	private JLabel luminosityLabel;
	private JLabel temperatureLabel;
	private JLabel epsilonLabel;
	private JLabel kappaLabel;
	private JLabel stepLabel;
	private JLabel methodLabel;

	private JTextField radius0Text;
	private JTextField radius1Text;
	private JTextField densityText;
	private JTextField luminosityText;
	private JTextField temperatureText;
	private JTextField epsilonText;
	private JTextField kappaText;
	private JTextField stepText;

	private JComboBox<String> methodBox;
	private JButton runButton;
	private JProgressBar progressbar;

	private String[] methodNames;

	private void initialize() {

		this.panel = new JPanel();
		this.statusLabel = new JLabel();
		this.runLabel = new JLabel();
		this.progressLabel = new JLabel();

		this.currentRadiusLabel = new JLabel();
		this.currentPressureLabel = new JLabel();
		this.currentLuminosityLabel = new JLabel();
		this.currentTemperatureLabel = new JLabel();

		this.radius0Label = new JLabel();
		this.radius1Label = new JLabel();
		this.densityLabel = new JLabel();
		this.luminosityLabel = new JLabel();
		this.temperatureLabel = new JLabel();
		this.epsilonLabel = new JLabel();
		this.kappaLabel = new JLabel();
		this.stepLabel = new JLabel();
		this.methodLabel = new JLabel();

		int length = 8;

		this.radius0Text = new JTextField(length);
		this.radius1Text = new JTextField(length);
		this.densityText = new JTextField(length);
		this.luminosityText = new JTextField(length);
		this.temperatureText = new JTextField(length);
		this.epsilonText = new JTextField(length);
		this.kappaText = new JTextField(length);
		this.stepText = new JTextField(length);

		this.runButton = new JButton();
		this.progressbar = new JProgressBar(0, 100);

		this.statusLabel.setOpaque(true);
		this.statusLabel.setText(AppConstants.STATUS_TXT);

		this.panel.setLayout(new GridBagLayout());
		this.runLabel.setText(AppConstants.RUN_LABEL_TXT);
		this.runButton.setText("Run");

		this.progressbar.setValue(0);
		this.progressLabel.setText((String) (new Integer(progressbar.getValue())).toString() + "%");

		this.radius0Label.setText(AppConstants.R0_LABEL_TXT);
		this.radius1Label.setText(AppConstants.R_LABEL_TXT);
		this.densityLabel.setText(AppConstants.DENSITY_LABEL_TXT);
		this.luminosityLabel.setText(AppConstants.LUMINOSITY_LABEL_TXT);
		this.temperatureLabel.setText(AppConstants.TEMPERATURE_LABEL_TXT);
		this.epsilonLabel.setText(AppConstants.EPSILON_LABEL_TXT);
		this.kappaLabel.setText(AppConstants.KAPPA_LABEL_TXT);
		this.stepLabel.setText(AppConstants.STEP_LABEL_TXT);
		this.methodLabel.setText(AppConstants.METHOD_LABEL_TXT);
		this.currentPressureLabel.setText("p = 0");
		this.currentRadiusLabel.setText("r = R_0");
		this.currentLuminosityLabel.setText("L = L_0");
		this.currentTemperatureLabel.setText("T = T_0");

		// set fixed size for numerical output
		// this.currentPressureLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension d = this.currentPressureLabel.getPreferredSize();
		this.currentPressureLabel.setPreferredSize(new Dimension(d.width + 180, d.height));

		d = this.progressLabel.getPreferredSize();
		this.progressLabel.setPreferredSize(new Dimension(d.width + 50, d.height));

		this.radius0Text.setText("1");
		this.radius1Text.setText("0.5");
		this.densityText.setText("1.41");
		this.luminosityText.setText("1");
		this.temperatureText.setText("5778");
		this.epsilonText.setText("1");
		this.kappaText.setText("1");
		this.stepText.setText("10000");

		this.methodNames = new String[2];
		this.methodNames[0] = "Euler";
		this.methodNames[1] = "RK4";
		this.methodBox = new JComboBox<>(this.methodNames);

		setListeners();
	}

	private void setListeners() {
		runButton.addActionListener(e -> buttonClicked(e));
	}

	private void buttonClicked(ActionEvent event) {
		changeStatus(Color.red, "Working...");

		try {

			double R0 = Double.parseDouble(this.radius0Text.getText());
			double R = Double.parseDouble(this.radius1Text.getText());
			double rho = Double.parseDouble(this.densityText.getText());
			double epsilon = Double.parseDouble(this.epsilonText.getText());
			double kappa = Double.parseDouble(this.kappaText.getText());
			double L = Double.parseDouble(this.luminosityText.getText());
			double T = Double.parseDouble(this.temperatureText.getText());
			int steps = Integer.parseInt(this.stepText.getText());
			String methodName = (String) this.methodBox.getSelectedItem();

			final IntegrationWorker integrationWorker = new IntegrationWorker(rho, epsilon, kappa, R0, R, L, T, steps,
					methodName);
			final IterationEventListener listener = this;
			Thread thread = new Thread() {
				public void run() {
					integrationWorker.addListener(listener);
					integrationWorker.addListener(new PrintPressure());
					integrationWorker.iterateToR();
					integrationWorker.removeListener(listener);
				}
			};
			thread.start();
		} catch (NumberFormatException e) {
			changeStatus(Color.red, "Input missing or wrong format");
			return;
		}
	}

	private void changeStatus(Color color, String string) {
		statusLabel.setForeground(color);
		statusLabel.setText(string);
	}

	private void constructLayout() {
		setLayout(new BorderLayout());
		constructJPanel();
		add(getInputPanel1(), BorderLayout.WEST);
		add(getInputPanel2(), BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
	}

	private JPanel getInputPanel1() {
		JPanel fileInfoPanel = new JPanel();
		Border spaceBorder = BorderFactory.createEmptyBorder(AppConstants.BORDER_SPACING, AppConstants.BORDER_SPACING,
				AppConstants.BORDER_SPACING, AppConstants.BORDER_SPACING);
		Border titleBorder = BorderFactory.createTitledBorder(AppConstants.INPUT1_TXT);
		fileInfoPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		fileInfoPanel.setLayout(new GridBagLayout());
		Insets rightPadding = new Insets(0, 0, 0, 0);
		Insets noPadding = new Insets(0, 0, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, radius0Label, 0, 0, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, radius0Text, 0, 1, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, luminosityLabel, 0, 2, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, luminosityText, 0, 3, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, temperatureLabel, 0, 4, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, temperatureText, 0, 5, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, densityLabel, 0, 6, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, densityText, 0, 7, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, epsilonLabel, 0, 8, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, epsilonText, 0, 9, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, kappaLabel, 0, 10, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, kappaText, 0, 11, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, noPadding, 0, 0);

		return fileInfoPanel;
	}

	private JPanel getInputPanel2() {
		JPanel fileInfoPanel = new JPanel();
		Border spaceBorder = BorderFactory.createEmptyBorder(AppConstants.BORDER_SPACING, AppConstants.BORDER_SPACING,
				AppConstants.BORDER_SPACING, AppConstants.BORDER_SPACING);
		Border titleBorder = BorderFactory.createTitledBorder(AppConstants.INPUT2_TXT);
		fileInfoPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		fileInfoPanel.setLayout(new GridBagLayout());
		Insets rightPadding = new Insets(0, 0, 0, 0);
		Insets noPadding = new Insets(0, 0, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, radius1Label, 0, 0, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, radius1Text, 0, 1, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, methodLabel, 0, 2, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, methodBox, 0, 3, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, stepLabel, 0, 4, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		PanelConstraints.addComponent(fileInfoPanel, stepText, 0, 5, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);

		return fileInfoPanel;
	}

	private void constructJPanel() {

		this.panel.add(runLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(runButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(progressLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(progressbar, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(currentRadiusLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(currentPressureLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(currentLuminosityLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(currentTemperatureLabel, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.panel.add(statusLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

	}

	private void constructAppWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		// setSize(AppConstants.APP_WINDOW_SIZE_WIDTH,
		// AppConstants.APP_WINDOW_SIZE_HEIGHT);
		setVisible(true);
	}

	public MainFrame() {
		super(AppConstants.APPNAME);
		initialize();
		constructLayout();
		constructAppWindow();
	}

	@Override
	public void nextIteration(IterationEvent event) {
		progressbar.setValue((int) event.getProgress());
		progressLabel.setText((String) (new Integer(progressbar.getValue())).toString() + "%");

		currentRadiusLabel.setText((String) "r = " + (new Double(event.getR())).toString() + " m");
		currentPressureLabel.setText((String) "p = " + (new Double(event.getP())).toString() + " Pa");
		currentLuminosityLabel.setText((String) "L = " + (new Double(event.getL())).toString() + " W");
		currentTemperatureLabel.setText((String) "T = " + (new Double(event.getT())).toString() + " K");

		if (event.isFinished()) {
			changeStatus(Color.black, "Finished, Results saved to file");
			progressbar.setValue(0);
			progressLabel.setText("0%");
		}
	}

}
