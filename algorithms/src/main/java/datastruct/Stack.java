package datastruct;

import java.util.Arrays;

public class Stack {
	private Element[] elements = null;
	// next available position ...
	int top = -1;

	public Stack(int size) {
		this.elements = new Element[size];
		top = 0;
	}
	
	public void push(Element x) {
		if (!full()) {
			elements[top++] = x;
		}
	}
	
	public Element pop() {
		Element x = null;
		if (!empty()) {
			x = elements[--top];
		}
		return x;
	}
	
	public boolean empty() {
		return top == 0;
	}
	
	public boolean full() {
		return !empty() && top == elements.length;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
}
