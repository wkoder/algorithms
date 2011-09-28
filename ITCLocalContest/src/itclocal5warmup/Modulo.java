package itclocal5warmup;
import java.util.*;

public class Modulo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while (T-- > 0) {
			Set<Integer> all = new HashSet<Integer>();
			for (int i = 0; i < 10; i++)
				all.add(scan.nextInt() % 42);
			System.out.println(all.size());
		}
	}
	
}
