import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Template {
	
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
		solve(); out.flush();
	}
	static Scanner scan; static PrintWriter out;
}
