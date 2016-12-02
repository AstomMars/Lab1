
public class Print {
	public static String print(node t) {
		String res = "";
		while (t != null) {
			if (t.quotiety != 0) { // 系数不为0，才有东西输出
				int s = 0;
				for (int i = 0; i < t.len; i++) {
					s += t.exponential[i];
				}
				if (t.len == 0 || s == 0) { // 字符串长度为0或者所有字符的次数都为0，此时
					res += String.valueOf(t.quotiety);
				} else {
					if (t.quotiety != 1) {
						if (t.quotiety == -1) {
							res += "-";
						} else {
							res += String.valueOf(t.quotiety);
						}
					}
					for (int temp = 0; temp < t.str.length; temp++) {
						if (t.exponential[temp] != 0) { // 判未知数输出条件
							res += String.valueOf(t.str[temp]);
							if (t.exponential[temp] != 1) { // 判次数输出条件
								res += "^";
								res += String.valueOf(t.exponential[temp]);
							}
						}
					}
				}
			}
			if (t.next != null && t.next.quotiety > 0 && !res.equals("")) {
				res += "+";
			}
			t = t.next;
		}
		if (res.length() != 0) {
			System.out.println(res);
			return res;
		} else {
			System.out.println(0);
			return "0";
		}
	}
}
