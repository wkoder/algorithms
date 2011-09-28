package itclocal5;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BuyingAFullHDTV_Generator {

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter("buyingafullhdtv.in");
		final int TESTS = 1000;
		final double EPS = 1e-9;
		final int MAX_COORD = 50;
		final int MAX_P = 100;
		
		Random r = new Random();
		for (int test = 0; test <= TESTS; test++) {
			int x1 = r.nextInt(MAX_COORD / 2) - MAX_COORD;
			int y1 = r.nextInt(MAX_COORD / 2) - MAX_COORD;
			int dx = r.nextInt(MAX_COORD / 4) + 1;
			int dy = r.nextInt(MAX_COORD / 4) + 1;
			if (r.nextBoolean())
				dx = -dx;
			if (r.nextBoolean())
				dy = -dy;
			int x2 = x1 + dx*2;
			int y2 = y1 + dy*2;
			int y3, x3;
			int d = r.nextInt(MAX_COORD / 2 / Math.max(Math.abs(dx), Math.abs(dy))) + 1;
			d = 2;
			if (r.nextBoolean()) {
				x3 = x1 + dx - dy*d;
				y3 = y1 + dy + dx*d;
			} else {
				x3 = x1 + dx + dy*d;
				y3 = y1 + dy - dx*d;
			}
			if (Math.abs(Math.hypot(x3-x1, y3-y1) - Math.hypot(x3-x2, y3-y2)) > EPS)
				System.err.println("ERROR1: " + test);
			int[][] coord = {{x1, y1}, {x2, y2}, {x3, y3}};
			for (int i = 0; i < 3; i++) {
				int a = r.nextInt(3);
				int b = r.nextInt(3);
				while (b == a)
					b = r.nextInt(3);
				int[] tmp = coord[a];
				coord[a] = coord[b];
				coord[b] = tmp;
			}
			double a = Math.hypot(coord[0][0] - coord[1][0], coord[0][1] - coord[1][1]);
			double b = Math.hypot(coord[0][0] - coord[2][0], coord[0][1] - coord[2][1]);
			double c = Math.hypot(coord[2][0] - coord[1][0], coord[2][1] - coord[1][1]);
			if (Math.abs(a - b) > EPS && Math.abs(a - c) > EPS && Math.abs(b - c) > EPS)
				System.err.println("ERROR2: " + test);
			loop:
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 2; j++)
					if (Math.abs(coord[i][j]) > 100) {
						System.err.println("ERROR3: " + test + " " + Arrays.deepToString(coord));
						break loop;
					}
			int P = -1;
			if (test < TESTS)
				P = r.nextInt(MAX_P) + 1;
			out.println(coord[0][0] + " " + coord[0][1] + " " + coord[1][0] + " " + coord[1][1] + " " + coord[2][0] + " " + coord[2][1] + " " + P);
		}
		out.flush();
		out.close();
	}
	
}
