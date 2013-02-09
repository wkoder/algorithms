import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class FractalDetector {
	
	private static boolean check(int i, int j, int offset, int[][] fractal, int mask) {
		if ((mask & (1 << offset)) != 0 && fractal[i][j] != mask)
			return false;
		if ((mask & (1 << offset)) == 0 && fractal[i][j] != 0)
			return false;
		return true;
	}
	
	public static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
		int R = Integer.parseInt(tok.nextToken());
		int C = Integer.parseInt(tok.nextToken());
		int[][] fractal = new int[R][C];
		for (int i = 0; i < R; i++) {
			char[] s = read().toCharArray();
			for (int j = 0; j < C; j++) {
				fractal[i][j] = s[j] == '*' ? 0 : 1;
			}
		}
		
		int[][] temp;
		int[][] nextFractal = new int[R][C];
		int count = 0;
		for (int size = 1; size <= Math.min(R, C); size *= 2) {
//			for (int i = 0; i < R-size+1; i++) {
//				for (int j = 0; j < C-size+1; j++) {
//					System.out.print(fractal[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			for (int i = 0; i < R-size; i++)
				for (int j = 0; j < C-size; j++) {
					if (size == 1) {
						nextFractal[i][j] = (fractal[i][j] << 3) | (fractal[i][j+1] << 2) | (fractal[i+1][j] << 1) | fractal[i+1][j+1];
					} else {
						if (fractal[i][j] == -1 || fractal[i+size][j] == -1 || fractal[i][j+size] == -1 || fractal[i+size][j+size] == -1)
							continue;
//						System.out.println(i + ", " + j);
						int mask = fractal[i][j] | fractal[i+size][j] | fractal[i][j+size] | fractal[i+size][j+size];
						if (!check(i, j, 3, fractal, mask)) {
							nextFractal[i][j] = -1;
							continue;
						}
						if (!check(i, j+size, 2, fractal, mask)) {
							nextFractal[i][j] = -1;
							continue;
						}
						if (!check(i+size, j, 1, fractal, mask)) {
							nextFractal[i][j] = -1;
							continue;
						}
						if (!check(i+size, j+size, 0, fractal, mask)) {
							nextFractal[i][j] = -1;
							continue;
						}
						nextFractal[i][j] = mask;
						
						if (size == 2)
							count++;
						else
							count -= 3;
					}
				}
//			System.out.println(size + " " + count);
			temp = nextFractal;
			nextFractal = fractal;
			fractal = temp;
		}
		out.println(count);
	}
	
	public static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
