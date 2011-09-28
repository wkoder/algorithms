import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class EmbassyQueue {
	
	static void move(Queue<P> q1, Queue<P> q2, int win, int time) {
		
	}
	
	static void solve() {
		int[] win = new int[3];
		int[] time = new int[3];
		StringTokenizer tok = new StringTokenizer(read());
		for (int i = 0; i < win.length; i++)
			win[i] = Integer.parseInt(tok.nextToken());
		tok = new StringTokenizer(read());
		for (int i = 0; i < time.length; i++)
			time[i] = Integer.parseInt(tok.nextToken());
		
		Queue<P> q1 = new LinkedList<P>();
		Queue<P> q2 = new LinkedList<P>();
		Queue<P> q3 = new LinkedList<P>();
		Queue<P> q4 = new LinkedList<P>();
		
		int N = Integer.parseInt(read());
		tok = new StringTokenizer(read());
		while (N-- > 0) {
			int c = Integer.parseInt(tok.nextToken());
			P p = new P(c, 0);
			q1.add(p);
		}
		
		move(q1, q2, win[0], time[0]);
		move(q2, q3, win[1], time[1]);
		move(q3, q4, win[2], time[2]);
		
		long max = 0;
		while (!q4.isEmpty())
			max = Math.max(max, q4.poll().time);
		System.out.println(max);
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
	
	static class P {
		long got, time;
		public P(int ggot, int ttime) {
			got = ggot;
			time = ttime;
		}
	}
}
