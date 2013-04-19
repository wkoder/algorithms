package solved;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * http://projecteuler.net/problem=59
 * 
 * @author Moises Osorio
 *
 */
public class P59_XORDecryption {
	
	static final int MAX = 1201;

	public static void solve() throws Exception {
		byte[] data = new byte[MAX];
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("cipher1.txt")));
		StringTokenizer tok = new StringTokenizer(reader.readLine(), ",");
		reader.close();
		int len = 0;
		while (tok.hasMoreTokens())
			data[len++] = Byte.parseByte(tok.nextToken());
		
		int[] best = new int[3];
		int max = 0;
		int[] xor = {'a', 'a', 'a'-1};
		while (true) {
			if (++xor[2] > 'z') {
				xor[2] = 'a';
				if (++xor[1] > 'z') {
					xor[1] = 'a';
					if (++xor[0] > 'z')
						break;
				}
			}
			
			int chars = 0;
			for (int i = 0; i < len; i++) {
				int ch = data[i] ^ xor[i % 3];
				if (Character.isLetter(ch))
					chars++;
			}
			
			if (chars > max) {
				max = chars;
				for (int i = 0; i < best.length; i++)
					best[i] = xor[i];
			}
		}
		
		int sum = 0;
		for (int i = 0; i < len; i++) {
			int ch = data[i] ^ best[i % 3];
			sum += ch;
//			System.out.print((char) ch);
		}
//		System.out.println();
		System.out.println(sum);
	}

	public static void main(String[] args) throws Exception {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
