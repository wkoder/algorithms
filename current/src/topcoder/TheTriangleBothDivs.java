package topcoder;
import java.text.DecimalFormat;
import java.util.StringTokenizer;


public class TheTriangleBothDivs {
	DecimalFormat f = new DecimalFormat("00");
	
	String goTry(char[] t, int p, int from, int to) {
		String best = null;
		for (int x = from; x <= to; x++) {
			t[p] = (char)(x + '0');
			String time = go(t, p+1);
			if (best == null || best.compareTo(time) > 0)
				best = time;
		}
		t[p] = '?';
		return best;
	}
	
	String go(char[] t, int p) {
		if (p == t.length) {
			StringTokenizer tok = new StringTokenizer(new String(t), " :GMT+");
			int h = Integer.parseInt(tok.nextToken());
			int m = Integer.parseInt(tok.nextToken());
			int o = Integer.parseInt(tok.nextToken());
			h = ((h - o) + 24) % 24;
			return f.format(h) + ":" + f.format(m);
		}
		
		if (t[p] != '?')
			return go(t, p+1);
		
		if (p == t.length-1)
			if (t[p-1] == '+')
				return goTry(t, p, 0, 9);
			else
				return goTry(t, p, 1, 9);
		
		if (p == t.length-2) {
			t[p] = '+';
			String plus = go(t, p+1);
			t[p] = '-';
			String minus = go(t, p+1);
			t[p] = '?';
			return plus.compareTo(minus) < 0? plus : minus;
		}
		
		if (p == 4)
			return goTry(t, p, 0, 9);
		
		if (p == 3)
			return goTry(t, p, 0, 5);
		
		if (p == 1)
			if (t[0] == '2')
				return goTry(t, p, 0, 3);
			else
				return goTry(t, p, 0, 9);
		
		if (t[1] == '?' || t[1] <= '3')
			return goTry(t, p, 0, 2);
		else
			return goTry(t, p, 0, 1);
	}
	public String fix(String time) {
		return go(time.toCharArray(), 0);
	}
}
