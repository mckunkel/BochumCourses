package statStarAntonius.ui;

import javax.swing.JLabel;

import statStarAntonius.Const;

// This custom field always displays the stellar radius in dependence of surface
// temperature and luminosity. It automatically updates whenever one of those
// fields changes.
public class StellarRadiusField extends JLabel implements FieldUpdateListener {

	private DoubleField temperatureField;
	private DoubleField luminosityField;
	private double radius = 0.;
	private boolean valid = false;

	public StellarRadiusField(DoubleField temperatureField, DoubleField luminosityField) {
		super("--");
		this.temperatureField = temperatureField;
		this.luminosityField = luminosityField;
		luminosityField.addFieldUpdateListener(this);
		temperatureField.addFieldUpdateListener(this);
	}

	@Override
	public void fieldChangedUpdate() {
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
