import java.io.*;
import java.util.Arrays;

public class GuitarHero_WCoder {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int[] state = new int[5];
		while ((line = in.readLine()) != null) {
			if (line.equals("")) {
				System.out.println();
				continue;
			}
			if (line.equals("-")) {
				System.out.println("-----");
				continue;
			}
			
			Arrays.fill(state, 0);
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if (Character.isUpperCase(ch))
					state[ch -'A'] = 2;
				else
					state[ch -'a'] = 1;
			}
			for (int i = 0; i < 5; i++)
				if (state[i] == 0)
					System.out.print("-");
				else if (state[i] == 1)
					System.out.print("O");
				else
					System.out.print("|");
			System.out.println();
		}
	}
}
