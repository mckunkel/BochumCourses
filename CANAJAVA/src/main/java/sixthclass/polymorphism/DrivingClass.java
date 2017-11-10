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

import java.util.Random;

public class DrivingClass {

	public static void main(String[] args) {
		Vehicle[] vehicle = new Vehicle[3];
		vehicle[0] = new Car();
		vehicle[1] = new MotorCycle();
		vehicle[2] = new Truck();

		// vehicle[0].engineSound();
		// vehicle[1].engineSound();
		// vehicle[2].engineSound();

		// another example of run-time binding
		Random aRandom = new Random();
		for (int i = 0; i < 22; i++) {
			int myRandom = aRandom.nextInt(3);
			if (myRandom % 3 == 0) {
				vehicle[2].engineSound();
			}
			if (myRandom % 2 == 0) {
				vehicle[1].engineSound();
			}
			if (myRandom % 1 == 0) {
				vehicle[0].engineSound();
			}
		}

	}

}
