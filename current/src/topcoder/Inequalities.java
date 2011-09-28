package topcoder;
import java.util.StringTokenizer;

public class Inequalities {
	public int maximumSubset(String[] inequalities) {
		double EPS = 1e-9;
		int N = inequalities.length;
		String[] op = new String[N];
		int[] val = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer tok = new StringTokenizer(inequalities[i]);
			tok.nextToken();
			op[i] = tok.nextToken();
			val[i] = Integer.parseInt(tok.nextToken());
		}
		
		int max = 0;
		for (int ix = 0; ix <= 2004; ix++) {
			double x = ix / 2.0 - 1;
			int m = 0;
			for (int i = 0; i < N; i++) {
				if (op[i].equals("<"))
					m += x < val[i]-EPS? 1 : 0;
				else if (op[i].equals("<="))
					m += x < val[i]+EPS? 1 : 0;
				else if (op[i].equals("="))
					m += Math.abs(x - val[i]) < EPS? 1 : 0;
				else if (op[i].equals(">="))
					m += x > val[i]-EPS? 1 : 0;
				else if (op[i].equals(">"))
					m += x > val[i]+EPS? 1 : 0;
			}
			max = Math.max(max, m);
		}
		return max;
	}
}
