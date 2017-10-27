package fourthclass;

public class AnObject {

	double pi;

	public AnObject() {
		this.pi = Math.PI;
		System.out.println("The value of pi is " + pi);
	}

	String string;
	boolean b;
	double aDouble;

	public AnObject(double aDubs, String aString, boolean aBool) {
		this.aDouble = aDubs;
		this.string = aString;
		this.b = aBool;
		printValues();
	}

	private void printValues() {
		System.out.println("The value of the double is " + this.aDouble);
		System.out.println("The value of the string is " + this.string);
		System.out.println("The value of the boolean is " + this.b);

	}
}
