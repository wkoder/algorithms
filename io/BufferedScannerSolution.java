import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class BufferedScannerSolution implements Runnable {

  private static void solve() {
    long sum = 0;
    int n = in.nextInt();
    while (n-- > 0) {
      sum += in.nextInt();
    }
    out.println(sum);
  }

  /** Template starts: */
  @Override
  public void run() {
    solve();
    out.flush();
    System.exit(0);
  }

  private final static Scanner in = new Scanner(new InputStreamReader(new BufferedInputStream(System.in)));
  private final static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

  public static void main(String[] args) {
    new Thread(null, new BufferedScannerSolution(), "Main", 1 << 26).start();
  }
}
