import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerTemplate {

  static Scanner in;
  static PrintWriter out;

  static void solve() {
  }

  public static void main(String[] args) throws IOException {
    in = new Scanner(new InputStreamReader(System.in));
    out = new PrintWriter(new BufferedOutputStream(System.out));
    solve();
    out.flush();
  }
}
