package topcoder.unknown;
import java.util.Arrays;

public class ColoringRectangle {
	public int chooseDisks(int w, int h, int[] red, int[] blue) {
		Arrays.sort(red);
		Arrays.sort(blue);
		int[][] d = {red, blue};
		int min = 1000;
		for (int c = 0; c < 2; c++) {
			double x = 0;
			int color = c;
			int[] p = new int[2];
			p[color] = d[color].length;
			p[color^1] = d[color^1].length;
			int n = 0;
			while (true) {
				int pos = --p[color];
				if (pos < 0 || d[color][pos] < h)
					break;
				
				double hh = 2 * Math.sqrt(d[color][pos]*d[color][pos]/4.0 - h*h/4.0);
				x += hh;
				n++;
				
				if (x+1e-5 > w) {
					min = Math.min(min, n);
					break;
				}
				color ^= 1;
			}
			
		}
		return min < 1000? min : -1;
	}
}
