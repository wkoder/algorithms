package topcoder;
import java.util.*;

public class TwoRegisters {
	
	private String f(Pair p) {
		String s = "";
		while (p.type > 0) {
			s = p.type + s;
			p = p.prev;
		}
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(new TwoRegisters().minProg(1000000));
	}
	
	public String minProg(int r) {
		Set<Pair> set = new HashSet<Pair>();
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(1, 1));
		set.add(new Pair(1, 1));
		int max = 0;
		while (true) {
			Pair p = q.poll();
			int xx, yy;
			
			if (q.size() > max) {
				max = q.size();
				System.out.println(max);
			}
			
			xx = p.x + p.y;
			yy = p.y;
			if (xx == r)
				return f(p) + 'X';
			else if (xx + yy <= r) {
				Pair p2 = new Pair(xx, yy);
				if (!set.contains(p2)) {
					p2.prev = p;
					p2.type = 'X';
					set.add(p2);
					q.add(p2);
				}
			}
			
			xx = p.x;
			yy = p.x + p.y;
			if (xx + yy <= r) {
				Pair p2 = new Pair(xx, yy);
				if (!set.contains(p2)) {
					p2.prev = p;
					p2.type = 'Y';
					set.add(p2);
					q.add(p2);
				}
			}
		}
	}
	
	class Pair {
		int x, y;
		Pair prev;
		char type;
		public Pair(int xx, int yy) {
			x = xx;
			y = yy;
		}
		@Override
		public int hashCode() {
			return x*1000000 + y;
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return x == p.x && y == p.y;
		}
	}
}
