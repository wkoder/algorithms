package gcj2011.round1c;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_SpaceEmergency {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int L = scan.nextInt();
			long time = scan.nextLong() * 2;
			int N = scan.nextInt();
			int C = scan.nextInt();
			int[] d = new int[C];
			long dsum = 0;
			for (int i = 0; i < C; i++)
				dsum += d[i] = scan.nextInt() * 2;
			
			long periodStart = time / dsum;
			int periodIndexStart;
			long dsum2 = 0;
			for (periodIndexStart = 0; periodIndexStart < C; periodIndexStart++) {
				dsum2 += d[periodIndexStart];
				if (dsum2 > time % dsum)
					break;
			}
			
			int periodIndexEnd = N % C;
			int periodEnd = N / C;
			
			long ans = periodEnd * dsum;
			for (int i = 0; i < periodIndexEnd; i++)
				ans += d[i];
			
//			System.out.println(periodStart + " " + periodIndexStart + " : " + periodEnd + " " + periodIndexEnd);
			
			if (periodStart < periodEnd || (periodStart == periodEnd && periodIndexStart < periodIndexEnd)) {
				int completePeriods = periodEnd - (int)periodStart - (periodIndexStart == 0? 0 : 1);
				List<S> savings = new ArrayList<S>();
				dsum2 = 0;
				for (int i = 0; i < C; i++) {
					dsum2 += d[i];
					if (completePeriods > 0)
						savings.add(new S(d[i], completePeriods));
					
					if (periodStart != periodEnd) {
						if (i > periodIndexStart)
							savings.add(new S(d[i], 1));
						else if (i == periodIndexStart)
							savings.add(new S((int)(dsum2 - (time % dsum)), 1));
						if (i < periodIndexEnd)
							savings.add(new S(d[i], 1));
					} else {
						if (i == periodIndexStart)
							savings.add(new S((int)(dsum2 - (time % dsum)), 1));
						else if (i > periodIndexStart && i < periodIndexEnd)
							savings.add(new S(d[i], 1));
					}
				}
				
				Collections.sort(savings);
				int i = 0;
//				System.out.println(ans);
//				System.out.println(savings);
				long save = 0;
				while (L > 0 && i < savings.size()) {
					S sav = savings.get(i);
					save += sav.time * Math.min(sav.rep, L) / 2;
					L -= sav.rep;
					i++;
				}
				
				System.out.println(save);
				ans -= save;
			}
			
			out.println(ans);
		}
	}
	
	static class S implements Comparable<S> {
		int time, rep;
		public S(int timee, int repp) {
			time = timee;
			rep = repp;
		}
		public int compareTo(S o) {
			return o.time - time;
		}
		@Override
		public String toString() {
			return time + "*" + rep;
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
//		String file = "B-small";
//		in = new BufferedReader( new FileReader(file + ".in") );
//		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}