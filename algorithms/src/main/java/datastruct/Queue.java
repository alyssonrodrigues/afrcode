package datastruct;

import java.util.Arrays;

public class Queue {
	private Element[] elements = null;
	private int size = -1;
	private int head = 0;
	// next available position ...
	private int tail = head;
	// full status sentinel ...
	boolean full = false;

	public Queue(int size) {
		this.elements = new Element[size];
		this.size = size;
	}

	public void enqueue(Element x) {
		if (!full()) {
			elements[tail] = x;
			if (tail == size - 1) {
				tail = 0;

			} else {
				tail++;
			}
			full = head == tail;
		}
	}

	public Element dequeue() {
		Element x = null;
		if (!empty()) {
			x = elements[head];
			if (head == size - 1) {
				head = 0;
			} else {
				head++;
			}
			full = false;
		}
		return x;
	}

	public boolean empty() {
		return head == tail && !full();
	}

	public boolean full() {
		return full;
	}

	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
}
