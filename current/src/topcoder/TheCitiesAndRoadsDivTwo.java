package topcoder;
public class TheCitiesAndRoadsDivTwo {
	int size(int x, String[] map, boolean[] v) {
		if (v[x])
			return 0;
		v[x] = true;
		int s = 1;
		for (int i = 0; i < map.length; i++)
			if (map[x].charAt(i) == 'Y')
				s += size(i, map, v);
		return s;
	}
	public int find(String[] map) {
		int k = map.length;
		boolean[] v = new boolean[k];
		int res = 1;
		int n = 0;
		for (int i = 0; i < k; i++)
			if (! v[i]) {
				n++;
				res *= size(i, map, v);
			}
		if (n == 1)
			return 1;
		for (int i = 0; i < n-2; i++)
			res *= map.length;
		return res;
	}
}
