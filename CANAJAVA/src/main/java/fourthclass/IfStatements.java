package fourthclass;

public class IfStatements {
	public static void main(String[] args) {

		char x = 0;
		System.out.println("Please enter a character from the keyboard");
		try {
			x = (char) System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (x == 'c' || x == 'C') {
			System.out.println(x + " = 'c'");
		} else if (x == 'd' || x == 'D')
			System.out.println(x + " = 'd'");
		else if (x == 'e' || x == 'E')
			System.out.println(x + " = 'e'");
		else
			System.out.println(x + " is not 'c','d' or 'e'");

		boolean aBool = false;
		if (aBool == false) {
			System.out.println("a) The value of aBool is false");
		}
		if (!aBool) {
			System.out.println("b) The value of aBool is false");
		}

	}
}
