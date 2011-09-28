import java.util.Scanner;


public class Distance_WCoder {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		scan.next();
		while (T-- > 0) {
			scan.next();
			
			String maxIP = "";
			double max = -1;
			String add = scan.next();
			while (!add.equals("traceroute")) {
				String ip = scan.next();
				String a = scan.next();
				String b = scan.next();
				String c = scan.next();
				int d = 0;
				double v = 0;
				if (!a.equals("*")) {
					d++;
					v += Double.parseDouble(a);
				}
				if (!b.equals("*")) {
					d++;
					v += Double.parseDouble(b);
				}
				if (!c.equals("*")) {
					d++;
					v += Double.parseDouble(c);
				}
				v /= d;
				if (v > max) {
					max = v;
					maxIP = ip.substring(1, ip.length()-1);
				}
				
				if (scan.hasNext())
					add = scan.next();
				else
					break;
			}
			System.out.println(maxIP);
		}
	}
}
