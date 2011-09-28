import java.io.*;
import java.util.StringTokenizer;

public class HistoryChecker_WCoder {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		boolean ok = true;
		int lastYear = -1;
		while ((line = in.readLine()) != null) {
			int year = Integer.parseInt(new StringTokenizer(line).nextToken());
			ok &= year >= lastYear;
			if (year == 2012) {
				System.out.println(ok? "ACCURATE" : "INACCURATE");
				lastYear = -1;
				ok = true;
			} else
				lastYear = year;
		}
	}
}
