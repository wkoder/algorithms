package topcoder;
public class LotteryTicket {
	public String buy(int price, int b1, int b2, int b3, int b4) {
		int[] b = {b1, b2, b3, b4};
		for (int m = 0; m < 1 << 4; m++) {
			int s = 0;
			for (int i = 0; i < 4; i++)
				if ((m & (1 << i)) != 0)
					s += b[i];
			if (s == price)
				return "POSSIBLE";
		}
		return "IMPOSSIBLE";
	}
}
