package topcoder.unknown;
public class ProductTriplet {
	public long countTriplets(int minx, int maxx, int miny, int maxy, int minz, int maxz) {
		long ans = 0;
		int lim = 1 << 16;
		for (int x = minx; x <= maxx && x < lim; x++) {
			long miny2 = minz / x;
			if (x*miny2 < minz)
				miny2++;
			miny2 = Math.max(miny2, miny);
			
			long maxy2 = maxz / x;
			maxy2 = Math.min(maxy2, maxy);
			
			if (maxy2 >= miny2)
				ans += maxy2 - miny2 + 1;
		}
		for (int y = miny; y <= maxy && y < lim; y++) {
			long minx2 = minz / y;
			if (y*minx2 < minz)
				minx2++;
			minx2 = Math.max(minx2, minx);
			minx2 = Math.max(minx2, lim);
			
			long maxx2 = maxz / y;
			maxx2 = Math.min(maxx2, maxx);
			
			if (maxx2 >= minx2)
				ans += maxx2 - minx2 + 1;
		}
		return ans;
	}
}
