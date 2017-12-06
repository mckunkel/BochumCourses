package numericalIntegrationHomework_Antonius;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//This button is meant to work with text fields like the double field class. It maintains a list of text fields and enables itself only when all of the text fields have valid content. (Technically, this could be an interface, but that has not been necessary yet.) 
public class ConditionalButton extends JButton implements DocumentListener {

	private List<DoubleField> attachedFields;
	private boolean listening;

	public ConditionalButton() {
		super();
		initialize();
	}

	public ConditionalButton(Icon arg0) {
		super(arg0);
		initialize();
	}

	public ConditionalButton(String arg0) {
		super(arg0);
		initialize();
	}

	public ConditionalButton(Action arg0) {
		super(arg0);
		initialize();
	}

	public ConditionalButton(String arg0, Icon arg1) {
		super(arg0, arg1);
		initialize();
	}

	private void initialize() {
		this.attachedFields = new ArrayList<>();
		this.listening = true;
		update();
	}

	public void addField(DoubleField field) {
		this.attachedFields.add(field);
		field.getDocument().addDocumentListener(this);
		update();
	}

	public void removeField(DoubleField field) {
		this.attachedFields.remove(field);
		field.getDocument().removeDocumentListener(this);
		update();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		update();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		update();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		update();
	}

	private void update() {
		if (this.listening) {
			boolean all_valid = true;

			for (DoubleField field : this.attachedFields) {
				all_valid &= field.isContentValid();
			}

			if (all_valid) {
				this.setEnabled(true);
			} else {
				this.setEnabled(false);
			}
		}
	}

	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
		if (listening == true) {
			update();
		}
	}
}
