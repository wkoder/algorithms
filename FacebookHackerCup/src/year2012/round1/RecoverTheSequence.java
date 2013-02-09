package year2012.round1;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class RecoverTheSequence {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	static List<Integer> D = new ArrayList<Integer>();
	
	static int checksum(List<Integer> arr) {
	    int result = 1;
	    for (int i = 0; i < arr.size(); i++)
	        result = (31 * result + arr.get(i)) % 1000003;
	    return result;
	}
	
	static List<Integer> merge_sort(List<Integer> arr) {
	    int n = arr.size();
	    if (n <= 1)
	        return arr;

	    // arr is indexed 0 through n-1, inclusive
	    int mid = n / 2;
	    
	    List<Integer> first = merge_sort(new ArrayList<Integer>(arr.subList(0, mid)));
	    List<Integer> second = merge_sort(new ArrayList<Integer>(arr.subList(mid, n)));
	    return merge(first, second);
	}

	static List<Integer> merge(List<Integer> arr1, List<Integer> arr2) {
		List<Integer> result = new ArrayList<Integer>();
		while (arr1.size() > 0 && arr2.size() > 0) {
			if (D.remove(0) == 1)
				result.add(arr1.remove(0));
			else
				result.add(arr2.remove(0));
		}
		
		result.addAll(arr1);
		result.addAll(arr2);
		return result;
	}
	
	private static void hack() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = scan.nextInt();
			String S = scan.next();
			D.clear();
			for (int i = 0; i < S.length(); i++)
				D.add(S.charAt(i)-'0');
			List<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < N; i++)
				arr.add(i);
			List<Integer> p = merge_sort(arr);
			Integer[] ans = new Integer[N];
			for (int i = 0; i < N; i++)
				ans[p.get(i)] = i + 1;
			List<Integer> orig = Arrays.asList(ans);
//			System.out.println(orig);
			out.println("Case #" + t + ": " + checksum(orig));
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
//		String file = "A-small";
//		in = new BufferedReader( new FileReader(file + ".txt") );
//		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
