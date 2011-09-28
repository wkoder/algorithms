package quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_BotTrust {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int orange = 1;
			int orangeTime = 0;
			int blue = 1;
			int blueTime = 0;
			int n = scan.nextInt();
			while (n-- > 0) {
				char color = scan.next().charAt(0);
				String b = scan.next().replace("\0", "");
				int button = Integer.parseInt(b);
				if (color == 'O') {
					orangeTime += Math.abs(button - orange);
					orange = button;
					orangeTime = Math.max(orangeTime, blueTime);
					orangeTime++;
				} else {
					blueTime += Math.abs(button - blue);
					blue = button;
					blueTime = Math.max(orangeTime, blueTime);
					blueTime++;
				}
			}
			
			out.println(Math.max(orangeTime, blueTime));
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "A-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
