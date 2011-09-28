package itclocal5;
import java.util.*;

public class HouseMD_WCoder {

	public static void main(String[] args) {
		final int MAX = 100;
		final int NEVER_MET = Integer.MAX_VALUE;
		Scanner scan = new Scanner(System.in);
		int[][] metAt = new int[MAX][MAX];
		int[] infectedAt = new int[MAX];
		SortedSet<Zombie> zombies = new TreeSet<Zombie>(); // Agended persons to be zombies.
		while (true) {
			int N = scan.nextInt();
			if (N == 0)
				break;
			int T = scan.nextInt();
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					metAt[i][j] = scan.nextInt();
					if (metAt[i][j] == -1)
						metAt[i][j] = NEVER_MET;
				}
			zombies.clear();
			Arrays.fill(infectedAt, NEVER_MET);
			
			infectedAt[0] = 0;
			zombies.add(new Zombie(0, 0));
			int stillNotZombie = N;
			while (! zombies.isEmpty()) {
				Zombie zombie = zombies.first();
				zombies.remove(zombie); // Don't process it again.
				int person = zombie.person;
				int time = zombie.time;
				stillNotZombie--; // Oh no, we lose one more =(
				for (int i = 0; i < N; i++)
					// If we met this person after we got sick but not after the T limit and he wasn't infected already.
					if (metAt[person][i] >= time && metAt[person][i] < T && infectedAt[i] > metAt[person][i]) {
						Zombie newZombie = new Zombie(i, infectedAt[i]);
						zombies.remove(newZombie); // Just in case this person was agended to be infected later.
						infectedAt[i] = metAt[person][i];
						newZombie.time = metAt[person][i];
						zombies.add(newZombie); // Agended zombie.
					}
			}
			System.out.println(stillNotZombie);
		}
	}
	
	static class Zombie implements Comparable<Zombie> {
		int person;
		int time;
		public Zombie(int p, int t) {
			person = p;
			time = t;
		}
		public int compareTo(Zombie z) {
			if (time != z.time)
				return time - z.time;
			return person - z.person;
		}
	}
}
