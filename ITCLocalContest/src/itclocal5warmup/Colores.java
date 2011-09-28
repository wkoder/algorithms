package itclocal5warmup;
import java.util.*;

public class Colores {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while (T-- > 0) {
			int[] rgb = new int[3];
			boolean ok = false;
			for (int i = 0; i < 3; i++) {
				rgb[i] = scan.nextInt();
				int color = 255 - rgb[i];
				if (Math.abs(rgb[i] - color) > 32)
					ok = true;
				rgb[i] = color;
			}
			if (! ok)
				for (int i = 0; i < 3; i++)
					rgb[i] = (255 - rgb[i] + 128) % 256;
			System.out.println(rgb[0] + " " + rgb[1] + " "+ rgb[2]);
		}
	}
	
}
