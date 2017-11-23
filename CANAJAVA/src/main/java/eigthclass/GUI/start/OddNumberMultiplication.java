package eigthclass.GUI.start;

public class OddNumberMultiplication {

	private int result;

	public OddNumberMultiplication() {
		result = 0;
	}

	public void calculateOddMultiplication() {
		for (int i = 1; i < 10000; i++) {
			for (int j = 1; j < 10000; j++) {
				if (isOdd(i) && isOdd(j)) {
					result = i * j;
				}
			}
		}
	}

	private boolean isOdd(int n) {
		return n % 2 == 0 ? false : true;
	}

}
