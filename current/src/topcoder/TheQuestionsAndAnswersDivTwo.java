package topcoder;
import java.util.HashSet;
import java.util.Set;

public class TheQuestionsAndAnswersDivTwo {
	public int find(String[] questions) {
		Set<String> set = new HashSet<String>();
		for (String s : questions)
			set.add(s);
		return 1 << set.size();
	}
}
