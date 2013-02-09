import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HeavyBooks {
	
	private void move(ArrayList<Integer> a, ArrayList<Integer> b, int m) {
		Collections.sort(a);
		m = Math.min(m, a.size());
		int to = a.size()-m;
		for (int i = a.size()-1; i >= to; i--)
			b.add(a.remove(i));
	}
	
	public int[] findWeight(int[] books, int[] moves) {
		Arrays.sort(books);
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		int bestT = 1 << 30;
		int bestW = 0;
		for (int k = books.length-moves[0]; k >= 0; k--)  {
			a.clear();
			b.clear();
			for (int i = k; i < k+moves[0]; i++)
				b.add(books[i]);
			for (int i = 1; i < moves.length; i++) {
				if (i % 2 == 0)
					move(a, b, moves[i]);
				else
					move(b, a, moves[i]);
			}
			
			int t = 0;
			for (int aa : a)
				t += aa;
			int w = 0;
			for (int bb : b)
				w += bb;
			if (w-t > bestW-bestT) {
				bestW = w;
				bestT = t;
			}
		}
		
		return new int[]{bestT, bestW};
	}
}
