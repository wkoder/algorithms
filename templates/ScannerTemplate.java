import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ScannerTemplate implements Runnable {

  private static void solve() {
  }

  /** Template starts: */
  @Override
  public void run() {
    solve();
    out.flush();
    System.exit(0);
  }

  private final static Scanner in = new Scanner(new InputStreamReader(System.in));
  private final static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

  public static void main(String[] args) {
    new Thread(null, new ScannerTemplate(), "Main", 1 << 26).start();
  }
}
