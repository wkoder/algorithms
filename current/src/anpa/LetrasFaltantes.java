package anpa;
import java.util.*;

public class LetrasFaltantes {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringTokenizer tok = new StringTokenizer(scan.nextLine());
		int n = Integer.parseInt(tok.nextToken());
		int m = Integer.parseInt(tok.nextToken());
		char[] a = scan.nextLine().toCharArray();
		char[] b = scan.nextLine().toCharArray();
		int x = 0;
		int y = 0;
		String ans = "";
		while (x < n && y < m) {
			if (a[x] == b[y])
				x++;
			else
				ans += b[y];
			y++;
		}
		if (x < n)
			ans = "no entiendo";
		else
			while (y < m)
				ans += b[y++];
		System.out.println(ans);
	}
	
}
