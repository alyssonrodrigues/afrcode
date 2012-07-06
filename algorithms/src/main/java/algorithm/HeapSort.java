package algorithm;

import datastruct.Element;
import datastruct.MaxHeap;

public class HeapSort {

	// O(nlgn) ...
	public static void sort(Element[] elements) {
		MaxHeap heap = new MaxHeap(elements);
		heap.sort();
	}
}
