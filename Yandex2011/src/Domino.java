import java.util.*;

public class Domino {
	
	private static String toChar(int t) {
		return (char)('a' + t) + "";
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		String[] ans = {"", "", "", ""};
		int ch = 0;
		ans[2] += toChar(ch);
		ans[3] += toChar(ch);
		ch = (ch + 1) % 26;
		for (int i = 0; i < N/2; i++) {
			ans[0] += toChar(ch) + toChar(ch);
			ch = (ch + 1) % 26;
			ans[1] += toChar(ch) + toChar(ch);
			ch = (ch + 1) % 26;
			if (N%2 == 0 && i == N/2-1) {
				ans[2] += toChar(ch);
				ans[3] += toChar(ch);
				ch = (ch + 1) % 26;
			} else {
				ans[2] += toChar(ch) + toChar(ch);
				ch = (ch + 1) % 26;
				ans[3] += toChar(ch) + toChar(ch);
				ch = (ch + 1) % 26;
			}
		}
		
		if (N % 2 == 1) {
			ans[0] += toChar(ch);
			ans[1] += toChar(ch);
			ch = (ch + 1) % 26;
		}
		
		for (String a : ans)
			System.out.println(a);
	}
}
