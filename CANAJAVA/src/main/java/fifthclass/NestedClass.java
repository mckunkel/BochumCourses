package fifthclass;

public class NestedClass {

	private String str = "NestedClass variable";

	public class InnerClass {

		String str = "InnerClass variable";

		public void printValues() {
			System.out.println("InnerClass class variable is " + str);
			System.out.println("NestedClass class variable is " + NestedClass.this.str);
		}
	}

	public static class StaticClass {

		String str = "StaticClass variable";

		public void printValues() {
			System.out.println("StaticClass class variable is " + str);
		}
	}

	public static void main(String[] args) {

		System.out.println("NestedClass class variable is " + new NestedClass().str);
		System.out.println("############");
		new NestedClass().new InnerClass().printValues();
		System.out.println("############");
		new StaticClass().printValues();

	}

}
