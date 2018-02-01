package statStarAntonius.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

// A text field that checks whether its current value can be parsed as a double,
// and changes its color and internal state depending on that.
// It also implements ConditionalField so it can be used in conjunction with
// ConditionalButton.
public class DoubleField extends JTextField implements DocumentListener, ConditionalField {

	static final Color normal_color = new Color(255, 255, 255);
	static final Color error_color = new Color(255, 224, 192);

	private double value;
	private boolean valid;
	private Function<Double, Boolean> isValidFunc = x -> true;

	private List<FieldUpdateListener> listeners;

	public DoubleField() {
		super();
		initialize();
	}

	public DoubleField(String text) {
		super(text);
		initialize();
	}

	public DoubleField(int columns) {
		super(columns);
		initialize();
	}

	public DoubleField(String text, int columns) {
		super(text, columns);
		initialize();
	}

	public DoubleField(Document doc, String text, int columns) {
		super(doc, text, columns);
		initialize();
	}

	private void initialize() {
		this.getDocument().addDocumentListener(this);
		this.listeners = new ArrayList<>();
		verifyContents();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		verifyContents();
		notifyListeners();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		verifyContents();
		notifyListeners();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		verifyContents();
		notifyListeners();
	}

	private void notifyListeners() {
		for (FieldUpdateListener listener : listeners) {
			listener.fieldChangedUpdate();
		}
	}

	private void verifyContents() {
		try {
			this.value = Double.parseDouble(this.getText());
			this.valid = this.isValidFunc.apply(this.value);
		} catch (NumberFormatException exception) {
			this.valid = false;
		} catch (NullPointerException exception) {
			this.valid = false;
		}
		if (this.valid) {
			this.setBackground(normal_color);
		} else {
			this.setBackground(error_color);
		}
	}

	public double getValue() {
		verifyContents();
		if (valid) {
			return value;
		} else {
			throw new NumberFormatException();
		}
	}

	@Override
	public boolean isContentValid() {
		verifyContents();
		return valid;
	}

	public void setValidFunc(Function<Double, Boolean> isValidFunc) {
		this.isValidFunc = isValidFunc;
	}

	@Override
	public void addFieldUpdateListener(FieldUpdateListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeFieldUpdateListener(FieldUpdateListener listener) {
		this.listeners.remove(listener);
	}
}
