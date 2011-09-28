package itclocal5;

import java.util.Scanner;

public class JavierAguirre_WCoder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String worst = null;
		int worstValue = 1000;
		for (int i = 0; i < 24; i++) {
			String player = scan.next();
			int value = scan.nextInt() + scan.nextInt();
			if (value < worstValue) {
				worstValue = value;
				worst = player;
			}
		}
		System.out.println(worst);
	}
	
}
