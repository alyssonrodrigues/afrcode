package algorithm;

import datastruct.Element;

public class BubbleSort {

	// O(n^2) ...
	public static void sort(Element[] elements) {
		for (int i = 0; i < elements.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < elements.length; j++) {
				if (elements[j].key.compareTo(elements[min].key) < 0) {
					min = j;
				}
			}
			Element temp = elements[i];
			elements[i] = elements[min];
			elements[min] = temp;
		}
	}
}
