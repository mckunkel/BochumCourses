package statStarAntonius.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

// This button is meant to work with text fields implementing the
// ConditionalField interface. It maintains a list of text fields and enables
// itself only when all of the text fields have valid content.
public class ConditionalButton extends JButton implements FieldUpdateListener {

	private List<ConditionalField> attachedFields;
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

	public void addField(ConditionalField field) {
		this.attachedFields.add(field);
		field.addFieldUpdateListener(this);
		update();
	}

	public void removeField(ConditionalField field) {
		this.attachedFields.remove(field);
		field.removeFieldUpdateListener(this);
		update();
	}

	@Override
	public void fieldChangedUpdate() {
		update();
	}

	private void update() {
		if (this.listening) {
			boolean all_valid = true;

			for (ConditionalField field : this.attachedFields) {
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
