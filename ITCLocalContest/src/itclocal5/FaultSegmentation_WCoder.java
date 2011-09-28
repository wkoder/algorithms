package itclocal5;
import java.util.Scanner;

public class FaultSegmentation_WCoder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		final int MAX = 1000;
		
		// If you mix scan.nextLine with other scan.nextX you'll have mistakes. Try to avoid it!
		int T = Integer.parseInt(scan.nextLine());
		String[] entries = new String[MAX];
		while (T-- > 0) {
			int E = Integer.parseInt(scan.nextLine());
			for (int i = 0; i < E; i++)
				entries[i] = scan.nextLine();
			
			int Q = Integer.parseInt(scan.nextLine());
			while (Q-- > 0) {
				String query = scan.nextLine();
				int count = 0;
				for (int i = 0; i < E; i++)
					if (entries[i].startsWith(query)) // If it's prefix.
						count++;
				System.out.println(count);
			}
		}
	}
	
}
