import java.io.*;
import java.util.Scanner;

public class TuentiTemplate {
  private static void solve() {
    int T = in.nextInt();
    for (int t = 1; t <= T; t++) {
      out.print("Case #" + t + ": ");
    }
  }

  private static Scanner in;
  private static PrintWriter out;

  private static void setUpIO(String challengeName) throws IOException {
    BufferedReader bufferedReader;
    if (challengeName == null) {
      bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(new BufferedOutputStream(System.out));
    } else {
      bufferedReader = new BufferedReader(new FileReader(challengeName + ".txt"));
      out = new PrintWriter(new FileOutputStream(challengeName + "_" + System.currentTimeMillis() + ".out"));
    }
    in = new Scanner(bufferedReader);
  }

  public static void main(String[] args) throws IOException {
    setUpIO(null);
    solve();
    out.flush();
  }
}
