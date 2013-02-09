package gcj2010.round2b;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_SafetyInNumbers {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static double get2Min(double[] p) {
		double min = Double.MAX_VALUE;
		double min2 = Double.MAX_VALUE;
		
		for (int i = 0; i < p.length; i++)
			if (p[i] < min-EPS) {
				min2 = min;
				min = p[i];
			} else if (p[i] > min+EPS && p[i] < min2-EPS)
				min2 = p[i];
		
		return min2 < Double.MAX_VALUE - EPS ? min2 : -1;
	}
	
	static Integer[] getMins(double[] p) {
		double min = Double.MAX_VALUE;
		for (double pp : p)
			min = Math.min(min, pp);
		
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < p.length; i++)
			if (Math.abs(min - p[i]) < EPS)
				list.add(i);
		
		return list.toArray(new Integer[0]);
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ":");
			int N = scan.nextInt();
			int[] k = new int[N];
			int X = 0;
			for (int i = 0; i < N; i++)
				X += k[i] = scan.nextInt();
			
			double[] p = new double[N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					p[j] = k[j];
				
				double xxx = X;
				while (xxx > EPS) {
					double min2 = get2Min(p);
					if (min2 < 0) {
						for (int j = 0; j < N; j++)
							p[j] += xxx / N;
						break;
					}
					
					Integer[] mins = getMins(p);
					double delta = Math.min(min2 - p[mins[0]], xxx / mins.length);
					xxx -= delta * mins.length;
					for (int j : mins)
						p[j] += delta;
				}
				
				out.print(" " + ((p[i]-k[i]) * 100 / X));
			}
			out.println();
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "A-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
