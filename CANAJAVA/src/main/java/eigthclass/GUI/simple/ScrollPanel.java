package eigthclass.GUI.simple;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ScrollPanel extends JPanel implements ActionListener {

	private JButton runButton;
	private JLabel runLabel;

	private JComboBox<Integer> textIComboBox;
	private JComboBox<Integer> textJComboBox;

	private JLabel iLabel;
	private JLabel jLabel;

	public ScrollPanel() {
		initialize();
		constructLayout();
	}

	private void initialize() {
		setLayout(new GridBagLayout());
		Border spaceBorder = BorderFactory.createEmptyBorder(Constants.BORDER_SPACING, Constants.BORDER_SPACING,
				Constants.BORDER_SPACING, Constants.BORDER_SPACING);
		Border titleBorder = BorderFactory.createTitledBorder(Constants.SCROLLPANEL_TXT);

		setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		this.runButton = new JButton();
		this.runButton.addActionListener(this);
		this.runButton.setText(Constants.RUNBUTTON_TXT);

		this.runLabel = new JLabel();
		this.runLabel.setText(Constants.RUN_TXT);

		this.textIComboBox = new JComboBox(Constants.numberStrings);
		this.iLabel = new JLabel(Constants.ILABEL);

		this.textJComboBox = new JComboBox(Constants.numberStrings);
		this.jLabel = new JLabel(Constants.JLABEL);

	}

	private JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel();
		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder(Constants.BUTNPANEL_SUBTITLE);

		buttonPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridy = 0;
		// ///// First row /////////////////////////////
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = rightPadding;
		buttonPanel.add(runLabel, gc);

		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		buttonPanel.add(runButton, gc);

		// Next Row
		// gc.gridy++;

		return buttonPanel;
	}

	private JPanel getComboPanel() {
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
		PanelConstraints.addComponent(fileInfoPanel, textIComboBox, 1, 0, 1, 1, GridBagConstraints.LINE_END,
				GridBagConstraints.NONE, noPadding, 0, 0);
		PanelConstraints.addComponent(fileInfoPanel, jLabel, 0, 1, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, rightPadding, 0, 0);
		PanelConstraints.addComponent(fileInfoPanel, textJComboBox, 1, 1, 1, 1, GridBagConstraints.LINE_END,
				GridBagConstraints.NONE, noPadding, 0, 0);
		return fileInfoPanel;
	}

	private void constructLayout() {

		PanelConstraints.addComponent(this, getComboPanel(), 0, 0, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, 0, 0);
		PanelConstraints.addComponent(this, buttonPanel(), 0, 1, 1, 1, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, 0, 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.runButton) {
			buttonClicked(Integer.valueOf((String) textIComboBox.getSelectedItem()),
					Integer.valueOf((String) textJComboBox.getSelectedItem()));
		}
	}

	private void buttonClicked(Integer i, Integer j) {
		System.out.println("I was clicked from ScrollPanel with i value: " + i + " j value: " + j);
	}
}
