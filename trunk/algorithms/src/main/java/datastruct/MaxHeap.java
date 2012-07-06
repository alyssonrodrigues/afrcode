package datastruct;

import java.util.Arrays;

public class MaxHeap {
	// array's index in java starts at 0 ...
	private Element[] elements = null;
	private int heapSize = -1;

	public MaxHeap(Element[] e) {
		this.elements = e;
		heapSize = this.elements.length;
		buildMaxHeap();
	}

	// O(nlgn) ...
	private void buildMaxHeap() {
		// half of nodes will be leaves ...
		for (int i = (heapSize - 1) / 2; i >= 0; i--) {
			maxHeapify(elements, i);
		}
	}
	
	public Element[] getElements() {
		return elements;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
	
	public void sort() {
		int length = elements.length;
		for (int i = length - 1; i >= 1; i--) {
			swap(elements, 0, i);
			heapSize--;
			maxHeapify(elements, 0);
		}
	}
	
	// O(lgn) ...
	private void maxHeapify(Element[] e, int index) {
		int left = left(index);
		int right = right(index);
		int largest = index;
		if (left < heapSize && 
				e[left].key.compareTo(e[index].key) > 0) {
			largest = left;
		} else {
			largest = index;
		}
		if (right < heapSize &&
				e[right].key.compareTo(e[largest].key) > 0) {
			largest = right;
		}
		if (largest != index) {
			swap(e, index, largest);
			maxHeapify(e, largest);
		}
	}

	private void swap(Element[] e, int index, int largest) {
		Element temp = e[index];
		e[index] = e[largest];
		e[largest] = temp;
	}
	
	// parent(i) = i / 2 when i starts in 1 ...
	private int parent(int index) {
		return index % 2 == 0 ? index / 2 - 1 : index / 2;
	}
	
	// left(i) = 2i when i starts in 1 ...
	private int left(int index) {
		return (2 * index) + 1;
	}
	
	// right(i) = 2i + 1 when i starts in 1 ...
	private int right(int index) {
		return (2 * index + 1) + 1;
	}
	
}
