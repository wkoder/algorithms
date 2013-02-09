package topcoder.unknown;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheSoccerDivTwo {
	public int find(int[] points) {
		int best = points.length;
		for (int k = 1; k < points.length; k++) {
			Team ZERO = new Team(0, points[0]+3);
			Team LOSER = new Team(k, points[k]);
			List<Team> t = new ArrayList<Team>();
			for (int i = 1; i < points.length; i++)
				if (i != k)
					t.add(new Team(i, points[i]));
			Collections.sort(t);

			int l = -1;
			int w = t.size();
			while (l < w) {
				List<Team> t2 = new ArrayList<Team>();
				for (int i = 0; i <= l; i++)
					t2.add(t.get(i));
				for (int i = w; i < t.size(); i++)
					t2.add(new Team(t.get(i).i, t.get(i).p + 3));
				for (int i = l+1; i < w; i++)
					t2.add(new Team(t.get(i).i, t.get(i).p + 1));
				t2.add(ZERO);
				t2.add(LOSER);
				
				Collections.sort(t2);
				for (int i = 0; i < t2.size(); i++)
					if (t2.get(i).i == 0) {
						best = Math.min(best, i+1);
						break;
					}
				l++;
				w--;
			}
		}
		return best;
	}
	class Team implements Comparable<Team> {
		int i, p;
		public Team(int ii, int pp) {
			i = ii;
			p = pp;
		}
		public int compareTo(Team t) {
			if (p != t.p)
				return t.p - p;
			return i - t.i;
		}
	}
}
