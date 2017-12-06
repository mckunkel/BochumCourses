package eigthclasshomeworktimo;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements IterationEventListener {

	private JPanel panel;

	private JLabel statusLabel;
	private JLabel runLabel;
	private JLabel progressLabel;

	private JLabel currentRadiusLabel;
	private JLabel currentPressureLabel;

	private JLabel radius0Label;
	private JLabel radius1Label;
	private JLabel densityLabel;
	private JLabel stepLabel;
	private JLabel methodLabel;

	private JTextField radius0Text;
	private JTextField radius1Text;
	private JTextField densityText;
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
		this.radius0Label = new JLabel();
		this.radius1Label = new JLabel();
		this.densityLabel = new JLabel();
		this.stepLabel = new JLabel();
		this.methodLabel = new JLabel();
		this.radius0Text = new JTextField(5);
		this.radius1Text = new JTextField(5);
		this.densityText = new JTextField(5);
		this.stepText = new JTextField(5);
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
		this.stepLabel.setText(AppConstants.STEP_LABEL_TXT);
		this.methodLabel.setText(AppConstants.METHOD_LABEL_TXT);

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

		double R0 = Double.parseDouble(this.radius0Text.getText());
		double R = Double.parseDouble(this.radius1Text.getText());
		double rho = Double.parseDouble(this.densityText.getText());
		int steps = Integer.parseInt(this.stepText.getText());
		String methodName = (String) this.methodBox.getSelectedItem();

		final IntegrationWorker integrationWorker = new IntegrationWorker(rho, R0, R, steps, methodName);
		integrationWorker.addListener(this);
		integrationWorker.addListener(new PrintPressure());
		integrationWorker.iterateToR();
		integrationWorker.removeListener(this);
	}

	private void changeStatus(Color color, String string) {
		statusLabel.setForeground(color);
		statusLabel.setText(string);
	}

	private void constructLayout() {

	}

	private void constructAppWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(AppConstants.APP_WINDOW_SIZE_WIDTH, AppConstants.APP_WINDOW_SIZE_HEIGHT);
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
		currentPressureLabel.setText((String) "r = " + (new Double(event.getP())).toString() + " m");

		if (event.isFinished()) {
			changeStatus(Color.black, "Finished, Results saved to file");
			progressbar.setValue(0);
			progressLabel.setText("0%");
		}
	}

}
