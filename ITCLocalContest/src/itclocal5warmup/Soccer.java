package itclocal5warmup;
import java.util.*;

public class Soccer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while (T-- > 0) {
			String s = scan.next();
			int best = -1;
			int best2 = -1;
			int worst = -1;
			int worst2 = -1;
			int win = 0;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == 'W')
					win++;
				int a = win;
				int b = i + 1;
				if (best == -1 || a*best2 >= best*b) { // a/b >= best/best2
					best = a;
					best2 = b;
				}
				if (worst == -1 || a*worst2 <= worst*b) { // a/b <= worst/worst2
					worst = a;
					worst2 = b;
				}
			}
			System.out.println(best2 + " " + worst2);
		}
	}
	
}
