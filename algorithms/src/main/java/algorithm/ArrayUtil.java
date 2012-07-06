package algorithm;

public class ArrayUtil {

	// O(n^2) inverted BubbleSort ...
	public static void nonZeroFirst(int[] elements) {
		int maxIndex = 0;
		int size = elements.length;
		for (int i = 0; i < size - 1; i++) {
			maxIndex = i;
			for (int j = i + 1; j < size; j++) {
				// non-positive numbers are equal positive numbers in this
				// algorithm ...
				int aux = elements[j] < 0 ? -elements[j] : elements[j];
				int maxCandidate = elements[maxIndex] < 0 ? -elements[maxIndex] :
					elements[maxIndex];
				if (aux > maxCandidate) {
					maxIndex = j;
				}
			}
			swap(elements, i, maxIndex);
		}
	}
	
	// O(n) ...
	public static void fasterNonZeroFirst(int[] elements) {
		int size = elements.length;
		int lastNonZeroIndex = 0;
		for (int i = 0; i < size; i++) {
			if (elements[i] != 0) {
				swap(elements, i, lastNonZeroIndex);
				lastNonZeroIndex++;
			}
		}
	}
	
	// O(n) ... (0 means None)
	public static int whichNumberIsMissing(int[] elements) {
		int size = elements.length;
		int n = elements.length + 1;
		int sum = 0;
		int expectedSum = (n * (n + 1)) / 2;
		for (int i = 0; i < size; i++) {
			sum += elements[i];
		}
		return expectedSum - sum;
	}
	
	// O(n) ...
	public static boolean hasNumberTwice(int[] elements) {
		int size = elements.length;
		int n = size;
		int sum = 0;
		int expectedSum = (n * (n + 1)) / 2;
		for (int i = 0; i < size; i++) {
			sum += elements[i];
		}
		int dif = expectedSum - sum;
		
		boolean has = false;
		if (dif != 0) {
			has = true;
		}
		return has;
	}
	
	public static void swap(int[] e, int i, int j) {
		int x = e[i];
		e[i] = e[j];
		e[j] = x;
	}
}
