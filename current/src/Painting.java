import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Painting {
	
	static final int MAX = 10000;
	static Color[] colors = new Color[MAX];
	
	public static void solve() {
		while (true) {
			StringTokenizer tok = new StringTokenizer(read());
			int R = Integer.parseInt(tok.nextToken());
			int C = Integer.parseInt(tok.nextToken());
			if (R + C == 0)
				break;
			
			for (int i = 0; i < MAX; i++)
				colors[i] = new Color(i);
			for (int i = 0; i < R; i++) {
				tok = new StringTokenizer(read());
				for (int j = 0; j < C; j++) {
					int k = Integer.parseInt(tok.nextToken());
					colors[k].points.add(new Point(i, j));
				}
			}
			for (int i = 0; i < MAX; i++) // RE?
				colors[i].assertOK();
			Arrays.sort(colors);
			boolean first = true;
			for (int i = 0; i < MAX; i++)
				if (colors[i].points.size() > 0) {
					if (!first)
						out.print(" ");
					first = false;
					out.print(colors[i].color);
				}
			out.println();
		}
	}
	
	public static class Color implements Comparable<Color> {
		int color;
		List<Point> points = new ArrayList<Point>();
		public Color(int c) {
			color = c;
		}
		public void assertOK() {
			for (Point a : points)
				for (Point b : points)
					if (a != b && a.x != b.x && a.y != b.y)
						throw new RuntimeException("DAMMIT");
		}
		public int compareTo(Color c) {
			if (points.size() != c.points.size())
				return points.size() - c.points.size();
			return color - c.color;
		}
	}
	
	public static class Point {
		int x, y;
		public Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
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
