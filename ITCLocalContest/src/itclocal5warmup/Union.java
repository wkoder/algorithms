package itclocal5warmup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Union {
	
	public static void main(String[] args) throws Exception {
		String prefix = "soccer";
		int total = 10;
		PrintWriter ent = new PrintWriter(prefix.toLowerCase() + ".in");
		PrintWriter sal = new PrintWriter(prefix.toLowerCase() + ".out");
		ent.println(total);
		for (int i = 0; i < total; i++) {
			write(ent, new BufferedReader(new FileReader(prefix + i + ".ent")));
			write(sal, new BufferedReader(new FileReader(prefix + i + ".sal")));
		}
		ent.flush();
		ent.close();
		sal.flush();
		sal.close();
	}
	
	private static void write(PrintWriter out, BufferedReader in) throws IOException {
		String s;
		while ((s = in.readLine()) != null)
			out.println(s);
	}
	
}
