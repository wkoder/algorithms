package codeforces;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class ParkingLot {
	
	public static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
		int N = Integer.parseInt(tok.nextToken());
		int M = Integer.parseInt(tok.nextToken());
		HashMap<Integer, Integer> lot = new HashMap<Integer, Integer>(N);
		SegmentTree tree = new SegmentTree(N);
		TreeSet<Pair> avail = new TreeSet<ParkingLot.Pair>();
		Pair.N = N;
		avail.add(new Pair(0, N-1));
		while (M-- > 0) {
			tok = new StringTokenizer(read());
			int type = Integer.parseInt(tok.nextToken());
			int id = Integer.parseInt(tok.nextToken());
//			System.out.println(type + " " + id + ": " + avail);
			if (type == 1) {
				Pair p = avail.first();
				int L = p.getSlot();
				lot.put(id, L);
				tree.add(L, 1);
				avail.remove(p);
				if (L > p.i)
					avail.add(new Pair(p.i, L-1));
				if (L < p.j)
					avail.add(new Pair(L+1, p.j));
				out.println(L+1);
			} else {
				int L = lot.remove(id);
				tree.add(L, -1);
				int l = 0;
				int u = L;
				while (l - u > 3) {
					int m = (l + u) / 2;
					int cnt = tree.getCount(m, L);
					if (cnt == 0)
						u = m;
					else
						l = m+1;
				}
				int i = u;
				while (tree.getCount(i, L) == 0 && i >= 0)
					i--;
				i++;
				
				l = L;
				u = N;
				while (l - u > 3) {
					int m = (l + u) / 2;
					int cnt = tree.getCount(L, m);
					if (cnt == 0)
						l = m;
					else
						u = m-1;
				}
				int j = l;
				while (tree.getCount(L, j) == 0 && j < N)
					j++;
				j--;
				
//				System.out.println(i + " :: " +j);
				
				avail.remove(new Pair(i, L-1));
				avail.remove(new Pair(L+1, j));
				avail.add(new Pair(i, j));
			}
		}
	}
	
	public static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	static class Pair implements Comparable<Pair> {
		static int N;
		int i, j;
		public Pair(int ii, int jj) {
			i = ii;
			j = jj;
		}
		public int size() {
			if (i == 0 || j == N-1)
				return j - i;
			return (j - i) / 2;
		}
		public int getSlot() {
			if (i == 0)
				return 0;
			if (j == N-1)
				return N-1;
			return (j + i) / 2;
		}
		@Override
		public int compareTo(Pair p) {
			if (size() != p.size())
				return p.size() - size();
			return i - p.i;
		}
		@Override
		public String toString() {
			return i + ":" + j;
		}
	}
	
	static class SegmentTree {
		
		int N;
		int[] t;
		
		/**
		 * Crea un SegmentTree para que soporte operaciones en el rango [0, size], inclusivo.
		 * @param size		La posicion maxima que manejara el arbol.
		 */
		SegmentTree(int size) {
			N = 1;
			while (N <= size)
				N <<= 1;
			t = new int[2 * N - 1];
		}
		
		/**
		 * Agrega, o elimina, n puntos en la posicion indicada. Si n < 0, entonces esta eliminando.
		 * Complejidad:		O(lg n)
		 * @param pos		Posicion del punto a agregar.
		 * @param n			Numero de puntos a agregar, o eliminar.
		 */
		void add(int pos, int n) {
			pos += N;
			while (pos > 0) {
				t[pos] += n;
				pos >>= 1;
			}
	    }
		
		/**
		 * Cuenta el numero de puntos que estan dentro del rango [l, r], inclusivo.
		 * Complejidad:		O(lg l + lg r)
		 * @param l			Inicio del rango.
		 * @param r			Fin del rango.
		 * @return			Numero de puntos que contiene el rango especificado.
		 */
		int getCount(int l, int r) {
			l += N;
			r += N;
			if (r >= t.length)
				r = t.length - 1;
			int ans = 0;
			while (l <= r) {
				if (l == r) {
					ans += t[l];
					break;
				}
				if ((l & 1) == 1)
					ans += t[l++];
				if ((r & 1) == 0)
					ans += t[r--];
				l >>= 1;
				r >>= 1;
			}
			return ans;
		}
	}

}
