package numericalIntegrationHomework_Antonius;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

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

	public MainFrame() {
		super("Pressure Integration");
		initializeVariables();
		constructLayout();
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
	}

	public void constructAppWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(640, 400);
		setVisible(true);
	}
}
