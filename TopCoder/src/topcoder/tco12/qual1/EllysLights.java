package topcoder.tco12.qual1;
public class EllysLights {
	public int getMinimum(String initial, String[] switches) {
		int S = switches.length;
		int[] sw = new int[S];
		for (int i = 0; i < initial.length(); i++) {
			int a = -1;
			int b = -1;
			for (int j = 0; j < S; j++)
				if (switches[i].charAt(j) == 'Y')
					if (a == -1)
						a = j;
					else
						b = j;
			if (initial.charAt(i) == 'Y')
				if (b == -1)
					if (sw[a] == -1)
						return -1;
					else
						sw[a] = 1;
				else
					; // XOR a-b
			else if (b == -1)
				if (sw[a] == 1)
					return -1;
				else
					sw[a] = -1;
			else
				; // == a-b
		}
		
		return 0;
	}
}
