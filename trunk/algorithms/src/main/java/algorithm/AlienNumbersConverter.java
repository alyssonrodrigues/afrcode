package algorithm;

public class AlienNumbersConverter {
	public static String convertOneNumber(String in, String sor, String tar) {
		int tar_num = 0;
		int unit = 1;
		for (int i = in.length() - 1; i >= 0; i--) {
			int face = (Integer) sor.indexOf(in.charAt(i));
			tar_num += face * unit;
			unit *= sor.length();
		}
		String out = "";
		do {
			out = tar.charAt(tar_num % tar.length()) + out;
			tar_num = tar_num / tar.length();
		} while (tar_num > 0);
		return out;
	}
}
