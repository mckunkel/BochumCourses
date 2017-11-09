/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package sixthclass;

public class UpDownCasting {
	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		Car car = (Car) vehicle;
	}

	private void downCast() {
		Vehicle vehicle = new Vehicle();
		Car car = (Car) vehicle;
		// or
		Vehicle vehicle2 = new Car();
		Car car2 = (Car) vehicle2;
	}

	private void upCast() {
		Car car = new Car();
		Vehicle vehicle = (Car) car;

	}
}
