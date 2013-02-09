package topcoder.unknown;
public class EqualizeStrings {
	public String getEq(String s, String t) {
		String x = "";
		for (int i = 0; i < s.length(); i++) {
			int a = Math.min(s.charAt(i) - 'a', t.charAt(i) - 'a');
			int b = Math.max(s.charAt(i) - 'a', t.charAt(i) - 'a');
			if (b-a < 13)
				x += (char)(a + 'a');
			else
				x += 'a';
		}
		return x;
	}
}
