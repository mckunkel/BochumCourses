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
package sixthclassMKhomework;

public class PeriodicTableElement implements NuclearProperties {
	protected int Z;
	protected int N;

	// now for isotopes
	protected int minA;
	protected int maxA;
	protected int nIsotopes;

	public int getA() {
		return this.N + this.Z;
	}

	public void setN(int N) {
		this.N = N;
	}

	public int getN() {
		return this.N;
	}

	public int getZ() {
		return this.Z;
	}

	public int getMinA() {
		return this.minA;
	}

	public int getMaxA() {
		return this.maxA;
	}

	public int getNIsotopes() {
		return this.nIsotopes;
	}

	public double getBindingEnergy() {
		return NuclearFunctions.bindingEnergy(this.getA(), this.getZ());
	}
}
