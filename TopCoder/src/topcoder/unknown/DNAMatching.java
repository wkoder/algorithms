package topcoder.unknown;
public class DNAMatching {
	boolean is(String a, String b) {
		if (a.length() != b.length())
			return false;
		for (int i = 0; i < a.length(); i++)
			if (comp(a.charAt(i)) != b.charAt(b.length()-1-i))
				return false;
		return true;
	}
	char comp(char x) {
		switch (x) {
		case 'A': return 'T';
		case 'C': return 'G';
		case 'T': return 'A';
		case 'G': return 'C';
		}
		return 'X';
	}
	public int getMaxSize(String[] dna) {
		int N = dna.length;
//		boolean[][] is = new boolean[N][N];
//		for (int i = 0; i < N; i++)
//			for (int j = 0; j < N; j++)
//				if (i != j)
//					is[i][j] = is(dna[i], dna[j]);
		int x = N;
		for (int i = 0; i < N; i++)
			for (int j = i+1; j < N; j++)
				if (is(dna[i], dna[j]))
					x--;
		return x;
	}
}
