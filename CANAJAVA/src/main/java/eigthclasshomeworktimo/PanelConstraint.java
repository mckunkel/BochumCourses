package eigthclasshomeworktimo;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PanelConstraint extends GridBagConstraints {

	public static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, int anchor, int fill, int ipadx, int ipady) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0, anchor, fill,
				AppConstants.insets, ipadx, ipady);
		container.add(component, gbc);
	}

	public static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, double weightx, double weighty, int anchor, int fill, int ipadx, int ipady) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor,
				fill, AppConstants.insets, ipadx, ipady);
		container.add(component, gbc);
	}

	public static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, int anchor, int fill, Insets inset, int ipadx, int ipady) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0, anchor, fill,
				inset, ipadx, ipady);
		container.add(component, gbc);
	}

	public static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, double weightx, double weighty, int anchor, int fill, Insets inset, int ipadx, int ipady) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor,
				fill, inset, ipadx, ipady);
		container.add(component, gbc);
	}
}
