package topcoder.unknown;
public class EasySequence {
	public int find(int[] A, int[] B) {
		int MAX = 200000;
		int[] s = new int[MAX];
		for (int i = 0; i < A.length; i++)
			s[i] = A[i];
		for (int i = A.length; i < A.length*2; i++)
			for (int j = i-A.length; j < i; j++)
				s[i] = (s[j] + s[i]) % 10;
		for (int i = A.length*2; i < MAX; i++)
			s[i] = (2*s[i-1] - s[i-1-A.length] + 10) % 10;
		next:
		for (int i = 0; i < MAX-B.length; i++) {
			int j;
			for (j = 0; j < B.length; j++)
				if (s[i+j] != B[j])
					continue next;
			return i;
		}
		return -1;
	}
}
