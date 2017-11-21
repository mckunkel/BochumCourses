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

import java.util.ArrayList;
import java.util.List;

import org.jlab.groot.data.H2F;
import org.jlab.groot.ui.TCanvas;

public class App {
	public static void main(String[] args) {
		H2F bindingEnergyHistogram = new H2F("Binding Energy", 20, 0, 20, 8, 0, 8);

		List<PeriodicTableElement> aElements = new ArrayList<>();
		aElements.add(new Hydrogen());
		aElements.add(new Helium());
		aElements.add(new Lithium());
		aElements.add(new Beryllium());
		aElements.add(new Boron());
		aElements.add(new Carbon());
		aElements.add(new Nitrogen());

		for (PeriodicTableElement periodicTableElement : aElements) {
			for (int i = periodicTableElement.getMinA(); i <= periodicTableElement.getMaxA(); i++) {
				periodicTableElement.setN(i - periodicTableElement.getZ());

				bindingEnergyHistogram.setBinContent(periodicTableElement.getN(), periodicTableElement.getZ(),
						periodicTableElement.getBindingEnergy());

			}
		}
		TCanvas can1 = new TCanvas("", 800, 800);
		can1.draw(bindingEnergyHistogram);

	}

}
