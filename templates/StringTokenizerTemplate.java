import java.io.*;
import java.util.StringTokenizer;

public class StringTokenizerTemplate {

  private static void solve() {
  }

  /** Template starts: */
  private final static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer stringTokenizer;
  private final static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

  public static void main(String[] args) {
      solve();
      out.flush();
  }

  private static long readLong() {
    return Long.parseLong(read());
  }

  private static int readInt() {
    return Integer.parseInt(read());
  }

  private static String read() {
    while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
      String line = readLine();
      if (line == null) {
        return null;
      }
      stringTokenizer = new StringTokenizer(line);
    }
    return stringTokenizer.nextToken();
  }

  private static String readLine() {
    try {
      return in.readLine();
    } catch (IOException e) {
      return null;
    }
  }
}
