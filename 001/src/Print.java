
public class Print {
	public static String print(node t) {
		String res = "";
		while (t != null) {
			if (t.quotiety != 0) { // ϵ����Ϊ0�����ж������
				int s = 0;
				for (int i = 0; i < t.len; i++) {
					s += t.exponential[i];
				}
				if (t.len == 0 || s == 0) { // �ַ�������Ϊ0���������ַ��Ĵ�����Ϊ0����ʱ
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
						if (t.exponential[temp] != 0) { // ��δ֪���������
							res += String.valueOf(t.str[temp]);
							if (t.exponential[temp] != 1) { // �д����������
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
