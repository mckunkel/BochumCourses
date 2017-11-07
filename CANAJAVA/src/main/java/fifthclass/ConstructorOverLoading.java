package fifthclass;

import java.util.ArrayList;
import java.util.List;

public class ConstructorOverLoading {

	int x;
	String string;
	List<Double> aList;

	public ConstructorOverLoading(int x, String string, List<Double> aList) {
		this.x = x;
		this.string = string;
		this.aList = aList;
		printConstructorInformation(x, string, aList);
	}

	public ConstructorOverLoading(int x, String string) {
		this.x = x;
		this.string = string;
		printConstructorInformation(x, string);

	}

	public ConstructorOverLoading(int x) {
		this.x = x;
		printConstructorInformation(x);

	}

	public ConstructorOverLoading() {
		this(4, "using this constructor");
	}

	private void printConstructorInformation(int x) {
		System.out.println("Value of x = " + x);
	}

	private void printConstructorInformation(int x, String string) {
		printConstructorInformation(x);
		System.out.println("Value of the string is " + string);

	}

	private void printConstructorInformation(int x, String string, List<Double> aList) {
		printConstructorInformation(x, string);
		System.out.println("List values are ");
		for (Double double1 : aList) {
			System.out.println(double1);
		}

	}

	public static void main(String[] args) {
		List<Double> myList = new ArrayList<>(); // Not declaring the type in the
		// ArrayList constructor is a feature for Java 8 and above
		myList.add(1.2);
		myList.add(Math.PI);
		myList.add(Math.exp(-2.9 / 3));

		ConstructorOverLoading c3Loading = new ConstructorOverLoading(3, "3 arg constructor ", myList);
		ConstructorOverLoading c2Loading = new ConstructorOverLoading(2, "2 arg constructor ");
		ConstructorOverLoading c1Loading = new ConstructorOverLoading(1);

		ConstructorOverLoading cLoading = new ConstructorOverLoading();

	}

}
