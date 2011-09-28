package itclocal5;
import java.util.*;

public class Dijkstra_WCoder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		final String DIJKSTRA = "dijkstra";
		
		int T = scan.nextInt();
		while (T-- > 0) {
			int N = scan.nextInt();
			Map<String, Integer> cost = new HashMap<String, Integer>();
			Map<String, Integer> weight = new HashMap<String, Integer>();
			Map<String, List<String>> adj = new HashMap<String, List<String>>();
			for (int i = 0; i < N; i++) {
				String name = scan.next();
				weight.put(name, scan.nextInt());
				int l = scan.nextInt();
				List<String> links = new ArrayList<String>();
				while (l-- > 0)
					links.add(scan.next());
				adj.put(name, links);
			}
			String target = scan.next();

			SortedSet<Page> pages = new TreeSet<Page>();
			// Initial cost
			cost.put(DIJKSTRA, weight.get(DIJKSTRA));
			pages.add(new Page(DIJKSTRA, weight.get(DIJKSTRA)));
			while (! pages.isEmpty()) {
				Page p = pages.first();
				pages.remove(p);
				if (p.name.equals(target)) // Stop looking
					break;
				
				for (String link : adj.get(p.name)) { // Improve links cost
					int newCost = p.cost + weight.get(link);
					Integer currCost = cost.get(link);
					if (currCost == null)
						currCost = 1 << 28;
					if (currCost > newCost) {
						Page p2 = new Page(link, currCost);
						pages.remove(p2); // Remove old reference
						// Update cost
						p2.cost = newCost;
						cost.put(link, newCost);
						pages.add(p2); // Add with the new cost
					}
				}
			}
			
			System.out.println(cost.get(target));
		}
	}
	
	static class Page implements Comparable<Page> {
		String name;
		int cost;
		public Page(String n, int c) {
			name = n;
			cost = c;
		}
		public int compareTo(Page p) {
			if (cost != p.cost)
				return cost - p.cost;
			return name.compareTo(p.name);
		}
	}
}
