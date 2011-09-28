package itclocal5warmup;
import java.util.*;

public class Palindromos {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while (T-- > 0) {
			int N = scan.nextInt();
			char[] p = scan.next().toCharArray();
			int max = 0;
			for (int a = 0; a < N; a++)
				for (int b = a; b < N && b <= a+1; b++) {
					int l = a;
					int r = b;
					while (l >= 0 && r < N && p[l] == p[r]) {
						if (r-l >= max)
							max = r-l+1;
						l--;
						r++;
					}
				}
			System.out.println(max);
		}
	}
	
}
