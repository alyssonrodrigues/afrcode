package algorithm;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	// O(n) ...
	public static void revert(char[] s) {
		int size = s.length;
		for (int i = 0; i < size / 2; i++) {
			swap(s, i, size - i - 1);
		}
	}
	
	// O(n!) ...
	public static List<String> permut(char[] s, int pos) {
		List<String> p = new ArrayList<String>();
		int size = s.length;
		if (size - pos < 2) {
			return p;
		}
		if (size - pos == 2) {
			p.add(String.valueOf(s));
			swap(s, size - 1, size - 2);
			p.add(String.valueOf(s));
			swap(s, size - 1, size - 2);
		} else {
			for (int i = pos; i < size; i++) {
				swap(s, i, pos);
				p.addAll(permut(s, pos + 1));
				swap(s, i, pos);
			}
		}
		return p;
	}
	
	// O(n) ...
	public static List<String> split(char[] s, char t) {
		List<String> words = new ArrayList<String>();
		StringBuffer token = new StringBuffer(s.length);
		int length = s.length;
		int lastIndexOfT = 0;
		for (int i = 0; i < length; i++) {
			if (s[i] == t) {
				if (i - lastIndexOfT >= 1) {
				  words.add(token.toString());
				}
				token = new StringBuffer(length - i - 1);
				lastIndexOfT = i;
			}
			else if (i + 1 == length) {
				token.append(s[i]);
				words.add(token.toString());
			}
			else {
				token.append(s[i]);
			}
		}
		return words;
	}
	
	public static void swap(char[] s, int i, int j) {
		char c = s[i];
		s[i] = s[j];
		s[j] = c;
	}
}
