package eigthclass.GUI.simple;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MiniFrame extends JFrame {

	private ContentPanel contentPanel;

	public MiniFrame() {
		super("Mini Me");
		this.contentPanel = new ContentPanel();
		constructAppWindow();
	}

	private void constructAppWindow() {
		setLayout(new FlowLayout());
		add(this.contentPanel);
		add(new ScrollPanel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ...

		setLocationRelativeTo(null);
		pack();
		setSize(Constants.APP_WINDOW_SIZE_WIDTH / 3, Constants.APP_WINDOW_SIZE_HEIGHT);

		setVisible(true);
	}

	// Here we will construct some stuff together
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
				new MiniFrame();
			}
		});
	}
}
