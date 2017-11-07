package fifthclass;

public class TwoClassesNotNested {
	public static void main(String[] args) {
		HalloCANU hi = new HalloCANU("you");
		System.out.println(hi.SayHalloCANU());
		// OR
		System.out.println(new HalloCANU("MK").SayHalloCANU());
	}
}

class HalloCANU {
	String str;

	public HalloCANU(String str) {
		this.str = str;
	}

	public String SayHalloCANU() {
		return "CANU says \"Hallo\" to you " + this.str;
	}
}