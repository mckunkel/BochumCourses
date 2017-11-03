package fourthclass;

import org.jlab.groot.data.H2F;

public class UsingFinally {
	public static void main(String[] args) {
		System.out.println("Is -2 degrees cold? " + isCold(-2));
		H2F aH2f = new H2F("aname", 3, 0, 4, 3, 0, 4);
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
