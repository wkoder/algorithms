package topcoder.unknown;
public class SilverDistance {
	public int minSteps(int sx, int sy, int gx, int gy) {
		gx -= sx;
		gy -= sy;
		if (gx == 0 && gy >= 0)
			return gy;
		int s = 0;
		if ((gx & 1) != (gy & 1)) {
			gy--;
			s++;
		}
		return s + Math.max(Math.abs(gx), Math.abs(gy));
	}
}
