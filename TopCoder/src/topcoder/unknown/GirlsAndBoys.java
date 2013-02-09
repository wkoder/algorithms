package topcoder.unknown;
public class GirlsAndBoys {
	int f(String s) {
		int x = 0;
		int p = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'G')
				continue;
			x += i - p;
			p++;
		}
		return x;
	}
	public int sortThem(String row) {
		String row2 = "";
		for (int i = 0; i < row.length(); i++)
			row2 = row.charAt(i) + row2;
		return Math.min(f(row), f(row2));
	}
}
