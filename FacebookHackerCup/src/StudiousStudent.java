import java.util.*;

public class StudiousStudent {
	
	static int MAX = 10;
	static int M;
	static boolean[] used = new boolean[MAX];
	static String[] list = new String[MAX];
	static String best;
	
	static void go(int p, String cur) {
		if (best != null && cur.compareTo(best) > 0)
			return;
		
		if (p == M) {
			best = cur;
			return;
		}
		
		for (int i = 0; i < M; i++)
			if (!used[i]) {
				used[i] = true;
				go(p+1, cur + list[i]);
				used[i] = false;
			}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		while (N-- > 0) {
			M = scan.nextInt();
			best = null;
			for (int i = 0; i < M; i++)
				list[i] = scan.next();
			go(0, "");
			System.out.println(best);
		}
	}
}
