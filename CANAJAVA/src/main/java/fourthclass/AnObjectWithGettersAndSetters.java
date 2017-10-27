package fourthclass;

public class AnObjectWithGettersAndSetters {
	String string;
	boolean b;
	double aDouble;

	public AnObjectWithGettersAndSetters(double aDubs, String aString, boolean aBool) {

		this.aDouble = aDubs;
		this.string = aString;
		this.b = aBool;
	}

	// to make getters and setters easily in Eclipse
	// MacOS press command+option+s
	// Linux might be Shift+Alt+S
	// Windows Shift+Alt+S

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public double getaDouble() {
		return aDouble;
	}

	public void setaDouble(double aDouble) {
		this.aDouble = aDouble;
	}

}
