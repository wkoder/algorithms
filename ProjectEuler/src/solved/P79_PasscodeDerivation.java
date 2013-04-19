package solved;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


/**
 * http://projecteuler.net/problem=79
 * 
 * @author Moises Osorio
 *
 */
public class P79_PasscodeDerivation {
	
	static final int MAX = 50;

	public static void solve() throws Exception {
		final int N = 10;
		int[][] graph = new int[N][N];
		int[] inDegree = new int[N];
		int[] outDegree = new int[N];
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("keylog.txt")));
		for (int i = 0; i < MAX; i++) {
			String code = reader.readLine();
			for (int j = 0; j < code.length() - 1; j++) {
				int a = code.charAt(j) - '0';
				int b = code.charAt(j + 1) - '0';
				graph[a][b]++;
				outDegree[a]++;
				inDegree[b]++;
			}
		}
		reader.close();
		
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < N; i++)
			if (outDegree[i] > 0 && inDegree[i] == 0)
				q.add(i);
		String pass = "";
		while (!q.isEmpty()) {
			int x = q.poll();
			pass += x;
			for (int i = 0; i < N; i++)
				if (graph[x][i] > 0)
					if ((inDegree[i] -= graph[x][i]) == 0)
						q.add(i);
		}
		System.out.println(pass);
	}

	public static void main(String[] args) throws Exception {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
