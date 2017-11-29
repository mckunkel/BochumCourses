package eigthclasshomeworktimo;

import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PressureGUI extends JFrame {

	private JPanel aJPanel;
	private JLabel aJLabel1;
	private JTextField aJTextField1;
	private JLabel aJLabel2;
	private JTextField aJTextField2;
	private JLabel aJLabel3;
	private JTextField aJTextField3;
	private JLabel aJLabel4;
	private JTextField aJTextField4;
	private JLabel aJLabel5;
	private JTextField aJTextField5;
	private JComboBox<String> aJComboBox;
	private JButton aJButton;

	private void init() {
		String[] methodnames = new String[2];
		methodnames[0] = "Euler";
		methodnames[1] = "RK4";

		this.aJPanel = new JPanel();
		this.aJLabel1 = new JLabel("Radius of star in solar radii");
		this.aJTextField1 = new JTextField(5);
		this.aJLabel2 = new JLabel("Radius for pressure evaluation");
		this.aJTextField2 = new JTextField(5);
		this.aJLabel3 = new JLabel("Density in solar densities");
		this.aJTextField3 = new JTextField(5);
		this.aJLabel4 = new JLabel("Number of integration steps");
		this.aJTextField4 = new JTextField(5);
		this.aJLabel5 = new JLabel("Integration method");
		this.aJComboBox = new JComboBox<>(methodnames);
		this.aJButton = new JButton("Calculate pressure in Pa");
		this.aJButton.addActionListener(
				e -> System.out.println(IntegrateODE.iterateToR(Double.parseDouble(this.aJTextField1.getText()),
						Double.parseDouble(this.aJTextField2.getText()),
						Double.parseDouble(this.aJTextField3.getText()), Integer.parseInt(this.aJTextField4.getText()),
						(String) this.aJComboBox.getSelectedItem())));
		makeJPanel();
	}

	private void makeJPanel() {
		this.aJPanel.setLayout(new BoxLayout(this.aJPanel, BoxLayout.Y_AXIS));
		this.aJPanel.add(this.aJLabel1);
		this.aJPanel.add(this.aJTextField1);
		this.aJPanel.add(this.aJLabel2);
		this.aJPanel.add(this.aJTextField2);
		this.aJPanel.add(this.aJLabel3);
		this.aJPanel.add(this.aJTextField3);
		this.aJPanel.add(this.aJLabel4);
		this.aJPanel.add(this.aJTextField4);
		this.aJPanel.add(this.aJLabel5);
		this.aJPanel.add(this.aJComboBox);
		this.aJPanel.add(this.aJButton);
	}

	private void constructWindow() {
		add(aJPanel);
		java.awt.Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screensize.getWidth();
		int height = (int) screensize.getHeight();
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}

	public PressureGUI() {
		super("PressureGUI");
		init();
		constructWindow();
	}

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
				new PressureGUI();
			}
		});
	}
}
