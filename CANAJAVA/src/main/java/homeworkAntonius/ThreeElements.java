package homeworkAntonius;

import java.util.ArrayList;
import java.util.List;

public class ThreeElements {

	public static void main(String[] args) {

		Hydrogen hydrogen = new Hydrogen();
		Helium helium = new Helium();
		Lithium lithium = new Lithium();

		hydrogen.printElementInfo();
		helium.printElementInfo();
		lithium.printElementInfo();

		// Yes, very good, but the idea of polymorphism would be to do somthing like
		// this
		List<Element> aElements = new ArrayList<>();
		aElements.add(new Hydrogen());
		aElements.add(new Helium());
		aElements.add(new Lithium());
		for (Element element : aElements) {
			element.printElementInfo();
		}
		// see how now a collection (List) can hold now all the information because
		// we declare them of object type Element
		// grade 1.0/1.0
	}
}