package fourthclass;

public class UsingFinally {
	public static void main(String[] args) {
		System.out.println("Is -2 degrees cold? " + isCold(-2));
	}

	private static boolean isCold(int i) {
		try {
			if (i > -1) {
				return false;
			} else {
				return true;
			}
		} finally {
			System.out.println("true: Yes is is cold \nfalse : No it is not that cold");
		}
	}
}
