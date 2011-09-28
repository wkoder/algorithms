package topcoder;
public class AppleWord {
	public int minRep(String word) {
		word = word.toLowerCase();
		if (word.length() < 5)
			return -1;
		int cost = 0;
		if (word.charAt(0) != 'a')
			cost++;
		if (word.charAt(word.length()-1) != 'e')
			cost++;
		if (word.charAt(word.length()-2) != 'l')
			cost++;
		for (int i = 1; i < word.length()-2; i++)
			if (word.charAt(i) != 'p')
				cost++;
		return cost;
	}
}
