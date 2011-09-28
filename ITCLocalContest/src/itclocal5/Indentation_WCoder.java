package itclocal5;
import java.util.Scanner;

public class Indentation_WCoder {
	
	static void indent(int level) {
		while (level-- > 0)
			System.out.print("    ");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int level = 0; // Start with no indentation.
		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			if (line.charAt(0) == '}')
				indent(--level); // Low one level and the indent.
			else if (line.charAt(line.length()-1) == '{')
				indent(level++); // Indent and then increase the level.
			else
				indent(level); // Just indent.
			System.out.println(line);
		}
	}
	
}
