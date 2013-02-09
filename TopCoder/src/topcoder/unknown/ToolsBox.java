package topcoder.unknown;
import java.util.*;

public class ToolsBox {
	public int countTools(String[] need) {
		Set<String> set = new HashSet<String>();
		for (String n : need) {
			StringTokenizer tok = new StringTokenizer(n);
			while (tok.hasMoreTokens())
				set.add(tok.nextToken());
		}
		return set.size();
	}
}
