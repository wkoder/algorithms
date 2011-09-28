package topcoder;
public class LotteryCheating {
	public int minimalChange(String ID) {
		int min = ID.length();
		long max = Math.round(Math.pow(10, ID.length()));
		for (long x = 0; x*x < max; x++) {
			String s = x*x + "";
			while (s.length() < ID.length())
				s = "0" + s;
			int c = 0;
			for (int i = 0; i < s.length(); i++)
				if (s.charAt(i) != ID.charAt(i))
					c++;
			min = Math.min(min, c);
		}
		return min;
	}
}
