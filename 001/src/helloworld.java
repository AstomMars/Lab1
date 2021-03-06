import java.io.IOException;
import java.util.Scanner;


public class helloworld {
	public static void main(String[] args) throws IOException {
		char ch;
		Multinomial multinomial1 = null;
		Scanner s = new Scanner(System.in);
		System.out.println("input something");
		while (true) {
			ch = (char) System.in.read();
			if (ch == '!') {
				if (multinomial1 != null) {
					String cmd = s.nextLine();
					char nd = 'y';// 随便初始化一个
					if (cmd.length() > 2 && cmd.substring(0, 3).equals("end")) {
						break;
					} else if (cmd.length() > 2 && cmd.substring(0, 3).equals("d/d")) {
						nd = cmd.charAt(3);
						multinomial1.derivative(nd);
						multinomial1.combining(multinomial1.forother);
						multinomial1.Print(multinomial1.forother);
					} else if (cmd.length() > 8 && cmd.substring(0, 9).equals("simplify ")) {
						String now = cmd.substring(9);
						String[] cmdlist = now.split(" ");
						String[] cmdsplit;
						for (int j = 0; j < cmdlist.length; j++) {
							cmdsplit = cmdlist[j].split("=");
							char chnow;
							int numnow;
							chnow = cmdsplit[0].charAt(0);
							numnow = Integer.valueOf(cmdsplit[1]);
							multinomial1.simplify(chnow, numnow);
						}
						multinomial1.combining(multinomial1.forother);
						multinomial1.Print(multinomial1.forother);
					} else {
						System.out.println("输入的命令不合法，请重新输入");
					}
				} else {
					System.out.println("多项式为空，请先输入多项式");
					s.nextLine();
				}
			} else {
				multinomial1 = new Multinomial();
				int num = multinomial1.read(ch);
				if (num < 0) {
					break;
				}
				multinomial1.combining(multinomial1.head);
				multinomial1.Print(multinomial1.head);
				// System.in.read();
			}
			if (multinomial1 != null) {
				multinomial1.mulcopy();
			}
		}
		s.close();
	}
}
