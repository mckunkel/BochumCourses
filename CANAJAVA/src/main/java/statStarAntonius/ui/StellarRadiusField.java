package statStarAntonius.ui;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import statStarAntonius.Const;

public class StellarRadiusField extends JLabel implements DocumentListener {

	private DoubleField temperatureField;
	private DoubleField luminosityField;
	private double radius = 0.;
	private boolean valid = false;

	public StellarRadiusField(DoubleField temperatureField, DoubleField luminosityField) {
		super("--");
		this.temperatureField = temperatureField;
		this.luminosityField = luminosityField;
		luminosityField.getDocument().addDocumentListener(this);
		temperatureField.getDocument().addDocumentListener(this);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		update();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		update();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		update();
	}

	public void update() {
		if (this.temperatureField.isContentValid() && this.luminosityField.isContentValid()) {
			double T = temperatureField.getValue();
			double L = luminosityField.getValue() * Const.lSolar;
			radius = Math.sqrt(L / (4 * Math.PI * Const.sigma)) / (T * T);
			this.setText(String.format("%.4f", radius / Const.rSolar));
			valid = true;
		} else {
			this.setText("--");
			valid = false;
		}
	}

	public double getRadius() {
		if (valid) {
			return radius;
		} else {
			throw new NumberFormatException();
		}
	}

	public boolean isValid() {
		return valid;
	}
}
