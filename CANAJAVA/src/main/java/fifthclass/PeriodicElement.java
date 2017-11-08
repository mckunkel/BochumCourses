package fifthclass;

public class PeriodicElement {

	int elementNumber;
	String elementName;
	String elementalIsotope;

	public PeriodicElement(int elementNumber, String elementName, String elementalIsotope) {
		this.elementNumber = elementNumber;
		this.elementName = elementName;
		this.elementalIsotope = elementalIsotope;
	}

	public void excite() {

	}

	public void decay() {

	}

	public static void main(String[] args) {
		PeriodicElement hydrogen = new PeriodicElement(1, "Hydrogen", "H2");
	}
}
