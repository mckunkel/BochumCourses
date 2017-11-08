package fifthclass;

import fourthclass.AnObjectWithGettersAndSetters;

public class UsingConstuctorsWithArguments {

	public static void main(String[] args) {
		AnObjectWithGettersAndSetters anObject = new AnObjectWithGettersAndSetters(10, "myString", true);
		System.out.println(anObject.getaDouble());
		System.out.println(anObject.getString());
		System.out.println(anObject.isB());

	}
}
