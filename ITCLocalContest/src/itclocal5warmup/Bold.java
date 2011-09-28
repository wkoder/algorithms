package itclocal5warmup;
import java.util.*;

public class Bold {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while (T-- > 0) {
			scan.nextInt();
			String s = scan.next();
			boolean b = s.contains("B");
			boolean n = s.contains("N");
			if (b)
				if (n)
					System.out.println("Some bold");
				else
					System.out.println("Bold");
			else
				System.out.println("Non bold");
		}
	}
	
}
