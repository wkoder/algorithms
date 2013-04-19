package topcoder.unknown;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NameInput {
	public int countUpDownKeyPresses(String[] predictionSequence, String[] name) {
		String seq = "";
		for (String s : predictionSequence)
			seq += s;
		String n = "";
		for (String s : name)
			n += s;
		char[] s = seq.toCharArray();
		
		int N = s.length;
		int K = n.length();
		List<Integer>[] adj = new List[250];
		for (char ch = 0; ch < 250; ch++) {
			adj[ch] = new ArrayList<Integer>();
			for (int i = 0; i < s.length; i++)
				if (ch == s[i])
					adj[ch].add(i);
		}
		
		int INF = 1 << 28;
		Set<Node> set = new TreeSet<Node>();
		set.add(new Node(0, -1, 0));
		int[][] cost = new int[N][K];
		for (int i = 0; i < N; i++)
			Arrays.fill(cost[i], INF);
		while (! set.isEmpty()) {
			Node node = set.iterator().next();
			set.remove(node);
			int nextName = node.namepos + 1;
			if (nextName == K)
				return node.cost;
			char ch = n.charAt(nextName);
			for (int nextSeq : adj[ch]) {
				int d = Math.abs(node.seqpos-nextSeq);
				int addCost = Math.min(d, N-d);
				if (node.cost+addCost < cost[nextSeq][nextName]) {
					Node node2 = new Node(nextSeq, nextName, cost[nextSeq][nextName]);
					set.remove(node2);
					node2.cost = cost[nextSeq][nextName] = node.cost + addCost;
					set.add(node2);
				}
			}
		}
		return -1;
	}
	
	private class Node implements Comparable<Node> {
		int namepos, seqpos, cost;
		public Node(int sp, int np, int c) {
			seqpos = sp;
			namepos = np;
			cost = c;
		}
		public int compareTo(Node n) {
			if (cost != n.cost)
				return cost - n.cost;
			if (seqpos != n.seqpos)
				return seqpos - n.seqpos;
			return namepos - n.namepos;
		}
	}
}