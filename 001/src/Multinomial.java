import java.io.IOException;
import java.util.Scanner;

public class Multinomial {
	public void quick_sort(node tempNode, int l, int r) { // 排序函数，对字符数组排序，方便合并同类项
		if (l < r) {
			int i = l, j = r, x = tempNode.exponential[l];
			char y = tempNode.str[l];
			while (i < j) {
				while (i < j && tempNode.str[j] >= y) {
					j--;
				}
				if (i < j) {
					tempNode.exponential[i] = tempNode.exponential[j];
					tempNode.str[i] = tempNode.str[j];
					i++;
				}
				while (i < j && tempNode.str[i] < y) {
					i++;
				}
				if (i < j) {
					tempNode.exponential[j] = tempNode.exponential[i];
					tempNode.str[j] = tempNode.str[i];
					j--;
				}
			}
			tempNode.exponential[i] = x;
			tempNode.str[i] = y;
			quick_sort(tempNode, l, i - 1); // 递归调用
			quick_sort(tempNode, i + 1, r);
		}
	}

	// @SuppressWarnings("resource")

	public int read(char ch) throws IOException {
		if (ch == '\n') {
			return 0;
		}
		Scanner scanner = new Scanner(System.in);
		String cmd = scanner.nextLine();
		cmd = ch + cmd;
		// scanner.close();
		return expression(cmd);
	}

	public int expression(String str) { // 读入函数并建立链表储存数据
		node temp = head;
		char pre_ch = '!';
		int i, num = 3;
		int index = 0;
		// pre_ch表示上一个变量名
		// num = 0 表示上个是变量或结点开头;num = 1 表示上个是'^';num = 2表示上个是数字;num = 3表示上一个是运算符

		// System.out.println(cmd);
		char[] string = str.toCharArray();
		char ch;
		for (int j = 0; j < string.length; j++) {
			ch = string[j];
			if (ch == '*') {
				num = 3;
			} else if (ch == '+') {
				temp.len = index;
				quick_sort(temp, 0, temp.len - 1);
				index = 0;
				temp.next = new node();
				temp = temp.next;
				num = 3;
			} else if (ch == '-') {
				if (pre_ch == '!') {
					temp.quotiety = -1;
					num = 3;
				} else {
					temp.len = index;
					quick_sort(temp, 0, temp.len - 1);
					index = 0;
					temp.next = new node();
					temp = temp.next;
					temp.quotiety = -1;
					num = 3;
				}
			} else if (ch == '^') {
				if (!((65 <= pre_ch && pre_ch <= 90) || (97 <= pre_ch && pre_ch <= 122))) {
					System.out.println("输入不合法");
					return -1;
				}
				num = 1;
			} else if (48 <= ch && ch <= 57) { // 字母在前数字在后时，不能省略'*'
				if (num == 3) {
					temp.quotiety = temp.quotiety * (ch - 48);
				} else if (num == 1) {
					for (i = 0; i < index; i++) {
						if (pre_ch != temp.str[i]) {
							continue;
						} else {
							temp.exponential[i] += (ch - 48 - 1);
							break;
						}
					}
					i = 0;
					num = 1;
				} else if (num == 2) {
					if (temp.quotiety < 0) {
						temp.quotiety = (temp.quotiety) * 10 - (ch - 48);
					} else {
						temp.quotiety = (temp.quotiety) * 10 + (ch - 48);
					}
				}
				num = 2;
			} else if ((65 <= ch && ch <= 90) || (97 <= ch && ch <= 122)) {
				for (i = 0; i < index; i++) {
					if (ch != temp.str[i]) {
						continue;
					} else {
						temp.exponential[i]++;
						break;
					}
				}
				if (i == index) {
					temp.str[index] = ch;
					temp.exponential[index] = 1;
					index++;
					i = 0;
				}
				num = 0;
				pre_ch = ch;
			} else {
				System.out.print("输入不合法");
			}

			// ch = (char) System.in.read();
			if (j == string.length - 1) {
				temp.len = index;
				quick_sort(temp, 0, temp.len - 1);
				return 0;
			}
		}
		return 0;
	}

	public void combining(node Node) { // 合并同类项
		node NowNode = Node;
		node TempNode;
		node Temp_preNode;
		if (Node.next == null) {
			return;
		}
		int i, counter;
		do {
			TempNode = NowNode.next;
			Temp_preNode = NowNode;
			while (TempNode != null) {
				String s = String.valueOf(NowNode.str);
				String t = String.valueOf(TempNode.str);
				if (s.equals(t)) {
					counter = 0;
					for (i = 0; i < NowNode.len; i++) {
						if (NowNode.exponential[i] == TempNode.exponential[i]) {
							counter++;
						} else {
							break;
						}
					}
					if (counter == NowNode.len) {
						NowNode.quotiety += TempNode.quotiety;
						Temp_preNode.next = TempNode.next;
						TempNode = Temp_preNode.next;
						continue;
					}
					Temp_preNode = Temp_preNode.next;
					TempNode = TempNode.next;
				} else {
					Temp_preNode = Temp_preNode.next;
					TempNode = TempNode.next;
				}
			}
			NowNode = NowNode.next;
		} while (NowNode != null);
		do {
			if (Node.next == null) {
				break;
			}
			if (Node.len != 0) {
				Node = Node.next;
			} else {
				node temp = Node;
				while (Node.next != null) {
					if (Node.next.len == 0) {
						temp.quotiety += Node.next.quotiety;
						Node.next = Node.next.next;
					} else {
						Node = Node.next;
					}
				}
			}
		} while (Node.next != null);
	}

	public void simplify(char ch, int num) { // 赋值
		node TempNode = forother;
		do {
			for (int i = 0; i < TempNode.len; i++) {
				if (TempNode.str[i] == ch) {
					TempNode.quotiety *= (num << (TempNode.exponential[i] - 1));
					TempNode.exponential[i] = 0;
					break;
				}
			}
			int count = 0;
			for (int i = 0; i < TempNode.len; i++) {
				count += TempNode.exponential[i];
			}
			if (count == 0) {
				TempNode.len = 0;
			}
			TempNode = TempNode.next;
		} while (TempNode != null);
	}

	public void derivative(char x) { // 求导
		node t = forother;
		node h;
		h = t;
		int i = 0;
		while (t != null && i <= t.len) { // 求导函数
			if (t.str[i] == x) {
				t.quotiety *= t.exponential[i];
				t.exponential[i] -= 1;
				int count = 0;
				for (int k = 0; k < t.len; k++) {
					count += t.exponential[k];
				}
				if (count == 0) {
					t.len = 0;
				}
				t = t.next;
				i = 0;
				continue;
			}
			i++;
			if (i >= t.len) { // 找不到x
				t.quotiety = 0;
				t = t.next;
				i = 0;
			}
		}
		t = h;
	}

	public String Print(node t) {
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

	public void mulcopy() { // 创建一个镜像链表
		node temphead = head;
		node tempfor = forother;
		do {
			tempfor.quotiety = temphead.quotiety;
			tempfor.len = temphead.len;
			for (int i = 0; i < temphead.len; i++) {
				tempfor.exponential[i] = temphead.exponential[i];
				tempfor.str[i] = temphead.str[i];
			}
			if (temphead.next != null) {
				temphead = temphead.next;
				tempfor.next = new node();
				tempfor = tempfor.next;
			} else {
				break;
			}
		} while (true);
	}

	public node head = new node();// 储存链表的头指针
	public node forother = new node();// 镜像链表的头指针
}
