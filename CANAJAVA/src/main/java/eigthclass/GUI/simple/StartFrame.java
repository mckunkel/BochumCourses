package eigthclass.GUI.simple;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class StartFrame extends JFrame {

	public StartFrame() {
		super("A JFrame for you CANAJava");
		constructAppWindow();
	}

	private void constructAppWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(Constants.APP_WINDOW_SIZE_WIDTH, Constants.APP_WINDOW_SIZE_HEIGHT);

		setVisible(true);
	}

	// Here we will construct some stuff together

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new StartFrame();
			}
		});
	}
}
