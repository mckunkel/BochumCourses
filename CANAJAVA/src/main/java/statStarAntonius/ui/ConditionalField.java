package statStarAntonius.ui;

// To be used in conjunction with ConditionalButton.
public interface ConditionalField {
	void addFieldUpdateListener(FieldUpdateListener listener);

	void removeFieldUpdateListener(FieldUpdateListener listener);

	boolean isContentValid();
}
