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

public class DrivingClass {

	public static void main(String[] args) {
		Vehicle[] vehicle = new Vehicle[3];
		vehicle[0] = new Car();
		vehicle[1] = new MotorCycle();
		vehicle[2] = new Truck();

		vehicle[0].engineSound();
		vehicle[1].engineSound();
		vehicle[2].engineSound();

	}

}
