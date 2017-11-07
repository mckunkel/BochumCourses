package fifthclass;

public class CallingNestedClass {
	public static void main(String[] args) {
		// For Static classes
		NestedClass.StaticClass asStaticClass = new NestedClass.StaticClass();

		// For Inner classes
		NestedClass outerObj = new NestedClass();
		NestedClass.InnerClass innerObj = outerObj.new InnerClass();
		// OR
		NestedClass.InnerClass innerObj2 = new NestedClass().new InnerClass();
	}
}
