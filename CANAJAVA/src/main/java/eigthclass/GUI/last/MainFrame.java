package eigthclass.GUI.last;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MainFrame extends JFrame implements IterationEventListener {

	private JPanel jPanel1;

	private JLabel statusBar;
	private JLabel runLabel;
	private JLabel currentValueLabel;
	private JLabel progressLabel;
	private JLabel jLabel2;
	private JLabel iLabel;
	private JLabel jLabel;

	private JButton jButtonRun;
	private JProgressBar jProgressBar;

	private JTextField text_I;
	private JTextField text_J;

	public MainFrame() {
		super(Constants.APPNAME);
		initializeVariables();
		constructlayout();
		constructAppWindow();
	}

	private void initializeVariables() {
		this.jPanel1 = new JPanel();
		this.statusBar = new JLabel();
		this.runLabel = new JLabel();
		this.currentValueLabel = new JLabel();
		this.progressLabel = new JLabel();
		this.jLabel2 = new JLabel();

		this.jButtonRun = new JButton();
		this.jProgressBar = new JProgressBar(0, 100);

		this.statusBar.setOpaque(true);
		this.statusBar.setText(Constants.STATUSBAR_TXT);
		this.jPanel1.setLayout(new GridBagLayout());
		this.runLabel.setText(Constants.RUN_TXT);
		this.currentValueLabel.setText("Not Running");
		this.jButtonRun.setText("Run");
		this.jProgressBar.setValue(0);
		this.progressLabel.setText((String) (new Integer(jProgressBar.getValue())).toString() + "%");

		this.jLabel2.setText(Constants.CURRENTVALUE);

		this.text_I = new JTextField(Constants.TEXT_FIELD_LENGTH);
		this.iLabel = new JLabel(Constants.ILABEL);

		this.text_J = new JTextField(Constants.TEXT_FIELD_LENGTH);
		this.jLabel = new JLabel(Constants.JLABEL);

		setListeners();
	}

	private JPanel getPanel() {
		JPanel fileInfoPanel = new JPanel();
		Border spaceBorder = BorderFactory.createEmptyBorder(Constants.BORDER_SPACING, Constants.BORDER_SPACING,
				Constants.BORDER_SPACING, Constants.BORDER_SPACING);
		Border titleBorder = BorderFactory.createTitledBorder(Constants.FILE_FORM_SELECT);
		fileInfoPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		fileInfoPanel.setLayout(new GridBagLayout());
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);

		///// First row /////////////////////////////
		PanelConstraints.addComponent(fileInfoPanel, iLabel, 0, 0, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);
		PanelConstraints.addComponent(fileInfoPanel, text_I, 1, 0, 1, 1, GridBagConstraints.LINE_END,
				GridBagConstraints.NONE, noPadding, 0, 0);
		PanelConstraints.addComponent(fileInfoPanel, jLabel, 0, 1, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);
		PanelConstraints.addComponent(fileInfoPanel, text_J, 1, 1, 1, 1, GridBagConstraints.LINE_END,
				GridBagConstraints.NONE, noPadding, 0, 0);
		return fileInfoPanel;
	}

	private void constructlayout() {
		setLayout(new BorderLayout());
		constructJPanel1();
		add(statusBar, BorderLayout.SOUTH);
		add(jPanel1, BorderLayout.CENTER);
	}

	private void constructJPanel1() {
		this.jPanel1.add(runLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		this.jPanel1.add(jButtonRun, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		this.jPanel1.add(jProgressBar, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		this.jPanel1.add(progressLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		this.jPanel1.add(jLabel2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		this.jPanel1.add(currentValueLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		this.jPanel1.add(getPanel(), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 100, 10));
	}

	private void setListeners() {
		jButtonRun.addActionListener(e -> buttonClicked(e));
	}

	private void buttonClicked(ActionEvent event) {
		changeStatus(Color.red, "Working Please wait...");
		int iMax;
		int jMax;
		if (text_I.getText().isEmpty() || text_J.getText().isEmpty()) {
			iMax = Constants.MAXI;
			jMax = Constants.MAXJ;
		} else {
			iMax = Integer.parseInt(text_I.getText());
			jMax = Integer.parseInt(text_J.getText());
		}

		final OddNumberMultiplication oMultiplication = new OddNumberMultiplication(iMax, jMax);
		final IterationEventListener listener = this;
		Thread thread = new Thread() {
			public void run() {
				oMultiplication.addListener(listener);
				oMultiplication.calculateOddMultiplication();
				oMultiplication.removeListener(listener);
			}
		};
		thread.start();
	}

	private void constructAppWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(Constants.APP_WINDOW_SIZE_WIDTH / 2, Constants.APP_WINDOW_SIZE_HEIGHT / 2);

		setVisible(true);
	}

	@Override
	public void nextIteration(IterationEvent event) {
		jProgressBar.setValue((int) event.getProgress());
		progressLabel.setText((String) (new Integer(jProgressBar.getValue())).toString() + "%");
		currentValueLabel.setText((String) (new Integer(event.getValue()).toString()));

		if (event.isFinished()) {
			changeStatus(Color.getColor("204,204,204"), "Ready");
			jProgressBar.setValue(0);
			progressLabel.setText("0%");
			currentValueLabel.setText("Not Running");
		}
	}

	private void changeStatus(Color color, String string) {
		statusBar.setForeground(color);
		statusBar.setText(string);
	}
}
