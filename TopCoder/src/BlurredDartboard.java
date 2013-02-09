import java.util.Arrays;
import java.util.HashSet;

public class BlurredDartboard {
	public int minThrows(int[] points, int P) {
		int N = points.length;
		Arrays.sort(points);
		int max = points[N-1];
		
		HashSet<Integer> unk = new HashSet<Integer>();
		for (int i = 1; i <= N; i++)
			unk.add(i);
		for (int i = 0; i < N; i++)
			unk.remove(points[i]);
		Integer[] u = unk.toArray(new Integer[0]);
		Arrays.sort(u);
		
		int resT = P;
		if (u.length > 0) {
			int sumU = 0;
			for (int uu : u)
				sumU += uu;
			
			int t = P / sumU * u.length;
			if (P % sumU != 0) {
				int tt = t;
				int r = P % sumU;
				for (int i = 0; i < u.length && r > 0; i++) {
					r -= u[i];
					t++;
				}
				
				if (max > 0) {
					r = P % sumU;
					tt += r / max;
					if (r % max != 0)
						tt++;
					t = Math.min(t, tt);
				}
			}
			resT = Math.min(resT, t);
		}
		
		if (max > 0) {
			int t = P / max;
			if (P % max != 0)
				t++;
			resT = Math.min(resT, t);
		}
		
		return resT;
	}
}
