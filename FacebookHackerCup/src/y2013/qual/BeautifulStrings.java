package y2013.qual;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class BeautifulStrings {
	
	public static void solve() {
		int cases = scan.nextInt();
		scan.nextLine();
		for (int caze = 1; caze <= cases; caze++) {
			out.print("Case #" + caze + ": ");
			int[] count = new int[26];
			String line = scan.nextLine();
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if (Character.isLetter(ch))
					count[Character.toLowerCase(ch) - 'a']++;
			}
			Arrays.sort(count);
			int beauty = 0;
			for (int i = 0; i < count.length; i++)
				beauty += (i + 1) * count[i];
			out.println(beauty);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//scan = new Scanner(new InputStreamReader(System.in));
		scan = new Scanner(new File("beautiful_strings.txt"));
		//out = new PrintWriter(new BufferedOutputStream(System.out));
		out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("beautiful_strings.out")));
		solve(); out.flush();
	}
	static Scanner scan; static PrintWriter out;
}
