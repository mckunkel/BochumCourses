package statStarAntonius.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

// This field displays the Y mass fraction in dependence of the X and Z fields,
// which are settable by the user. It also implements ConditionalField so it can
// be used with ConditionalButton to ensure that the simulation is run only when
// there is a sensible Y value.
public class YFractionField extends JLabel implements FieldUpdateListener, ConditionalField {

	public static Color normal_color = new Color(0, 0, 0);
	public static Color error_color = new Color(236, 129, 0);

	DoubleField xField;
	DoubleField zField;
	double yFraction;
	boolean valid;

	private List<FieldUpdateListener> listeners;

	public YFractionField(DoubleField xField, DoubleField zField) {
		super("--");
		this.xField = xField;
		this.zField = zField;
		this.xField.addFieldUpdateListener(this);
		this.zField.addFieldUpdateListener(this);
		this.listeners = new ArrayList<>();
	}

	@Override
	public void fieldChangedUpdate() {
		update();
	}

	public void update() {
		if (this.xField.isContentValid() && this.zField.isContentValid()) {
			yFraction = 1 - (this.xField.getValue() + this.zField.getValue());
			this.setText(String.format("%.4f", yFraction));
			if (yFraction >= 0 && yFraction <= 1) {
				valid = true;
			} else {
				valid = false;
			}
		} else {
			this.setText("--");
			valid = false;
		}

		if (valid) {
			this.setForeground(normal_color);
		} else {
			this.setForeground(error_color);
		}

		for (FieldUpdateListener listener : listeners) {
			listener.fieldChangedUpdate();
		}
	}

	public double getyFraction() {
		if (valid) {
			return yFraction;
		} else {
			throw new NumberFormatException();
		}
	}

	@Override
	public void addFieldUpdateListener(FieldUpdateListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeFieldUpdateListener(FieldUpdateListener listener) {
		listeners.remove(listener);
	}

	@Override
	public boolean isContentValid() {
		return valid;
	}
}
