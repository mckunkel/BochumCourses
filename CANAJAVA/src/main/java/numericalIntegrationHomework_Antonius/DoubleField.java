package numericalIntegrationHomework_Antonius;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

//A text field that checks whether its current value can be parsed as a double, and changes its color and internal state depending on that.
public class DoubleField extends JTextField implements DocumentListener {

	static final Color normal_color = new Color(255, 255, 255);
	static final Color error_color = new Color(255, 224, 192);

	private double value;
	private boolean valid;

	public DoubleField() {
		super();
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
		verifyContents();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		verifyContents();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		verifyContents();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		verifyContents();
	}

	private void verifyContents() {
		try {
			this.value = Double.parseDouble(this.getText());
			this.valid = true;
			this.setBackground(normal_color);
		} catch (NumberFormatException exception) {
			this.valid = false;
			this.setBackground(error_color);
		} catch (NullPointerException exception) {
			this.valid = false;
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

	public boolean isContentValid() {
		verifyContents();
		return valid;
	}
}
