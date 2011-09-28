import java.io.*;
import java.util.Random;

public class BalancingGen {
	
	private static final Random RAND = new Random();
	
	public static void main(String[] args) throws IOException {
		final int TESTS = 100;
		final int MAXLEN = 100;
		
		PrintWriter out = new PrintWriter("balancing.in");
		out.println(TESTS);
		for (int t = 0; t < TESTS; t++) {
			int len = RAND.nextInt(MAXLEN) + 1;
			String p = create(len);
			int k = RAND.nextInt(len);
			String p2 = p.substring(0, k);
			if (RAND.nextBoolean())
				p2 += "(";
			p2 += p.substring(k+1);
			out.println(p2);
		}
		out.flush();
		out.close();
	}
	
	private static String create(int len) {
		if (len == 0)
			return "";
		if (len == 1)
			return RAND.nextBoolean()? "(" : ")";
		
		String ans = "(";
		len -= 2;
		while (len > 0) {
			if (len == 1) {
				ans += RAND.nextBoolean()? "(" : ")";
				break;
			}
			
			int len2 = RAND.nextInt(len/2 + 1) * 2;
			len -= len2;
			ans += create(len2);
		}
		return ans + ")";
	}
}
