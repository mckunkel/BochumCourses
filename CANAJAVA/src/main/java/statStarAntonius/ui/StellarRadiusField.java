package statStarAntonius.ui;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StellarRadiusField extends JLabel implements DocumentListener {

	private DoubleField temperatureField;
	private DoubleField luminosityField;
	public StellarRadiusField(DoubleField temperatureField, DoubleField luminosityField) {
		super();
		this.temperatureField = temperatureField;
		this.luminosityField = luminosityField;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	
	public void update() {
		
	}
}
