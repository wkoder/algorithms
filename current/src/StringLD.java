import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class StringLD {
	
	static final int MAX = 100;
	static String[] words = new String[MAX];
	
	public static void solve() {
		int N;
		while ((N = Integer.parseInt(read())) > 0) {
			int steps =  MAX;
			for (int i = 0; i < N; i++) {
				String s = read();
				words[i] = "";
				for (int j = s.length()-1; j >= 0; j--)
					words[i] += s.charAt(j);
				steps = Math.min(steps, s.length());
			}
			
			Arrays.sort(words, 0, N);
			for (int i = 1; i < N; i++) {
				int j;
				int l = Math.min(words[i].length(), words[i-1].length());
				for (j = 0; j < l; j++)
					if (words[i].charAt(j) != words[i-1].charAt(j))
						break;
				steps = Math.min(steps, Math.max(words[i].length() - j, words[i-1].length() - j));
			}
			out.println(steps-1);
		}
	}
	
	public static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
