package seventhclass.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.Pair;

import sixthclass.polymorphism.Car;
import sixthclass.polymorphism.MotorCycle;
import sixthclass.polymorphism.Truck;
import sixthclass.polymorphism.Vehicle;

public class UsingMaps {
	public static void main(String[] args) {

		Map<Integer, String> aMap = new TreeMap<>();
		aMap.put(3, "Three");
		aMap.put(1, "One");
		aMap.put(2, "Two");
		aMap.put(1, "Four");
		for (Integer key : aMap.keySet()) {
			System.out.println(key);
		}
		for (String value : aMap.values()) {
			System.out.println(value);
		}

		Map<Integer, String> aMap2 = new LinkedHashMap<>();
		aMap2.put(3, "Three");
		aMap2.put(1, "One");
		aMap2.put(2, "Two");
		aMap2.put(1, "Four");

		for (Integer key : aMap2.keySet()) {
			System.out.println(key);
		}
		for (String value : aMap2.values()) {
			System.out.println(value);
		}

		for (Map.Entry<Integer, String> entry : aMap2.entrySet()) {
			Integer key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "  " + value);
		}

		List<Vehicle> aList = new ArrayList<>();
		aList.add(new Car());
		aList.add(new MotorCycle());
		aList.add(new Truck());

		for (Vehicle vehicle : aList) {
			vehicle.engineSound();
		}

		// Create a tree set
		TreeSet<String> aTreeSet = new TreeSet();
		// Add elements to the tree set
		aTreeSet.add("C");
		aTreeSet.add("A");
		aTreeSet.add("B");
		aTreeSet.add("E");
		aTreeSet.add("F");
		aTreeSet.add("D");
		System.out.println(aTreeSet);

		Pair<String, Vehicle> carPair = Pair.of("Mustang", new Car());

		System.out.println(carPair.getLeft() + "  ");
		carPair.getRight().engineSound();

		Map<String, Pair<Double, Double>> aMap3 = new HashMap<>();
		aMap3.put("Big Dipper", Pair.of(1.2, 1.3));
		aMap3.put("Little", Pair.of(1.0, 0.7));
		System.out.println(aMap3.get("Little").getLeft());

		List<Pair<Character, Integer>> hashProgress = Arrays.asList(Pair.of('.', 0), Pair.of('o', 1), Pair.of('8', 2),
				Pair.of('O', 3), Pair.of('@', 4), Pair.of('#', 5));
		System.out.println(hashProgress.get(4).getLeft());

	}

}
