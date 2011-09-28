import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class GeneralMobilization {
	
	static void build(int cur, List<City>[] adj, City[] cities, Queue<City> toProcess) {
		for (City neigh : adj[cur]) {
			int from = neigh.a;
			if (from == cur)
				from = neigh.b;
			if (cities[from] != null)
				continue;
			
			neigh.a = from;
			neigh.b = cur;
			cities[from] = neigh;
			toProcess.add(neigh);
			build(from, adj, cities, toProcess);
		}
	}
	
	static void solve() {
		int n = Integer.parseInt(read());
		City[] cities = new City[n];
		Army[] armies = new Army[n];
		StringTokenizer tok = new StringTokenizer(read());
		for (int i = 0; i < n; i++)
			armies[i] = new Army(i, Integer.parseInt(tok.nextToken()));
		List<City>[] adj = new List[n];
		for (int i = 0; i < n; i++)
			adj[i] = new ArrayList<City>();
		
		for (int i = 0; i < n-1; i++) {
			tok = new StringTokenizer(read());
			int a = Integer.parseInt(tok.nextToken())-1;
			int b = Integer.parseInt(tok.nextToken())-1;
			int c = Integer.parseInt(tok.nextToken());
			City city = new City();
			city.a = a;
			city.b = b;
			city.cap = c;
			adj[a].add(city);
			adj[b].add(city);
		}
		
		cities[0] = new City();
		Queue<City> toProcess = new LinkedList<GeneralMobilization.City>();
		build(0, adj, cities, toProcess);
		
		for (int i = 1; i < n; i++)
			cities[i].q.add(armies[i]);
		
		int time = 0;
		while (!toProcess.isEmpty()) {
			time++;
			while (!toProcess.isEmpty()) {
				City cur = toProcess.poll();
				City par = cities[cur.b];
				for (int i = 0; i < cur.cap && !cur.q.isEmpty(); i++) {
					Army neigh = cur.q.poll();
					neigh.t = time;
					par.q.add(neigh);
				}
			}
			
			for (int i = 1; i < n; i++)
				if (!cities[i].q.isEmpty())
					toProcess.add(cities[i]);
		}
		
		System.out.print(0);
		for (int i = 1; i < n; i++)
			System.out.print(" " + armies[i].t);
		System.out.println();
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	static class City {
		PriorityQueue<Army> q;
		int cap;
		int a, b;
		public City() {
			q = new PriorityQueue<GeneralMobilization.Army>();
		}
	}
	
	static class Army implements Comparable<Army> {
		int k;
		int p;
		int t;
		public Army(int kk, int pp) {
			k = kk;
			p = pp;
		}
		public int compareTo(Army o) {
			return p - o.p;
		}
	}
}
