package topcoder.unknown;
import java.util.*;

public class T9 {
	public String message(String[] part, String[] dict, String[] keystr) {
		Map<String, List<String>> d = new HashMap<String, List<String>>();
		int[] pos = new int[256];
		for (int i = 0; i < part.length; i++)
			for (int j = 0; j < part[i].length(); j++)
				pos[part[i].charAt(j)] = i + 1;
		for (String s : dict) {
			String map = "";
			for (int i = 0; i < s.length(); i++)
				map += pos[s.charAt(i)];
			List<String> matches;
			if (! d.containsKey(map)) {
				matches = new ArrayList<String>();
				d.put(map, matches);
			} else
				matches = d.get(map);
			matches.add(s);
		}
		for (List<String> matches : d.values())
			Collections.sort(matches);
		
		String ans = "";
		String key = "";
		for (String k : keystr)
			key += k;
		String w = "";
		for (int i = 0; i < key.length(); i++)
			if (key.charAt(i) != '0')
				w += key.charAt(i);
			else {
				if (w.length() > 0) {
					int p = 0;
					while (true) {
						if (w.charAt(w.length()-1) == '#') {
							p++;
							w = w.substring(0, w.length()-1);
						} else if (w.charAt(w.length()-1) == '*') {
							p += 5;
							w = w.substring(0, w.length()-1);
						} else
							break;
					}
					List<String> matches = d.get(w);
					ans += matches.get(p % matches.size());
					w = "";
				}
				ans += " ";
			}
		
		if (w.length() > 0) {
			int p = 0;
			while (true) {
				if (w.charAt(w.length()-1) == '#') {
					p++;
					w = w.substring(0, w.length()-1);
				} else if (w.charAt(w.length()-1) == '*') {
					p += 5;
					w = w.substring(0, w.length()-1);
				} else
					break;
			}
			List<String> matches = d.get(w);
			ans += matches.get(p % matches.size());
			w = "";
		}
		
		return ans;
	}
}
