package algorithm;

import datastruct.Element;

public class MinMax {

	// O(n) ...
	public static Element[] minMax(Element[] elements) {
		int min = 0;
		int max = 0;
		for (int i = 1; i < elements.length; i++) {
			if (elements[i].key.compareTo(elements[min].key) < 0) {
				min = i;
			}
			if (elements[i].key.compareTo(elements[max].key) > 0) {
				max = i;
			}
		}
		return new Element[] {elements[min], elements[max]};
	}

}
