package itclocal5;
import java.util.*;

public class ElWero_WCoder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		final int MAX = 100;
		
		int T = scan.nextInt();
		String[] words = new String[MAX];
		int[] likes = new int[MAX];
		while (T-- > 0) {
			int L = scan.nextInt();
			for (int i = 0; i < L; i++) {
				words[i] = scan.next().toLowerCase();
				likes[i] = scan.nextInt();
			}
			
			String best = null;
			int bestLike = -1;
			int S = scan.nextInt();
			while (S-- > 0) {
				String sug = scan.next();
				String sugLow = sug.toLowerCase();
				int l = 0;
				for (int i = 0; i < L; i++)
					if (sugLow.contains(words[i]))
						l += likes[i];
				if (l > bestLike) {
					best = sug;
					bestLike = l;
				}
			}
			System.out.println(best);
		}
	}
}
