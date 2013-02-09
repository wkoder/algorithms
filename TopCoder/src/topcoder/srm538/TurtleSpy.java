package topcoder.srm538;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TurtleSpy {
	public double maxDistance(String[] commands) {
		int fw = 0;
		int bw = 0;
		final String FW = "forward ";
		final String BW = "backward ";
		final String L = "left ";
		final String R = "right ";
		List<Integer> a = new ArrayList<Integer>();
		for (String cmd : commands) {
			if (cmd.startsWith(FW))
				fw += Integer.parseInt(cmd.substring(FW.length()));
			else if (cmd.startsWith(BW))
				bw += Integer.parseInt(cmd.substring(BW.length()));
			else if (cmd.startsWith(L))
				a.add(Integer.parseInt(cmd.substring(L.length())));
			else
				a.add(360 - Integer.parseInt(cmd.substring(R.length())));
		}
		
		boolean[] cur = new boolean[360];
		boolean[] next = new boolean[360];
		cur[0] = true;
		for (int angle : a) {
			Arrays.fill(next, false);
			for (int i = 0; i < 360; i++) {
				next[i] |= cur[i];
				next[(i + angle) % 360] |= cur[i];
			}
			
			boolean[] tmp = cur;
			cur = next;
			next = tmp;
		}
		
		int angle = 0;
		for (int i = 1; i < 360; i++)
			if (cur[i] && Math.abs(180 - i) < Math.abs(180 - angle))
				angle = i;
		
		double rad = Math.toRadians(Math.abs(180 - angle));
		int r = Math.min(fw, bw);
		int d = Math.max(fw, bw);
		double x = r * Math.cos(rad);
		double y = r * Math.sin(rad);
		return Math.hypot(x + d, y);
	}
}
