package topcoder;
import java.util.*;

public class RobotSimulation {
	int MAX = 100;
	int ZERO = 50;
	int TIMES = 1000;
	String D = "LURD";
	int[] dx = {0, 1, 0, -1};
	int[] dy = {-1, 0, 1, 0};
	int x, y, N;
	char[] p;
	Set<Pair> set = new HashSet<Pair>();

	int f() {
		int diff = 0;
		Pair pair = new Pair(x, y);
		if (set.add(pair))
			diff++;
		for (int i = 0; i < p.length; i++) {
			int d = D.indexOf(p[i]);
			x += dx[d];
			y += dy[d];
			pair = new Pair(x, y);
			if (set.add(pair))
				diff++;
		}
		return diff;
	}
	
	public int cellsVisited(String program, int times) {
		p = program.toCharArray();
		
		int[] diff = new int[TIMES];
		for (int i = 0; i < TIMES; i++)
			diff[i] = f();
		int v = 0;
		for (int i = 0; i < TIMES && i < times; i++)
			v += diff[i];
		if (times > TIMES)
			v += diff[TIMES-1] * (times - TIMES);
		return v;
	}
	
	private class Pair {
		int a, b;
		public Pair(int aa, int bb) {
			a = aa;
			b = bb;
		}
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return p.a == a && b == p.b;
		}
		public int hashCode() {
			return a * 10000 + b;
		}
	}
}
