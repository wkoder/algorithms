package topcoder.unknown;
public class FlatCatsBreeding {
	public int days(int D, int K, int T) {
		int cats = 1;
		int d = 0;
		while (cats < T) {
			cats += cats * K;
			d++;
		}
		return d*D;
	}
}
