import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FacebookHackerCupTemplate {

  static final int INF = 1 << 28, MAX = 1000;
  static final double EPS = 1E-9;
  static Scanner scan;
  static PrintWriter out;

  public static void solve() {
    int cases = scan.nextInt();
    for (int caze = 1; caze <= cases; caze++) {
      out.print("Case #" + caze + ": ");

    }
  }

  public static void main(String[] args) throws IOException {
    scan = new Scanner(new InputStreamReader(System.in));
    //scan = new Scanner(new File(".txt"));
    out = new PrintWriter(new BufferedOutputStream(System.out));
    //out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(".out")));
    solve();
    out.flush();
  }
}
