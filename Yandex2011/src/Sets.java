import java.util.*;

public class Sets {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = n*(n-1) / 2;
		Set<Integer>[] sets = new Set[m];
		Set<Integer> all = new HashSet<Integer>();
		for (int i = 0; i < m; i++) {
			int k = scan.nextInt();
			sets[i] = new HashSet<Integer>();
			while (k--> 0) {
				int x = scan.nextInt();
				sets[i].add(x);
				all.add(x);
			}
		}
		
		if (n == 2) {
			Object[] a = all.toArray();
			System.out.println(1 + " " + a[0]);
			System.out.print((a.length-1));
			for (int i = 1; i < a.length; i++)
				System.out.print(" " + a[i]);
			System.out.println();
			
			return;
		}
		
		Set<Integer> processed = new HashSet<Integer>();
		for (int x : all) {
			if (processed.contains(x))
				continue;
			
			Set<Integer> f = new HashSet<Integer>(all);
			for (int i = 0; i < m; i++)
				if (sets[i].contains(x))
					f.retainAll(sets[i]);
			
			System.out.print(f.size());
			for (int xx : f)
				System.out.print(" " + xx);
			System.out.println();
			
			processed.addAll(f);
		}
	}
}
