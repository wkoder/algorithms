package topcoder.tco12.qual2;
public class FoxAndKgram {
	public int maxK(int[] len) {
		int[] L = new int[51];
		for (int i = 0; i < len.length; i++)
			L[len[i]]++;
		for (int k = 50; k > 0; k--) {
			int w = L[k]*2;
			for (int i = 1; i < k; i++)
				w += Math.min(L[i], L[k-1-i]);
			if (w/2 >= k)
				return k;
		}
		
		return 0;
	}
}
