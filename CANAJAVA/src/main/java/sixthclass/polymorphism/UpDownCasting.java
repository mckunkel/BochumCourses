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
package sixthclass.polymorphism;

public class UpDownCasting {

	public static void main(String[] args) {
		new StuntRider().getStuntReady(new Car());
		System.out.println("###");
		upCast();
	}

	// downCast();
	// upCast();
	private static void downCast() {
		Vehicle vehicle = new Vehicle();
		Car car = (Car) vehicle;
		// or
		Vehicle vehicle2 = new Car();
		Car car2 = (Car) vehicle2;
	}

	private static void brokedownCast() {

		Vehicle vehicle2 = new Car();
		MotorCycle motorCycle = (MotorCycle) vehicle2;

		if (vehicle2 instanceof Car) {
			Car car = (Car) vehicle2;
			car.engineSound();
		} else if (vehicle2 instanceof MotorCycle) {
			MotorCycle motorCycle2 = (MotorCycle) vehicle2;
			motorCycle2.engineSound();
		}

	}

	private static void upCast() {

		Car car = new Car();
		Vehicle vehicle = (Car) car;
		vehicle.startEngine();
		vehicle.engineSound();

	}
}

class StuntRider {
	public void getStuntReady(Vehicle vehicle) {
		vehicle.startEngine();
		vehicle.idleEngine();

		if (vehicle instanceof Car) {
			Car car = (Car) vehicle;
			vehicle.engineSound();
		}
	}
}
