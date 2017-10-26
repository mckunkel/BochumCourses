package fourthclass;

public class SwitchStatements {
	public static void main(String[] args) {
		int month = 3;
		switch (month) {
		case 1:
			System.out.println("The 1st month is January");
			break;
		case 2:
			System.out.println("The 2nd month is February");
			break;
		case 3:
			System.out.println("The 3rd month is March");
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
			System.out.println("It is not January, February or March");
			break;
		default:
			System.out.println("This is undefined. There are only 12 months in the Earth year");
			break;
		}
	}

}
