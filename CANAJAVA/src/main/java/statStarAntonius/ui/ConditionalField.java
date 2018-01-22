package statStarAntonius.ui;

public interface ConditionalField {
	void addFieldUpdateListener(FieldUpdateListener listener);

	void removeFieldUpdateListener(FieldUpdateListener listener);

	boolean isContentValid();
}
