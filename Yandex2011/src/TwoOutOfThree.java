import java.util.*;

public class TwoOutOfThree {

	static int[][] memo = new int[1000][1000];
	static int[][] chosen = new int[1000][1000];
	static int[] cost = new int[1000];
	static int n;
	
	static int go(int a, int b) {
		if (b >= n)
			return cost[a];
		int c = b+1;
		if (c >= n)
			return Math.max(cost[a], cost[b]);
		if (memo[a][b] >= 0)
			return memo[a][b];
		
		int min = Integer.MAX_VALUE, k;

		k = go(c, c+1) + Math.max(cost[a], cost[b]);
		if (k < min) {
			min = k;
			chosen[a][b] = c;
		}

		k = go(b, c+1) + Math.max(cost[a], cost[c]);
		if (k < min) {
			min = k;
			chosen[a][b] = b;
		}
		
		k = go(a, c+1) + Math.max(cost[b], cost[c]);
		if (k < min) {
			min = k;
			chosen[a][b] = a;
		}
		
		return memo[a][b] = min;
	}
	
	static void print(int a, int b) {
		System.out.println((a+1) + " " + (b+1));
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			cost[i] = scan.nextInt();
			Arrays.fill(memo[i], -1);
		}
		
		System.out.println(go(0, 1));
		int a = 0;
		int b = 1;
		while (true) {
			if (b >= n) {
				System.out.println(a+1);
				break;
			}
			
			int c = b+1;
			if (c >= n) {
				print(a, b);
				break;
			}
			
			int x = chosen[a][b];
			if (x == a) {
				print(b, c);
			} else if (x == b) {
				print(a, c);
				a = b;
			} else if (x == c) {
				print(a, b);
				a = c;
			} else
				System.err.println("SHIT!");
			
			b = c + 1;
		}
	}
	
}
