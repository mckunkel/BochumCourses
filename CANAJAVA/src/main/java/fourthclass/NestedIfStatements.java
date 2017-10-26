package fourthclass;

public class NestedIfStatements {
	public static void main(String[] args) {
		int temperature = -3;
		if (temperature > 0) {
			System.out.println("It is not cold");
			if (temperature % 2 == 0) {
				System.out.println("It is an even temperature");
			} else
				System.out.println("It is an odd temperature");

		} else {
			System.out.println("It is cold");
			if (temperature % 2 == 0) {
				System.out.println("It is an even temperature");
			} else
				System.out.println("It is an odd temperature");
		}
	}
}
