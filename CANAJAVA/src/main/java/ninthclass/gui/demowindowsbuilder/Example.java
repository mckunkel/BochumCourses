package ninthclass.gui.demowindowsbuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Example {

	private JFrame frame;
	private JTable table;
	private JButton btnButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Example window = new Example();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Example() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 582, 298);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 2, 2, 2));

		JPanel pnl1 = new JPanel();
		frame.getContentPane().add(pnl1);
		pnl1.setLayout(new BorderLayout(0, 0));

		btnButton = new JButton("Button");
		pnl1.add(btnButton, BorderLayout.CENTER);

		JPanel pnl2 = new JPanel();
		frame.getContentPane().add(pnl2);
		pnl2.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		pnl2.add(table, BorderLayout.CENTER);

		JPanel pnl3 = new JPanel();
		frame.getContentPane().add(pnl3);

		JRadioButton rdbtnRadio = new JRadioButton("Radio1");

		JRadioButton rdbtnRadio_1 = new JRadioButton("Radio2");
		GroupLayout gl_pnl3 = new GroupLayout(pnl3);
		gl_pnl3.setHorizontalGroup(gl_pnl3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl3.createSequentialGroup().addContainerGap().addGroup(gl_pnl3
						.createParallelGroup(Alignment.LEADING).addComponent(rdbtnRadio).addComponent(rdbtnRadio_1))
						.addContainerGap(143, Short.MAX_VALUE)));
		gl_pnl3.setVerticalGroup(gl_pnl3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl3.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnRadio).addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnRadio_1)
						.addGap(114)));
		pnl3.setLayout(gl_pnl3);

		JPanel pnl4 = new JPanel();
		frame.getContentPane().add(pnl4);
		pnl4.setLayout(new BorderLayout(0, 0));
	}
}
