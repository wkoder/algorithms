import java.io.*;
import java.util.Scanner;

public class GoogleCodeJamTemplate {

  static final int INF = 1 << 28, MAX = 1000;
  static final double EPS = 1E-9;
  static BufferedReader in;
  static PrintWriter out;

  static void solve() {
    Scanner scan = new Scanner(in);
    int T = scan.nextInt();
    for (int t = 1; t <= T; t++) {
      out.print("Case #" + t + ": ");

    }
  }

  static String read() {
    try {
      return in.readLine();
    } catch (IOException e) {
      return null;
    }
  }

  public static void main(String[] args) throws IOException {
    in = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new BufferedOutputStream(System.out));
//		String file = "A-small";
//		in = new BufferedReader( new FileReader(file + ".in") );
//		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
    solve();
    out.flush();
  }
}
