import java.io.*;
import java.util.Scanner;

public class Balancing_WCoder {
	public static void main(String[] args) throws IOException {
		//Scanner scan = new Scanner(new File("balancing.in"));
		//PrintWriter out = new PrintWriter("balancing.out");
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
		int T = scan.nextInt();
		while (T-- > 0) {
			String p = scan.next();
			int b = 0;
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) == '(')
					b++;
				else
					b--;
				if (b < 0)
					break;
			}
			out.println(b == 0? "yes" : "no");
		}
		//out.flush();
		//out.close();
	}
}
