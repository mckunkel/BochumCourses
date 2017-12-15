package eightclass.GUI.classexample;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {// implements ActionListener

	private JPanel aJPanel;
	private JLabel aJLabel;
	private JTextField aJTextField;
	private JButton aJButton;

	public MainFrame() {
		super("An Example");
		init();
		constructWindow();
	}

	private void init() {
		this.aJPanel = new JPanel();
		this.aJLabel = new JLabel("This is my label");
		this.aJTextField = new JTextField("default text yyyyyy", 10);

		Font bigFont = aJTextField.getFont().deriveFont(Font.PLAIN, 25f);
		aJTextField.setFont(bigFont);

		this.aJButton = new JButton("A button");
		this.aJButton.addActionListener(e -> System.out.println(this.aJTextField.getText()));
		makeJPanel();
	}

	private void makeJPanel() {
		// this.aJPanel.setLayout(new FlowLayout());
		this.aJPanel.setLayout(new GridLayout(2, 2));

		this.aJPanel.add(this.aJLabel);
		this.aJPanel.add(this.aJTextField);
		this.aJPanel.add(this.aJButton);
	}

	private void constructWindow() {
		setLayout(new FlowLayout());
		add(this.aJPanel);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth() - (int) screenSize.getWidth() / 3;
		int height = (int) screenSize.getHeight() - (int) screenSize.getHeight() / 3;
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	// @Override
	// public void actionPerformed(ActionEvent e) {
	// if (e.getSource() == this.aJButton) {
	// System.out.println(this.aJTextField.getText());
	// }
	// }

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new MainFrame();
			}
		});
	}

}
