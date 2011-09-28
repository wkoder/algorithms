import java.io.*;
import java.util.*;

public class ArithmeticExpressions_WCoder {
	public static void main(String[] args) throws IOException {
		//Scanner scan = new Scanner(new File("arithmeticexpressions.in"));
		//PrintWriter out = new PrintWriter("arithmeticexpressions.out");
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
		int T = scan.nextInt();
		while (T-- > 0) {
			String e = scan.next();
			StringTokenizer tok = new StringTokenizer(e, "-+");
			int a = Integer.parseInt(tok.nextToken());
			int b = Integer.parseInt(tok.nextToken());
			if (e.contains("+"))
				out.println(a + b);
			else
				out.println(a - b);
		}
		//out.flush();
		//out.close();
	}
}
