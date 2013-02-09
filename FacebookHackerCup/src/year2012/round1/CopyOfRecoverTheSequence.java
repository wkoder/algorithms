package year2012.round1;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class CopyOfRecoverTheSequence {
	
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
	    
	    List<Integer>[] result = merge(arr, mid, n-mid);
	    List<Integer> second_half = merge_sort(result[1]);
	    List<Integer> first_half = merge_sort(result[0]);
		first_half.addAll(second_half);
		return first_half;
	}

	static List<Integer>[] merge(List<Integer> result, int s1, int s2) {
		List<Integer> arr1 = new ArrayList<Integer>();
		List<Integer> arr2 = new ArrayList<Integer>();
		List<Integer> D2 = new ArrayList<Integer>();
		for (int s11 = s1, s22 = s2; s11 > 0 && s22 > 0; ) {
			int oper = D.get(D.size()-1);
			D.remove(D.size()-1);
			D2.add(0, oper);
			if (oper == 1)
				s11--;
			else
				s22--;
		}
		while (s1 > 0 && s2 > 0) {
			int k = result.get(0);
	        if (D2.get(0) == 1) {
	            arr1.add(k);
	            s1--;
	        } else {
	        	arr2.add(k);
	        	s2--;
	        }
	        D2.remove(0);
	        result.remove(0);
	    }
		
		if (s1 > 0)
			arr1.addAll(result);
		else
			arr2.addAll(result);
		
	    return new List[]{arr1, arr2};
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
			for (int i = 1; i <= N; i++)
				arr.add(i);
			List<Integer> orig = merge_sort(arr);
			System.out.println(orig);
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
