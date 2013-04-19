package year2013.round1;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class DeadPixels {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	
	private static void hack() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int W = scan.nextInt();
			int H = scan.nextInt();
			int P = scan.nextInt();
			int Q = scan.nextInt();
			int N = scan.nextInt();
			int X = scan.nextInt();
			int Y = scan.nextInt();
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			int d = scan.nextInt();
			List<Integer>[] buckets = new List[W];
			for (int i = 0; i < W; i++)
				buckets[i] = new ArrayList<Integer>();
			buckets[X].add(Y);
			for (int i = 1; i < N; i++) {
				int xx = (X * a + Y * b + 1) % W;
				int yy = (X * c + Y * d + 1) % H;
				X = xx;
				Y = yy;
				
				buckets[X].add(Y);
			}

			int[] iny = new int[H];
			for (int x = 0; x < P-1; x++)
				for (int y : buckets[x])
					iny[y]++;
			long ways = 0;
			for (int x = 0; x < W-P+1; x++) {
				for (int y : buckets[x + P - 1])
					iny[y]++;
				
				int lasty = -1, y;
				for (y = 0; y <= H; y++)
					if (y < H && iny[y] == 0)
						continue;
					else {
						if (y - lasty - 1 >= Q)
							ways += y - lasty - Q; 
						lasty = y;
					}
				
				for (int yy : buckets[x])
					iny[yy]--;
			}
			out.println(ways);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "dead_pixels";
		in = new BufferedReader( new FileReader(file + ".txt") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}