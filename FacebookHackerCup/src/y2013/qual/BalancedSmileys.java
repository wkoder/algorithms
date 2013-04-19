package y2013.qual;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class BalancedSmileys {
	
	private static final int MAX = 101;
	private static int[][] memo = new int[MAX][MAX];
	private static char[] str;
	
	private static boolean go(int a, int b) {
		if (memo[a][b] >= 0)
			return memo[a][b] == 1;
		
		if (a == b)
			return true;
		if (b - a == 1)
			return str[a] == ' ' || str[a] == ':' || Character.isLetter(str[a]);

		boolean ok = false;
		if (b - a == 2)
			ok |= str[a] == ':' && (str[a+1] == ')' || str[a+1] == '(');
		if (b -a >= 2 && str[a] == '(' && str[b-1] == ')')
			ok |= go(a+1, b-1);
		for (int x = a+1; x < b && !ok; x++)
			ok |= go(a, x) && go(x, b);
		
		memo[a][b] = ok ? 1 : 0;
		return ok;
	}
	
	public static void solve() {
		int cases = scan.nextInt();
		scan.nextLine();
		for (int caze = 1; caze <= cases; caze++) {
			out.print("Case #" + caze + ": ");
			for (int i = 0; i < MAX; i++)
				Arrays.fill(memo[i], -1);
			str = scan.nextLine().toCharArray();
			out.println(go(0, str.length) ? "YES" : "NO");
		}
	}
	
	public static void main(String[] args) throws IOException {
//		scan = new Scanner(new InputStreamReader(System.in));
		scan = new Scanner(new File("balanced_smileys.txt"));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("balanced_smileys.out")));
		solve(); out.flush();
	}
	static Scanner scan; static PrintWriter out;
}
