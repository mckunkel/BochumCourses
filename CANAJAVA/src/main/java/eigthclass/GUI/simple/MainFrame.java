package eigthclass.GUI.simple;

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

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel jPanel1;

	private JLabel statusBar;
	private JLabel runLabel;
	private JLabel currentValueLabel;
	private JLabel progressLabel;
	private JLabel jLabel2;

	private JButton jButtonRun;
	private JProgressBar jProgressBar;

	private JTextField text_I;
	private JTextField text_J;
	private JLabel iLabel;
	private JLabel jLabel;

	public MainFrame() {
		super(Constants.APPNAME);
		initializeVariables();
		constructlayout();
		constructAppWindow();
	}

	private void initializeVariables() {
		contentPane = (JPanel) this.getContentPane();
		jPanel1 = new JPanel();
		statusBar = new JLabel();
		runLabel = new JLabel();
		currentValueLabel = new JLabel();
		progressLabel = new JLabel();
		jLabel2 = new JLabel();

		jButtonRun = new JButton();
		jProgressBar = new JProgressBar(0, 100);

		statusBar.setOpaque(true);
		statusBar.setText(Constants.STATUSBAR_TXT);
		jPanel1.setLayout(new GridBagLayout());
		runLabel.setText(Constants.RUN_TXT);
		currentValueLabel.setText("Not Running");
		jButtonRun.setText("Run");
		jProgressBar.setValue(0);
		progressLabel.setText((String) (new Integer(jProgressBar.getValue())).toString() + "%");

		jLabel2.setText("Current Value:");

		this.text_I = new JTextField(Constants.TEXT_FIELD_LENGTH);
		this.iLabel = new JLabel(Constants.ILABEL);

		this.text_J = new JTextField(Constants.TEXT_FIELD_LENGTH);
		this.jLabel = new JLabel(Constants.JLABEL);

		setListeners();
	}

	private JPanel getPercentagePanel() {
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
		contentPane.setLayout(new BorderLayout());
		contentPane.add(statusBar, BorderLayout.SOUTH);
		contentPane.add(jPanel1, BorderLayout.CENTER);
		jPanel1.add(runLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jButtonRun, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jProgressBar, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(progressLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jLabel2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(currentValueLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		jPanel1.add(getPercentagePanel(), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 100, 10));

	}

	private void setListeners() {
		jButtonRun.addActionListener(e -> buttonClicked(e));
	}

	private void buttonClicked(ActionEvent event) {
		System.out.println("I was clicked");
		change_status(Color.BLUE, "I was clicked");
	}

	private void constructAppWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(Constants.APP_WINDOW_SIZE_WIDTH, Constants.APP_WINDOW_SIZE_HEIGHT);

		setVisible(true);
	}

	private void change_status(Color color, String string) {
		statusBar.setForeground(color);
		statusBar.setText(string);
	}
}
